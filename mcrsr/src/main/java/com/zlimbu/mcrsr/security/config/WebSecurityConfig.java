package com.zlimbu.mcrsr.security.config;

import com.zlimbu.mcrsr.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin(); form login is not needed since there is a login form/html
        http.authorizeRequests().mvcMatchers(HttpMethod.GET,"/couponapi/coupons/{code:^[A-Z]*$}","/index","/couponDetails","/showGetCoupon","/getCoupon").hasAnyRole("USER","ADMIN")
                .mvcMatchers(HttpMethod.GET, "/createCoupon","/createResponse","/showCreateCoupon").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/getCoupon").hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.POST, "/couponapi/coupons","/saveCoupon","/getCoupon").hasRole("ADMIN")
                .mvcMatchers("/","/login","/logout","/showReg","/registerUser").permitAll().anyRequest().denyAll().and().csrf().disable().logout().logoutSuccessUrl("/").invalidateHttpSession(false);

//      Everything after .logout is for logging out
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //needed for log in
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
