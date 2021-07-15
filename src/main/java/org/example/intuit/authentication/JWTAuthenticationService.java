package org.example.intuit.authentication;

import org.springframework.stereotype.Component;

import java.util.HashMap;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

import static java.util.Collections.emptyList;

@Component
public class JWTAuthenticationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationService.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token}")
    private String token_header;

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(token_header);
        if (token != null) {

            try {
                Algorithm algorithm = Algorithm.HMAC512(this.secret);

                DecodedJWT decodedToken = JWT.decode(token);
                if (decodedToken.getAlgorithm().equals("HS512") && decodedToken.getClaims().containsKey("id") && decodedToken.getClaims().containsKey("email")) {

                    String email = decodedToken.getClaim("email").asString();

                    String id = decodedToken.getClaim("id").asString();

                    JWTVerifier verifier = JWT.require(algorithm).
                            withClaim("id", id).
                            withClaim("email", email).
                            build();

                    verifier.verify(token);

                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(new JWTPrincipal(getUserDetails(id, email)), null, emptyList());
                    MDC.put("id", id);
                    return authentication;

                }

            } catch (JWTVerificationException e) {
                LOGGER.error(e.getMessage());
                return null;
            }

        }

        return null;

    }

    private HashMap getUserDetails(String id, String email) {
        HashMap<String, Object> userDetails = new HashMap<>();
        userDetails.put("id", id);
        userDetails.put("email", email);
        return userDetails;
    }
}
