package com.techcam.repo;

import com.techcam.entity.GoodsreceiptdetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 11:23 PM
 * Project_name : tech-cam
 */

public interface IGoodsreceiptdetailRepo extends JpaRepository<GoodsreceiptdetailEntity, String> {
    List<GoodsreceiptdetailEntity> findAllByGoodsReceiptIdAndDeleteFlagIsFalse(String invoiceId);

    List<GoodsreceiptdetailEntity> findAllByGoodsReceiptIdAndQuantityAndPriceAndDeleteFlagIsFalseAndProductId(String invoiceId, int quantity, BigDecimal price, String productId);

    List<GoodsreceiptdetailEntity> findAllByGoodsReceiptIdAndProductIdAndDeleteFlagIsFalse(String invoiceCode, String productId);

    List<GoodsreceiptdetailEntity> findAllByProductIdAndDeleteFlagIsFalse(String id);
}
