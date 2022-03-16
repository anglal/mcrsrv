package com.zlimbu.mcrsr.controller;

import com.zlimbu.mcrsr.model.Coupon;
import com.zlimbu.mcrsr.repos.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CouponController {

    @Autowired
    private CouponRepo repo;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/showCreateCoupon")
    public String showCreateCoupon(){
        return "createCoupon";
    }

    @PostMapping("/saveCoupon")
    public String saveCoupon(Coupon coupon){
        this.repo.save(coupon);
        return "createResponse";
    }

    @GetMapping("/showGetCoupon")
    public String showGetCoupon(){
        return "showGetCoupon";
    }

    @PostMapping("/getCoupon")
    public ModelAndView getCoupon(String code){
        ModelAndView mav = new ModelAndView("couponDetails");
        mav.addObject(repo.findByCode(code));
        return mav;
    }
}
