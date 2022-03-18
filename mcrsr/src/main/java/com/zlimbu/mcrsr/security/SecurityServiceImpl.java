package com.zlimbu.mcrsr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

//For log in
@Service
public class SecurityServiceImpl implements SecurityService{

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager; //It is in WebSecurityConfig
    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        Authentication authenticate = this.authenticationManager.authenticate(token);
        boolean result = token.isAuthenticated();
        if(result){
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        return result;
    }
}
