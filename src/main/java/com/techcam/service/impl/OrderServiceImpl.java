package com.techcam.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.Customer.CustomerRequest;
import com.techcam.dto.request.order.*;
import com.techcam.dto.response.Customer.CustomerInfoResponse;
import com.techcam.dto.response.order.GetInfoOrder;
import com.techcam.dto.response.order.GetInfoOrderDetails;
import com.techcam.dto.response.order.OrderResponse;
import com.techcam.dto.response.voucher.VoucherResponse;
import com.techcam.entity.*;
import com.techcam.exception.TechCamExp;
import com.techcam.repo.IOrderDetailsRepo;
import com.techcam.repo.IOrderRepo;
import com.techcam.repo.IProductRepo;
import com.techcam.service.ICustomerService;
import com.techcam.service.IOrderService;
import com.techcam.service.IVoucherService;
import com.techcam.type.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


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
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public List<GetInfoOrder> getAllOrder() {
        List<OrdersEntity> ordersEntityList = ordersRepo.findAll();
        Type listType = new TypeToken<List<GetInfoOrder>>() {
        }.getType();
        return MODEL_MAPPER.map(ordersEntityList, listType);
    }

    @Override
    public List<GetInfoOrder> getAllOrderByStatus(String status) {
        List<OrdersEntity> ordersEntityList = ordersRepo.findAllByStatusAndDeleteFlagFalse(status);
        Type listType = new TypeToken<List<GetInfoOrder>>() {
        }.getType();
        return MODEL_MAPPER.map(ordersEntityList, listType);
    }

    @Override
    public List<GetInfoOrder> getAllOrderByDeleteFlag() {
        List<OrdersEntity> ordersEntityList = ordersRepo.findAllByDeleteFlagFalse();
        Type listType = new TypeToken<List<GetInfoOrder>>() {
        }.getType();
        return MODEL_MAPPER.map(ordersEntityList, listType);
    }

    @Override
    public List<GetInfoOrderDetails> findAllOrdersDetailsById(String id) {
        List<OrderdetailEntity> orderdetailEntities = orderDetailsRepo.findAllByOrdersIdAndDeleteFlag(id, false);
        Type listType = new TypeToken<List<GetInfoOrderDetails>>() {
        }.getType();
        return MODEL_MAPPER.map(orderdetailEntities, listType);
    }

    @Override
    public OrderResponse resgistrationOrder(OrderRequest request) {
        OrderResponse response = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        VoucherResponse voucherResponse = null;
        List<ProductEntity> productEntities = getInfoProducts(request.getProductDetails());
        if (Objects.nonNull(request.getVoucherId())) {
            voucherResponse = getInfoVoucher(request.getVoucherId());
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
            if (countRequest > 5) {
                throw new TechCamExp(ConstantsErrorCode.CUST_ORDER_TOO_MUCH);
            }
        }
        // lưu khách hàng
        CustomerInfoResponse customerInfoResponse = resgistrationCustomer(request.getCustomer());
        int itemQuantity = request.getProductDetails().stream().mapToInt(e -> e.getQuantity()).sum();
        OrderProductRequest orderRequestProduct = getTotalProduct(productEntities, request.getProductDetails());
        int totalDiscount = 0;
        if (Objects.nonNull(voucherResponse)) {
            if( voucherResponse.getVoucherKey().equals(VoucherKey.UNUSED.name())){
                if(checkPermissionVoucher(productEntities)){
                    throw new TechCamExp(ConstantsErrorCode.VOUCHER_ERROR);
                }else {
                    totalDiscount = valueVoucher(voucherResponse,orderRequestProduct.getTotalAmount());
                }
            }else {
                totalDiscount =valueVoucher(voucherResponse,orderRequestProduct.getTotalAmount()) + orderRequestProduct.getTotalDiscount();
            }
        } else {
            totalDiscount = orderRequestProduct.getTotalDiscount();
        }
        String orderStratus = "";
        if (request.getOrderMethod().equals(OrderMethod.PAYMENT.name()) || request.getOrderType().equals(OrderType.COUNTER.name())) {
            productEntities.stream().filter(productEntity -> {
                request.getProductDetails().stream().filter(orderEntity -> {
                    if (orderEntity.getProductId().equals(productEntity.getId())) {
                        System.out.println(orderEntity.getQuantity() +"-----"+ productEntity.getQuantity());
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
            orderStratus = OrderStatus.PAID.name();
        } else {
            orderStratus = OrderStatus.VERIFY.name();
        }
        // lưu hóa đơn.
        OrdersEntity ordersEntity = new OrdersEntity().builder()
                .id(UUID.randomUUID().toString())
                .customer(MODEL_MAPPER.map(customerInfoResponse, CustomerEntity.class))
                .voucher(Objects.isNull(voucherResponse) ? null : MODEL_MAPPER.map(voucherResponse, VoucherEntity.class))
                .ipAddress(request.getIpAddress())
                .orderType(request.getOrderType())
                .recipientName(Objects.nonNull(request.getRecipientName()) ? request.getCustomer().getFullName() : request.getRecipientName())
                .recipientPhone(Objects.nonNull(request.getRecipientPhone()) ? request.getCustomer().getPhoneNumber() : request.getRecipientPhone())
                .recipientAddress(Objects.nonNull(request.getRecipientAddress()) ? request.getCustomer().getAddress() : request.getRecipientAddress())
                .totalAmount(totalDiscount)
                .itemQuantity(itemQuantity)
                .tax(orderRequestProduct.getTotalAmount())
                .createBy("Quang")
                .paymentMethod(request.getPaymentMethod())
                .createDate(new Date())
                .modifierDate(new Date())
                .orderDate(new Date())
                .note(request.getNote())
                .deleteFlag(false)
                .salesPerson(request.getOrderType().equals(OrderType.COUNTER.name()) ? "Quang" : null)
                .status(OrderShipping.NOTSHIPPED.name())
                .transactionStatus(orderStratus)
                .build();
        System.out.println(ordersEntity);
        OrdersEntity orderSave = ordersRepo.save(ordersEntity);
        List<OrderdetailEntity> orderdetailEntities = new ArrayList<>();
        orderRequestProduct.getProductDetailsRequests().stream().filter(e -> {
                    OrderdetailEntity orderdetailEntity = new OrderdetailEntity();
                    orderdetailEntity.setId(UUID.randomUUID().toString());
                    orderdetailEntity.setOrders(orderSave);
                    orderdetailEntity.setNote(e.getNote());
                    orderdetailEntity.setDeleteFlag(false);
                    orderdetailEntity.setCreateDate(new Date());
                    orderdetailEntity.setModifierDate(new Date());
                    orderdetailEntity.setDiscount(e.getDiscount());
                    orderdetailEntity.setQuantity(e.getQuantity());
                    orderdetailEntity.setProduct(new ProductEntity().toBuilder().id(e.getProductId()).build());
                    orderdetailEntities.add(orderdetailEntity);
                    return false;
                }
        ).collect(Collectors.toList());
        try {
            orderDetailsRepo.saveAll(orderdetailEntities);
            productRepo.saveAll(productEntities);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(CommonStatus.FAIL.name());
        }

        return response;
    }

    private int valueVoucher(VoucherResponse voucherResponse, int sumMoney) {
        if(voucherResponse.getVoucherTypeDiscount().equals("%")){
            return (int) (sumMoney*voucherResponse.getVoucherDiscount());
        }
        return voucherResponse.getVoucherDiscount().intValue();
    }

    private boolean checkPermissionVoucher( List<ProductEntity> productEntities) {
        //todo đợi service khuyến mại kiểm tra nếu tồn tại khuyến mại sản phẩm
        Optional<ProductEntity> optionalProduct = productEntities.stream().filter(item-> item.getQuantity()==1000).findFirst();
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
        List<OrderdetailEntity> orderDetailEntities = orderDetailsRepo.findAllByIdNotInAndOrdersIdAndDeleteFlagFalse(ids, requests.getOrderId());
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
                                    orderDetailsSaves.add(e);
                                }
                            }
                            if (Objects.isNull(item.getId())) {
                                String id = UUID.randomUUID().toString();
                                item.setId(id);
                                OrderdetailEntity orderdetailEntity = new OrderdetailEntity();
                                orderdetailEntity.setQuantity(item.getQuantity());
                                orderdetailEntity.setId(id);
                                orderdetailEntity.setDeleteFlag(false);
                                orderdetailEntity.setCreateDate(new Date());
                                orderdetailEntity.setModifierDate(new Date());
                                orderdetailEntity.setNote(item.getNote());
                                orderdetailEntity.setDiscount(60000);
                                orderdetailEntity.setQuantity(item.getQuantity());
                                OrdersEntity ordersEntity = new OrdersEntity();
                                ordersEntity.setId(requests.getOrderId());
                                orderdetailEntity.setOrders(ordersEntity);
                                //todo thiếu discout
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
        orders.setModifierBy("Quang");
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
        OrdersEntity orders = ordersRepo.findByIdAndDeleteFlagFalse(request.getId());
        if (Objects.nonNull(orders)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        // lấy danh sách order details
        try {
            if (OrderMethod.CASH.name().equals(orders.getOrderType())) {
                List<OrderdetailEntity> orderDetailEntities = orderDetailsRepo.findAllByOrdersIdAndDeleteFlag(request.getId(), false);
                // kiểm tra xem danh sách sản phẩm có tồn tại không
                List<String> productId = orderDetailEntities.stream().map(item -> item.getProduct().getId()).collect(Collectors.toList());
                List<ProductEntity> productEntities = productRepo.findAllByIdInAndDeleteFlagFalse(productId);
                if (CollectionUtils.isEmpty(productEntities) || productEntities.size() != orderDetailEntities.size()) {
                    throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
                }
                // kiểm tra số lượng trong kho có tồn tại không.
                // todo bắn messga ra sau
                productEntities.stream().filter(productEntity -> {
                    orderDetailEntities.stream().filter(orderEntity -> {
                        if (orderEntity.getId().equals(productEntity.getId())) {
                            if (orderEntity.getQuantity() > productEntity.getQuantity()) {
                                throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
                            } else {
                                productEntity.setQuantity(productEntity.getQuantity() - orderEntity.getQuantity());
                            }
                        }
                        return false;
                    }).collect(Collectors.toList());
                    return false;
                }).collect(Collectors.toList());
                productRepo.saveAll(productEntities);
            }
            // kiểm tra xem người dùng có thay đổi người nhận hàng không thiếu ngày nhận hàng
            if (!orders.equals(request)) {
                orders.setRecipientName(request.getRecipientName());
                orders.setRecipientPhone(request.getRecipientPhone());
                orders.setRecipientAddress(request.getRecipientAddress());
            }
            orders.setSalesPerson("Quang");
            orders.setTransactionStatus(OrderStatus.CONFIRM.name());
            orders.setModifierDate(new Date());
            ordersRepo.save(orders);
        } catch (Exception e) {
            response.setStatus(CommonStatus.FAIL.name());
        }

        return response;
    }

    @Override
    public OrderResponse confirmExportOrder(ConfirmExportOrderRequest request) {
        OrderResponse orderResponse = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        OrdersEntity orders = ordersRepo.findByIdAndDeleteFlagFalse(request.getId());
        if (Objects.isNull(orders)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        List<String> ids = request.getProductDetailsRequests().stream().map(OrderProductDetailsRequest::getProductId).collect(Collectors.toList());
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
            orders.setStockKeeper("Quang");
            orders.setModifierDate(new Date());
        } catch (Exception e) {
            orderResponse.setStatus(CommonStatus.FAIL.name());
        }
        return orderResponse;
    }

    public OrderResponse payTheBill(CustomerPayTheBillRequest request) {
        // todo viết tiếp chưa đủ
        OrderResponse orderResponse = new OrderResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        OrdersEntity orders = ordersRepo.findByIdAndDeleteFlagFalse(request.getId());
        if (Objects.isNull(orders)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        orders.setAccounting("quang");
        orders.setModifierDate(new Date());
        orders.setTransactionStatus(OrderStatus.DONE.name());
        try {

        } catch (Exception e) {

        }
        return null;
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
        OrderdetailEntity orderdetailEntity = orderDetailsRepo.findByOrdersIdAndProductId(request.getOrderId(), request.getProductId());
        if (Objects.isNull(orderdetailEntity)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        List<String> imeiList = convertToListImei(orderdetailEntity.getImei());
        if (imeiList.size() < request.getImei().size()) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        imeiList = imeiList.stream().filter(item -> !checkImei(item, request.getImei())).collect(Collectors.toList());
        orderdetailEntity.setImei(mapperImei(imeiList));
        orderdetailEntity.setQuantity(orderdetailEntity.getQuantity() - request.getImei().size());
        try {
            orderDetailsRepo.save(orderdetailEntity);
            List<OrderdetailEntity> orderdetailEntities = orderDetailsRepo.findAllByOrdersIdAndDeleteFlag(request.getOrderId(), false);
            editOrderEntity(orderdetailEntities, orders);
            orders.setModifierDate(new Date());
            orders.setModifierBy("Quang");
            ordersRepo.save(orders);
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
        OrdersEntity orders = ordersRepo.findByIdAndDeleteFlagFalse(id);
        try {
            if (!(OrderMethod.CASH.name().equals(orders.getPaymentMethod()) && OrderType.ONLINE.name().equals(orders.getOrderType()))) {
                List<ProductEntity> productEntitiesSave = new ArrayList<>();
                List<OrderdetailEntity> orderDetailEntities = orderDetailsRepo.findAllByOrdersIdAndDeleteFlag(orders.getId(), false);
                List<String> ids = orderDetailEntities.stream().map(item -> item.getId()).collect(Collectors.toList());
                List<ProductEntity> productEntities = productRepo.findAllByIdInAndDeleteFlagFalse(ids);
                productEntities.stream().filter(product -> {
                    orderDetailEntities.stream().filter(orderDetailEntity -> {
                        if (orderDetailEntity.getId().equals(product.getId())) {
                            product.setQuantity(product.getQuantity() + orderDetailEntity.getQuantity());
                            productEntitiesSave.add(product);
                        }
                        return false;
                    }).collect(Collectors.toList());
                    return false;
                }).collect(Collectors.toList());
                productRepo.saveAll(productEntitiesSave);
            }
            if (Objects.isNull(orders)) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
            }
            orders.setDeleteFlag(true);
            orders.setModifierDate(new Date());
            orders.setModifierBy("Quang");

            ordersRepo.save(orders);
        } catch (Exception e) {
            e.printStackTrace();
            orderResponse.setStatus(CommonStatus.FAIL.name());
        }
        return orderResponse;
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
                            //todo check voucher hay khuyến mại tiền mặt ở đây
                            item.setDiscount((int) (e.getPrice() * item.getQuantity() * 0.1));
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

    private VoucherResponse getInfoVoucher(String id) {
        VoucherResponse voucherResponse = voucherService.getById(id);
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
}