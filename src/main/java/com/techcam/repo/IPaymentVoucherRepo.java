package com.techcam.repo;

import com.techcam.entity.PaymentVoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/23/2022
 * Project_name: Tech-cam
 */

public interface IPaymentVoucherRepo extends JpaRepository<PaymentVoucherEntity,String> {
}
