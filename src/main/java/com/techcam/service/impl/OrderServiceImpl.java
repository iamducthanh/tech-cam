package com.techcam.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcam.config.Config;
import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.Customer.CustomerRequest;
import com.techcam.dto.request.MailDto;
import com.techcam.dto.request.order.*;
import com.techcam.dto.request.techcamlog.TechCamlogRequest;
import com.techcam.dto.response.Customer.CustomerInfoResponse;
import com.techcam.dto.response.order.GetInfoOrder;
import com.techcam.dto.response.order.GetInfoOrderDetails;
import com.techcam.dto.response.order.OrderResponse;
import com.techcam.dto.response.PromotionResponseDTO;
import com.techcam.dto.response.voucher.VoucherResponse;
import com.techcam.dto.response.voucher.VoucherUseByOrderResponse;
import com.techcam.entity.*;
import com.techcam.exception.TechCamExp;
import com.techcam.repo.*;
import com.techcam.service.*;
import com.techcam.type.*;
import com.techcam.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/20/2022
 * Project_name: Tech-cam
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepo ordersRepo;
    @Autowired
    private IOrderDetailsRepo orderDetailsRepo;
    @Autowired
    private IProductRepo productRepo;
    @Autowired
    private IVoucherService voucherService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IReceiptVoucherRepo receiptVoucherRepo;
    @Autowired
    private MailerUtil mailerUtil;
    @Autowired
    private ITechCamLogService techCamLogService;
    @Autowired
    private IVoucherRepo voucherRepo;
    @Autowired
    private IPromotionService promotionService;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private SessionUtil sessionUtil;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    @Override
    public List<GetInfoOrder> getAllOrder() {
        List<OrdersEntity> ordersEntityList = ordersRepo.findAllByOrderByCreateDateDesc();
        Type listType = new TypeToken<List<GetInfoOrder>>() {
        }.getType();
        return MODEL_MAPPER.map(ordersEntityList, listType);
    }

    @Override
    public GetInfoOrder getInfoOrderByBankTransaction(String bankTransaction) {
        OrdersEntity orders = ordersRepo.findByBankTransaction(bankTransaction);
        return Objects.isNull(orders) ? null : MODEL_MAPPER.map(orders, GetInfoOrder.class);
    }

    @Override
    public OrderResponse checkOutBank(String bankStatus, String bankTransaction) {
        OrderResponse response = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        OrdersEntity orders = ordersRepo.findByBankTransaction(bankTransaction);
        if (Objects.isNull(orders)) {
            response.setStatus(CommonStatus.FAIL.name());
        }
        if (!StringUtils.equalsIgnoreCase(bankStatus, "00")) {
            response.setStatus(CommonStatus.FAIL.name());
            List<OrderdetailEntity> orderdetailEntities = orderDetailsRepo.findAllByOrdersIdAndDeleteFlag(orders.getId(), false);
            List<ProductEntity> productEntities = new ArrayList<>();
            orderdetailEntities.stream().filter(item -> {
                item.getProduct().setQuantity(item.getProduct().getQuantity() + item.getQuantity());
                productEntities.add(item.getProduct());
                return false;
            }).collect(Collectors.toList());
            productRepo.saveAll(productEntities);
            orders.setStatus(OrderStatus.PAID.name());
            orders.setDeleteFlag(false);
            ordersRepo.save(orders);
        }else {
            response.setStatus(CommonStatus.FAIL.name());
        }
        return response;
    }

    @Override
    public List<GetInfoOrder> getAllOrderByStatus(String status) {
        List<OrdersEntity> ordersEntityList = ordersRepo.findAllByStatusAndDeleteFlagFalseOrderByCreateDateDesc(status);
        Type listType = new TypeToken<List<GetInfoOrder>>() {
        }.getType();
        return MODEL_MAPPER.map(ordersEntityList, listType);
    }

    @Override
    public List<GetInfoOrder> getAllOrderByDeleteFlag() {
        List<OrdersEntity> ordersEntityList = ordersRepo.findAllByDeleteFlagFalseOrderByCreateDateDesc();
        Type listType = new TypeToken<List<GetInfoOrder>>() {
        }.getType();
        return MODEL_MAPPER.map(ordersEntityList, listType);
    }

    @Override
    public GetInfoOrder findOrderById(Integer id) {
        OrdersEntity orderEntity = ordersRepo.findByIdAndDeleteFlagFalse(id);
        Type listType = new TypeToken<GetInfoOrder>() {
        }.getType();

        return MODEL_MAPPER.map(orderEntity, listType);
    }

    @Override
    public List<GetInfoOrderDetails> findAllOrdersDetailsById(Integer id) {
        List<OrderdetailEntity> orderdetailEntities = orderDetailsRepo.findAllByOrdersIdAndDeleteFlag(id, false);
        Type listType = new TypeToken<List<GetInfoOrderDetails>>() {
        }.getType();
        return MODEL_MAPPER.map(orderdetailEntities, listType);
    }

    @Override
    public OrderResponse resgistrationOrder(OrderRequest request, HttpServletRequest httpServletRequest) {
        OrderResponse response = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        String vnp_ref = "";
        VoucherResponse voucherResponse = null;
        List<ProductEntity> productEntities = getInfoProducts(request.getProductDetails());
        if (StringUtils.isNotBlank(request.getVoucherId())) {
            voucherResponse = getInfoVoucher(request.getVoucherId(), request.getCustomer().getPhoneNumber());
            if (Objects.isNull(voucherResponse)) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
            }
        }
        if (CollectionUtils.isEmpty(productEntities)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        if (Objects.nonNull(request.getIpAddress())) {
            System.out.println(new Date());
            int countRequest = 0;
            try {
                countRequest = ordersRepo.countByPhoneNumberCustomer(SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(new Date())), request.getCustomer().getPhoneNumber(), request.getIpAddress());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(countRequest);
            if (countRequest >= 15) {
                throw new TechCamExp(ConstantsErrorCode.CUST_ORDER_TOO_MUCH);
            }
        }
        // lưu khách hàng
        CustomerInfoResponse customerInfoResponse = resgistrationCustomer(request.getCustomer());
        if (Objects.isNull(customerInfoResponse)) {
            throw new TechCamExp(ConstantsErrorCode.INTERNAL_SERVER_ERROR);
        }
        int itemQuantity = request.getProductDetails().stream().mapToInt(e -> e.getQuantity()).sum();
        OrderProductRequest orderRequestProduct = getTotalProduct(productEntities, request.getProductDetails());
        int totalDiscount = 0;
        VoucherEntity voucher = new VoucherEntity();
        if (Objects.nonNull(voucherResponse)) {
            totalDiscount = valueVoucher(voucherResponse, orderRequestProduct.getTotalAmount()) + orderRequestProduct.getTotalDiscount();
            voucher.setId(voucherResponse.getVoucherId());
        }
        String orderStratus = "";
        if (request.getOrderType().equals(OrderType.COUNTER.name()) || request.getPaymentMethod().equals(OrderMethod.PAYMENT.name())) {
            setQuantityProductOrder(request, productEntities);
        }
//        else if(request.getOrderType().equals(OrderType.COUNTER.name())){
//            orderStratus = OrderStatus.CONFIRM.name();
//        }
        if (request.getOrderType().equals(OrderType.COUNTER.name())) {
            orderStratus = OrderStatus.CONFIRM.name();
        } else {
            orderStratus = OrderStatus.VERIFY.name();
        }
        // lưu hóa đơn.
        OrdersEntity ordersEntity = new OrdersEntity().builder()
//                .id(UUID.randomUUID().toString())
                .customer(MODEL_MAPPER.map(customerInfoResponse, CustomerEntity.class))
                .voucher(Objects.isNull(voucher.getId())? null : voucher)
                .ipAddress(request.getIpAddress())
                .orderType(request.getOrderType())
                .recipientName(StringUtils.isNotBlank(request.getRecipientName()) ? request.getRecipientName() : request.getCustomer().getFullName())
                .recipientPhone(StringUtils.isNotBlank(request.getRecipientPhone()) ? request.getRecipientPhone() : request.getCustomer().getPhoneNumber())
                .recipientAddress(StringUtils.isNotBlank(request.getRecipientAddress()) ? request.getRecipientAddress() : request.getCustomer().getAddress())
                .totalAmount(totalDiscount)
                .itemQuantity(itemQuantity)
                .tax(orderRequestProduct.getTotalAmount())
                .createBy(request.getCustomer().getFullName())
                .paymentMethod(request.getPaymentMethod())
                .createDate(new Date())
                .modifierDate(new Date())
                .orderDate(new Date())
                .note(request.getNote())
                .deleteFlag(false)
                .feeDelivery(request.getFeeDelivery())
//                .salesPerson(request.getOrderType().equals(OrderType.COUNTER.name()) ? "Quang" : null)
                .status(OrderStatus.UNPAID.name())
                .transactionStatus(orderStratus)
                .build();
        if (StringUtils.equalsIgnoreCase(request.getPaymentMethod(), OrderMethod.PAYMENT.name())) {
            vnp_ref = Config.getRandomNumber(8);
            ordersEntity.setBankTransaction(vnp_ref);
            ordersEntity.setDeleteFlag(true);
        }
        System.out.println(ordersEntity);
        OrdersEntity orderSave = ordersRepo.save(ordersEntity);
        if (request.getOrderType().equalsIgnoreCase(OrderType.ONLINE.name())) {
            GetInfoOrder infoOrder = GetInfoOrder.builder()
                    .id(orderSave.getId())
                    .orderDate(orderSave.getOrderDate())
                    .tax(orderSave.getTax())
                    .transactionStatus(orderSave.getTransactionStatus())
                    .paymentDate(orderSave.getPaymentDate())
                    .itemQuantity(orderSave.getItemQuantity())
                    .totalAmount(orderSave.getTotalAmount())
                    .orderType(orderSave.getOrderType())
                    .stockKeeper(orderSave.getStockKeeper())
                    .recipientName(orderSave.getRecipientName())
                    .recipientPhone(orderSave.getRecipientPhone())
                    .paymentMethod(orderSave.getPaymentMethod())
                    .recipientAddress(orderSave.getRecipientAddress())
                    .shipmentStatus(orderSave.getShipmentStatus())
                    .salesPerson(orderSave.getSalesPerson())
                    .accounting(orderSave.getAccounting())
                    .shipmentId(orderSave.getShipmentId())
                    .status(orderSave.getStatus())
                    .note(orderSave.getStatus())
                    .createBy(orderSave.getCreateBy())
                    .modifierBy(orderSave.getModifierBy())
                    .deleteFlag(orderSave.getDeleteFlag())
                    .ipAddress(orderSave.getIpAddress())
                    .build();
            messagingTemplate.convertAndSend("/topic/notify", infoOrder);
        }


        List<OrderdetailEntity> orderdetailEntities = new ArrayList<>();
        orderRequestProduct.getProductDetailsRequests().stream().filter(e -> {
                    OrderdetailEntity orderdetailEntity = new OrderdetailEntity();
                    orderdetailEntity.setId(UUID.randomUUID().toString());
                    orderdetailEntity.setOrders(orderSave);
                    orderdetailEntity.setNote(e.getNote());
                    orderdetailEntity.setDeleteFlag(false);
                    orderdetailEntity.setDiscount(e.getDiscount());
                    orderdetailEntity.setQuantity(e.getQuantity());
                    orderdetailEntity.setProduct(new ProductEntity().toBuilder().id(e.getProductId()).build());
                    orderdetailEntities.add(orderdetailEntity);
                    return false;
                }
        ).collect(Collectors.toList());
        try {
            orderDetailsRepo.saveAll(orderdetailEntities);
            if (request.getOrderType().equals(OrderType.COUNTER.name()) || request.getPaymentMethod().equals(OrderMethod.PAYMENT.name())) {
                productRepo.saveAll(productEntities);
            }
//            if (Objects.nonNull(voucherResponse)) {
//                // todo lưu ý hỏi ngọc save voucher thế này được không
//                voucherResponse.setVoucherQuantity(voucherResponse.getVoucherQuantity() - 1);
//                VoucherRequest voucherRequest = MODEL_MAPPER.map(voucherResponse, VoucherRequest.class);
//                voucherService.createVoucher(voucherRequest);
//            }
            if (request.getOrderType().equals(OrderType.ONLINE.name())) {
                if (request.getPaymentMethod().equalsIgnoreCase(OrderMethod.PAYMENT.name())) {
                    String vnpay = VNPAYService.payments(ordersEntity.getTax() - ordersEntity.getTotalAmount()+ordersEntity.getFeeDelivery(), vnp_ref, httpServletRequest);
                    response.setVnpay(vnpay);
                }
                if (Objects.nonNull(customerInfoResponse.getEmail())) {
                    sendMail(String.format(MessageUtil.MAIL_ORDER_REGISTRATION_ONLINE, orderSave.getId()), customerInfoResponse.getEmail(),
                            MessageUtil.SUBJECT_MAIL_ORDER, MessageUtil.FROM_MAIL);
                }
            }
            if (request.getOrderMethod().equals(OrderType.COUNTER.name())) {
                saveLog(DescLog.INSERT_ORDER, "HD00" + orderSave.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(CommonStatus.FAIL.name());
        }

        return response;
    }

    private void sendMail(String body, String toMail, String subject, String fromMail) throws MessagingException {
        MailDto mailDto = new MailDto();
        mailDto.setBody(body);
        mailDto.setTo(toMail);
        mailDto.setSubject(subject);
        mailDto.setFrom(fromMail);
        mailerUtil.send(mailDto);
    }

    private void setQuantityProductOrder(OrderRequest request, List<ProductEntity> productEntities) {
        productEntities.stream().filter(productEntity -> {
            request.getProductDetails().stream().filter(orderEntity -> {
                if (orderEntity.getProductId().equals(productEntity.getId())) {
                    System.out.println(orderEntity.getQuantity() + "-----" + productEntity.getQuantity());
                    if (orderEntity.getQuantity() > productEntity.getQuantity()) {
                        throw new TechCamExp(ConstantsErrorCode.ORDER_PRODUCT_OUT_OF_STOCK);
                    } else {
                        productEntity.setQuantity(productEntity.getQuantity() - orderEntity.getQuantity());
                    }
                }
                return false;
            }).collect(Collectors.toList());
            return false;
        }).collect(Collectors.toList());
    }

    private int valueVoucher(VoucherResponse voucherResponse, int sumMoney) {
        if (voucherResponse.getVoucherTypeDiscount().equals("%")) {
            return (int) ((sumMoney * voucherResponse.getVoucherDiscount())/100);
        }
        int sum = Math.toIntExact(voucherResponse.getVoucherDiscount());
        return sum;
    }

    private boolean checkPermissionVoucher(List<ProductEntity> productEntities) {
        //todo đợi service khuyến mại kiểm tra nếu tồn tại khuyến mại sản phẩm
        Optional<ProductEntity> optionalProduct = productEntities.stream().filter(item -> item.getQuantity() == 1000).findFirst();
        return optionalProduct.isPresent();
    }

    @Override
    public OrderResponse editOrderDetails(EditOrderDetailRequest requests) {
        OrderResponse response = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        List<OrderdetailEntity> orderDetailsSaves = new ArrayList<>();
        if (CollectionUtils.isEmpty(requests.getOrderProductDetailsRequests())) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        List<String> ids = requests.getOrderProductDetailsRequests().stream().map(OrderProductDetailsRequest::getId).collect(Collectors.toList());
        List<String> productIds = requests.getOrderProductDetailsRequests().stream().map(OrderProductDetailsRequest::getProductId).collect(Collectors.toList());
        List<OrderdetailEntity> orderDetailEntities = orderDetailsRepo.findAllByIdNotInAndOrdersIdAndDeleteFlagFalse(ids, requests.getOrderId());
        List<ProductEntity> productEntities = productRepo.findAllByIdInAndDeleteFlagFalse(productIds);
        productEntities.stream().filter(productEntity -> {
            requests.getOrderProductDetailsRequests().stream().filter(item -> {
                if (productEntity.getId().equals(item.getProductId())) {
                    if (productEntity.getQuantity() < item.getQuantity()) {
                        throw new TechCamExp(ConstantsErrorCode.ORDER_PRODUCT_OUT_OF_STOCK);
                    }
                }
                return false;
            }).collect(Collectors.toList());
            return false;
        }).collect(Collectors.toList());
        if (!orderDetailEntities.isEmpty()) {
            orderDetailEntities.forEach(item -> item.setDeleteFlag(true));
            orderDetailsRepo.saveAll(orderDetailEntities);
        }
        List<OrderdetailEntity> orderDetailEntityList = orderDetailsRepo.findAllByIdInAndOrdersIdAndDeleteFlagFalse(ids, requests.getOrderId());
        if (!orderDetailEntityList.isEmpty()) {
            requests.getOrderProductDetailsRequests().stream().filter(
                    item -> {
                        orderDetailEntityList.stream().filter(e -> {
                            if (Objects.nonNull(item.getId()) && e.getId().equals(item.getId())) {
                                if (e.getQuantity() != item.getQuantity()) {
                                    e.setQuantity(item.getQuantity());
                                    e.setNote(item.getNote());
                                    orderDetailsSaves.add(e);
                                }
                            }
                            if (StringUtils.isBlank(item.getId())) {
                                String id = UUID.randomUUID().toString();
                                item.setId(id);
                                OrderdetailEntity orderdetailEntity = new OrderdetailEntity();
                                orderdetailEntity.setQuantity(item.getQuantity());
                                orderdetailEntity.setId(id);
                                orderdetailEntity.setDeleteFlag(false);
                                orderdetailEntity.setNote(item.getNote());
                                orderdetailEntity.setDeleteFlag(false);
                                orderdetailEntity.setDiscount((int) getSaleProduct(item.getProductId()));
                                orderdetailEntity.setQuantity(item.getQuantity());
                                OrdersEntity ordersEntity = new OrdersEntity();
                                ordersEntity.setId(requests.getOrderId());
                                ProductEntity product = new ProductEntity();
                                product.setId(item.getProductId());
                                orderdetailEntity.setProduct(product);
                                orderdetailEntity.setOrders(ordersEntity);
                                orderDetailsSaves.add(orderdetailEntity);

                            }
                            return false;
                        }).collect(Collectors.toList());
                        return false;
                    }
            ).collect(Collectors.toList());
        }
        try {
            List<OrderdetailEntity> orderDetailsSaveALl = orderDetailsRepo.saveAll(orderDetailsSaves);
            OrdersEntity orders = ordersRepo.findByIdAndDeleteFlagFalse(requests.getOrderId());

//            int itemQuantity = orderDetailsSaves.stream().mapToInt(e -> e.getQuantity()).sum();
            editOrderEntity(orderDetailsSaveALl, orders);
            ordersRepo.save(orders);
            saveLog(DescLog.EDIT_ORDER_VERIFY, "HD00" + orders.getId());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(CommonStatus.FAIL.name());
        }
        return response;
    }

    private void editOrderEntity(List<OrderdetailEntity> orderDetailsSaveALl, OrdersEntity orders) {
        orders.setItemQuantity(0);
        orders.setTax(0);
        orders.setTotalAmount(0);
        orders.setModifierBy(getInfoStaff().getUsername());
        orderDetailsSaveALl.stream().filter(item -> {
            orders.setItemQuantity(orders.getItemQuantity() + item.getQuantity());
            orders.setTax((int) (orders.getTax() + (item.getQuantity() * item.getProduct().getPrice())));
            // todo chưa sửa khuyến mại
            orders.setTotalAmount((int) (orders.getTotalAmount() + (item.getQuantity() * item.getProduct().getPrice() * 0.1)));
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public OrderResponse confirmOrderSalePerson(ConfirmSalePersonRequest request) {
        OrderResponse response = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<ProductEntity> productEntities = new ArrayList<>();
        OrdersEntity orders = ordersRepo.findByIdAndTransactionStatusAndDeleteFlagFalse(request.getId(), OrderStatus.VERIFY.name());
        if (Objects.isNull(orders)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        // lấy danh sách order details
        if (OrderMethod.CASH.name().equalsIgnoreCase(orders.getPaymentMethod())) {
            List<OrderdetailEntity> orderDetailEntities = orderDetailsRepo.findAllByOrdersIdAndDeleteFlag(request.getId(), false);
            // kiểm tra xem danh sách sản phẩm có tồn tại không
            List<String> productId = orderDetailEntities.stream().map(item -> item.getProduct().getId()).collect(Collectors.toList());
            productEntities = productRepo.findAllByIdInAndDeleteFlagFalse(productId);
            if (CollectionUtils.isEmpty(productEntities) || productEntities.size() != orderDetailEntities.size()) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
            }
            // kiểm tra số lượng trong kho có tồn tại không.
            // todo bắn messga ra sau
            productEntities.stream().filter(productEntity -> {
                orderDetailEntities.stream().filter(orderEntity -> {
                    if (orderEntity.getProduct().getId().equals(productEntity.getId())) {
                        if (orderEntity.getQuantity() > productEntity.getQuantity()) {
                            throw new TechCamExp(ConstantsErrorCode.ORDER_PRODUCT_OUT_OF_STOCK);
                        } else {
                            productEntity.setQuantity(productEntity.getQuantity() - orderEntity.getQuantity());
                        }
                    }
                    return false;
                }).collect(Collectors.toList());
                return false;
            }).collect(Collectors.toList());
        }
        // kiểm tra xem người dùng có thay đổi người nhận hàng không thiếu ngày nhận hàng
        orders.setDeliveryDate(request.getDeliveryDate());
        orders.setRecipientName(request.getRecipientName());
        orders.setRecipientPhone(request.getRecipientPhone());
        orders.setRecipientAddress(request.getRecipientAddress());
        orders.setSalesPerson(getInfoStaff().getUsername());
        orders.setNote(request.getNote());
        orders.setTransactionStatus(OrderStatus.CONFIRM.name());
        orders.setModifierDate(new Date());
        orders.setFeeDelivery(request.getFeeDelivery());
        try {
            productRepo.saveAll(productEntities);
            ordersRepo.save(orders);
            if (Objects.isNull(orders.getCustomer().getEmail())) {
                sendMail(String.format(MessageUtil.MAIL_CONFIRM_ORDER, orders.getId(), ConvertDateUtil.convertDateTime(orders.getDeliveryDate())), orders.getCustomer().getEmail(),
                        MessageUtil.SUBJECT_MAIL_ORDER, MessageUtil.FROM_MAIL);
            }
            saveLog(DescLog.CONFIRM_ORDER, "HD00" + orders.getId());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(CommonStatus.FAIL.name());
        }

        return response;
    }

    @Override
    public OrderResponse confirmExportOrder(ConfirmExportOrderRequest request) {
        OrderResponse orderResponse = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        OrdersEntity orders = ordersRepo.findByIdAndTransactionStatusAndDeleteFlagFalse(request.getId(), OrderStatus.CONFIRM.name());
        if (Objects.isNull(orders)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        validateImei(request);
        List<String> ids = request.getProductDetailsRequests().stream().map(OrderProductDetailsRequest::getId).collect(Collectors.toList());
        List<OrderdetailEntity> orderDetails = orderDetailsRepo.findAllByIdInAndOrdersIdAndDeleteFlagFalse(ids, request.getId());
        // kiểm tra xem có đúng id product truyền vào không
        if (orderDetails.size() != request.getProductDetailsRequests().size()) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        // kiểm tra imei và số lượng bán vào có đúng ko.
        try {
            orderDetails.stream().filter(item -> {
                request.getProductDetailsRequests().stream().filter(orderProductDetailsRequest -> {
                    if (item.getProduct().getId().equals(orderProductDetailsRequest.getProductId())) {
                        if (item.getQuantity() != orderProductDetailsRequest.getImei().size()) {
                            // todo bán lỗi imei truyền vào chưa đủ số lượng
                            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
                        } else {
                            item.setImei(mapperImei(orderProductDetailsRequest.getImei()));
                        }
                    }
                    return false;
                }).collect(Collectors.toList());
                return false;
            }).collect(Collectors.toList());
            orderDetailsRepo.saveAll(orderDetails);
            orders.setStockKeeper(getInfoStaff().getUsername());
            orders.setTransactionStatus(OrderStatus.SHIPPING.name());
            orders.setModifierDate(new Date());
            ordersRepo.save(orders);
            saveLog(DescLog.CONFIRM_EXPORT_ORDER, "HD00" + orders.getId());
        } catch (Exception e) {
            e.printStackTrace();
            orderResponse.setStatus(CommonStatus.FAIL.name());
        }
        return orderResponse;
    }

    private void validateImei(ConfirmExportOrderRequest request) {
        request.getProductDetailsRequests().stream().filter(item -> {
            List<String> imei = item.getImei().stream().distinct().collect(Collectors.toList());
            if (imei.size() != item.getImei().size()) {
                throw new TechCamExp(ConstantsErrorCode.IMEI_DUPLICATE);
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public OrderResponse payTheBill(CustomerPayTheBillRequest request) {
        OrderResponse orderResponse = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        OrdersEntity orders = ordersRepo.findByIdAndDeleteFlagFalse(request.getId());
        if (Objects.isNull(orders)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        orders.setAccounting(getInfoStaff().getUsername());
        orders.setModifierDate(new Date());
        orders.setTransactionStatus(OrderStatus.DONE.name());
        orders.setStatus(OrderStatus.PAID.name());
        ReceiptVoucherEntity receiptVoucherEntity = new ReceiptVoucherEntity();
        receiptVoucherEntity.setId(UUID.randomUUID().toString());
        receiptVoucherEntity.setCreateDate(new Date());
        receiptVoucherEntity.setModifierDate(new Date());
        receiptVoucherEntity.setReceiptValue(orders.getTax() - orders.getTotalAmount());
        receiptVoucherEntity.setDeleteFlag(false);
        receiptVoucherEntity.setPayer(orders.getCustomer().getFullName());
        receiptVoucherEntity.setPayerPhone(orders.getCustomer().getPhoneNumber());
        receiptVoucherEntity.setReceiptName(MessageUtil.RECEIPT_NAME_ORDER);
        receiptVoucherEntity.setDescription(String.format(MessageUtil.SAVE_ORDER_CUSTOMER_DONE, orders.getId()));
        receiptVoucherEntity.setGivenMoney(Objects.isNull(request.getGivenMoney()) ? (orders.getTax() - orders.getTotalAmount()) : request.getGivenMoney());
        receiptVoucherEntity.setReturnMoney(Objects.isNull(request.getGivenMoney()) ? 0 : request.getGivenMoney() - receiptVoucherEntity.getReceiptValue());
//        receiptVoucherEntity.setOrders(orders);
        try {
            ordersRepo.save(orders);
            receiptVoucherRepo.save(receiptVoucherEntity);
            if (Objects.isNull(orders.getCustomer().getEmail())) {
                sendMail(MessageUtil.MAIL_CUSTOMER_ORDER_DONE, orders.getCustomer().getEmail(),
                        MessageUtil.SUBJECT_MAIL_ORDER, MessageUtil.FROM_MAIL);
            }
            saveLog(DescLog.PAY_ORDER, "HD00" + orders.getId());
        } catch (Exception e) {
            e.printStackTrace();
            orderResponse.setStatus(CommonStatus.FAIL.name());
        }
        return orderResponse;
    }

    @Override
    public OrderResponse editOrderDetailsConfirm(EditOrderDetailsConfirmRequest request) {
        OrderResponse response = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        OrdersEntity orders = ordersRepo.findByIdAndDeleteFlagFalse(request.getOrderId());
        if (CollectionUtils.isEmpty(request.getImei())) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        if (Objects.isNull(orders)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        List<String> validateImeiDuplicate = request.getImei().stream().distinct().collect(Collectors.toList());
        if (validateImeiDuplicate.size() != request.getImei().size()) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        OrderdetailEntity orderdetailEntity = orderDetailsRepo.findByOrdersIdAndProductId(request.getOrderId(), request.getProductId());
        if (Objects.isNull(orderdetailEntity)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        ProductEntity productEntity = productRepo.getByIdAndDeleteFlagIsFalse(request.getProductId());
        if (Objects.isNull(productEntity)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        List<String> imeiList = convertToListImei(orderdetailEntity.getImei());
        List<String> finalImeiList = imeiList;
        request.getImei().stream().filter(item -> {
            if (!checkImei(item, finalImeiList)) throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
            return false;
        }).collect(Collectors.toList());
        if (imeiList.size() < request.getImei().size()) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        imeiList = imeiList.stream().filter(item -> !checkImei(item, request.getImei())).collect(Collectors.toList());
        orderdetailEntity.setImei(mapperImei(imeiList));
        orderdetailEntity.setQuantity(orderdetailEntity.getQuantity() - request.getImei().size());
        String noteImei = request.getImei().stream().map(String::toString).collect(Collectors.joining(", "));
        orderdetailEntity.setNote(request.getNote() + String.format(MessageUtil.NOTE_EDIT_ORDER_DETAILS, noteImei));
        try {
            orderDetailsRepo.save(orderdetailEntity);
            productEntity.setQuantity(productEntity.getQuantity() + request.getImei().size());
            productRepo.save(productEntity);
            // todo chưa có cái trường lưu sản phẩm lỗi
            List<OrderdetailEntity> orderdetailEntities = orderDetailsRepo.findAllByOrdersIdAndDeleteFlag(request.getOrderId(), false);
            editOrderEntity(orderdetailEntities, orders);
            orders.setModifierDate(new Date());
            orders.setModifierBy(getInfoStaff().getUsername());
            ordersRepo.save(orders);
            saveLog(DescLog.EDIT_ORDER_VERIFY, "HD00" + orders.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public boolean checkImei(String imei, List<String> imeiList) {
        Optional<String> optionalS = imeiList.stream().filter(item -> item.equals(imei)).findFirst();
        return optionalS.isPresent();
    }

    public List<String> convertToListImei(String imei) {
        List<String> stringList = new ArrayList<>();
        try {
            stringList = OBJECT_MAPPER.readValue(imei, new TypeReference<List<String>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    public String mapperImei(List<String> imeiSave) {
        String imei = "";
        try {
            imei = OBJECT_MAPPER.writeValueAsString(imeiSave);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return imei;
    }

    @Override
    public OrderResponse cancelOrder(String id) {
        OrderResponse orderResponse = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        OrdersEntity orders = ordersRepo.findByIdAndDeleteFlagFalse(Integer.parseInt(id));
        if (Objects.isNull(orders)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        try {
            if (!orders.getTransactionStatus().equals(OrderStatus.VERIFY.name())) {
                List<ProductEntity> productEntitiesSave = new ArrayList<>();
                List<OrderdetailEntity> orderDetailEntities = orderDetailsRepo.findAllByOrdersIdAndDeleteFlag(orders.getId(), false);
                List<String> ids = orderDetailEntities.stream().map(item -> item.getProduct().getId()).collect(Collectors.toList());
                List<ProductEntity> productEntities = productRepo.findAllByIdInAndDeleteFlagFalse(ids);
                productEntities.stream().filter(product -> {
                    orderDetailEntities.stream().filter(orderDetailEntity -> {
                        if (orderDetailEntity.getProduct().getId().equals(product.getId())) {
                            product.setQuantity(product.getQuantity() + orderDetailEntity.getQuantity());
                            productEntitiesSave.add(product);
                        }
                        return false;
                    }).collect(Collectors.toList());
                    return false;
                }).collect(Collectors.toList());
                productRepo.saveAll(productEntitiesSave);
            }
            orders.setDeleteFlag(true);
            orders.setModifierDate(new Date());
            orders.setModifierBy(getInfoStaff().getUsername());
            orders.setStatus(OrderStatus.CANCEL.name());
            ordersRepo.save(orders);
            // mới thêm để cộng lại voucher
            if (Objects.nonNull(orders.getVoucher())) {
                VoucherEntity voucherEntity = orders.getVoucher();
                voucherEntity.setQuantity(voucherEntity.getQuantity() + 1);
                voucherRepo.save(voucherEntity);
            }
            saveLog(DescLog.CANCEL_ORDER, "HD00" + orders.getId());
        } catch (Exception e) {
            e.printStackTrace();
            orderResponse.setStatus(CommonStatus.FAIL.name());
        }
        return orderResponse;
    }

    @Override
    public List<VoucherUseByOrderResponse> findAllByVoucherId(String code) {
        return ordersRepo.findAllByVoucherCodeAndTransactionStatusNotInAndDeleteFlagFalse(code, OrderStatus.CANCEL.name())
                .stream().map(this::mapToVoucherUseResponse).collect(Collectors.toList());
    }

    @Override
    public String editOrder(OrderRequest order) {
        OrdersEntity entity = ordersRepo.getById(order.getId());
        entity.setRecipientName(order.getRecipientName());
        entity.setRecipientPhone(order.getRecipientPhone());
        entity.setRecipientAddress(order.getRecipientAddress());
        entity.setDeliveryDate(order.getDeliveryDate());

        try {
            entity = ordersRepo.save(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "ok";
    }

    private <R> VoucherUseByOrderResponse mapToVoucherUseResponse(OrdersEntity x) {
        if (Objects.isNull(x)) return new VoucherUseByOrderResponse();
        return VoucherUseByOrderResponse.builder()
                .orderId(x.getId())
                .voucherId(Objects.isNull(x.getVoucher()) ? null : x.getVoucher().getId())
                .voucherCode(Objects.isNull(x.getVoucher()) ? null : x.getVoucher().getCode())
                .totalMoney(String.valueOf(x.getTotalAmount()))
                .customerId(Objects.isNull(x.getCustomer()) ? "" : x.getCustomer().getId())
                .fullName(Objects.isNull(x.getCustomer()) ? "" : x.getCustomer().getFullName())
                .dateUsed(ConvertDateUtil.convertDateTime(x.getOrderDate()))
                .build();
    }

    private OrderProductRequest getTotalProduct(List<ProductEntity> productEntities, List<OrderProductDetailsRequest> productDetails) {
        OrderProductRequest orderRequest = new OrderProductRequest();
        orderRequest.setTotalDiscount(0);
        orderRequest.setTotalAmount(0);
        List<OrderProductDetailsRequest> orderProductDetailsRequests = new ArrayList<>();
        // todo 1 tạm thời lài khuyến mại lấy sau có thể là tiền mặt
        productEntities.forEach(e -> {
            productDetails.stream().filter(item -> {
                        if (item.getProductId().equals(e.getId())) {
                            item.setDiscount((int) (e.getPrice() * item.getQuantity() * getSaleProduct(item.getProductId())));
                            orderRequest.setTotalDiscount((int) (orderRequest.getTotalDiscount() + item.getDiscount()));
                            orderRequest.setTotalAmount((int) (orderRequest.getTotalAmount() + e.getPrice() * item.getQuantity()));
                            orderProductDetailsRequests.add(item);
                        }
                        return false;
                    }
            ).findFirst();
        });
        orderRequest.setProductDetailsRequests(orderProductDetailsRequests);
        return orderRequest;
    }

    private CustomerInfoResponse resgistrationCustomer(CustomerRequest customerRequest) {
        CustomerInfoResponse customerInfoResponse = customerService.getCustomerByPhoneNumber(customerRequest.getPhoneNumber());
        if (Objects.isNull(customerInfoResponse)) {
            customerService.saveCustomer(customerRequest);
        } else {
            customerRequest.setId(customerInfoResponse.getId());
            customerService.updateCustomer(customerRequest, CommonTypeMethod.UPDATE.name());
        }
        customerInfoResponse = customerService.getCustomerByPhoneNumber(customerRequest.getPhoneNumber());
        return customerInfoResponse;
    }

    private VoucherResponse getInfoVoucher(String code, String phoneNumber) {
        VoucherResponse voucherResponse = voucherService.findFistByCode(code);
        if (Objects.nonNull(voucherResponse)) {
            OrdersEntity orders = ordersRepo.findFirstByVoucherCodeAndCustomerPhoneNumberAndTransactionStatusNotIn(code, phoneNumber, OrderStatus.CANCEL.name());
            if (Objects.nonNull(orders)) {
                throw new TechCamExp(ConstantsErrorCode.VOUCHER_NOT_EXISTS);
            }
        }
        return voucherResponse;
    }

    private List<ProductEntity> getInfoProducts(List<OrderProductDetailsRequest> productDetails) {
        List<ProductEntity> productEntities = new ArrayList<>();
        List<String> productIds = productDetails.stream()
                .map(OrderProductDetailsRequest::getProductId)
                .collect(Collectors.toList());
        productEntities = productRepo.findAllByIdInAndDeleteFlagFalse(productIds);
        if (productEntities.size() != productDetails.size()) {
            return new ArrayList<>();
        }
        return productEntities;
    }

    private StaffEntity getInfoStaff() {
//        StaffEntity staffEntity = (StaffEntity) sessionUtil.getObject("STAFF");
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setId("1");
        staffEntity.setUsername("aaa");
        return staffEntity;
    }

    public double getSaleProduct(String id) {

        return promotionService.getPromotionProduct(id);
    }

    public void saveLog(String typeMethod, String id) {
        StaffEntity staffEntity = getInfoStaff();
        TechCamlogRequest techCamlogRequest = new TechCamlogRequest();
        techCamlogRequest.setCreateBy(staffEntity.getUsername());
        techCamlogRequest.setStaffId(staffEntity.getId());
        techCamlogRequest.setOperationLink("thêm link sau ");
        techCamlogRequest.setOperationDesc(String.format(DescLog.LOG_ORDER, staffEntity.getUsername(), typeMethod, id));
        techCamlogRequest.setOperationKey(id);
        techCamLogService.saveLog(techCamlogRequest);

    }
}