package com.zlimbu.mcrsr.controller;

import com.zlimbu.mcrsr.model.Coupon;
import com.zlimbu.mcrsr.repos.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

    @Autowired
    CouponRepo repo;

    @PostMapping("/coupons")
    public Coupon create(@RequestBody Coupon coupon){
        return this.repo.save(coupon);
    }
    @GetMapping("/coupons/{code}")
    public Coupon getCoupon(@PathVariable(value = "code") String code){
        return repo.findByCode(code);
    }
}
