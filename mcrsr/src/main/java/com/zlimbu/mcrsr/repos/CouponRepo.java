package com.zlimbu.mcrsr.repos;

import com.zlimbu.mcrsr.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
}
