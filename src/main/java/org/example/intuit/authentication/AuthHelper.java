package org.example.intuit.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.example.intuit.model.DriverDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthHelper {
    @Value("${jwt.secret}")
    private String secret;

    public Driver getAuthenticatedUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        assert context != null;
        Authentication authentication = context.getAuthentication();
        assert authentication != null;
        JWTPrincipal principal = (JWTPrincipal) authentication.getPrincipal();
        return principal.getUser();
    }

    public String getJWTToken(DriverDetails driverDetails){
        return JWT.create().withClaim("email", driverDetails.getEmail())
                .withClaim("id", driverDetails.get_id()).
                        sign(Algorithm.HMAC512(this.secret));
    }
}
