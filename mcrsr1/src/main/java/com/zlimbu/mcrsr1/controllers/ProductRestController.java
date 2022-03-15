package com.zlimbu.mcrsr1.controllers;


import com.zlimbu.mcrsr1.dto.Coupon;
import com.zlimbu.mcrsr1.model.Product;
import com.zlimbu.mcrsr1.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("productapi")
public class ProductRestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductRepo repo;

    @Value("${couponService.url}")
    private String couponServiceURL;

    @PostMapping("/products")
    Product create(@RequestBody Product product){
        Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }
}
