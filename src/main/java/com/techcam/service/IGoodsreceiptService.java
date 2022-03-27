package com.techcam.service;

import com.techcam.dto.request.invoice.InvoiceRequest;
import com.techcam.dto.response.invoice.InvoiceDetailResponse;
import com.techcam.dto.response.invoice.InvoiceResponse;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 10:05 PM
 * Project_name : tech-cam
 */

public interface IGoodsreceiptService {

    List<InvoiceResponse> findAllByInvoiceCode(String invoiceCode);

    InvoiceResponse getByInvoiceId(String id);

    String deleteById(String id);

    List<InvoiceResponse> findAllInvoice();

    List<InvoiceDetailResponse> findAllInvoiceDetailByInvoiceId(String invoiceId);

    String createInvoice(InvoiceRequest invoiceRequest);

    String updateInvoice(InvoiceRequest invoiceRequest);
}
