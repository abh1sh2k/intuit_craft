package org.example.intuit.authentication;

import java.security.Principal;
import java.util.HashMap;

public class JWTPrincipal implements Principal {

    private Driver driver;

    public JWTPrincipal(HashMap<String, Object> userDetails) {
        this.driver = new Driver();
        this.driver.setUserId((String) userDetails.get("id"));
        this.driver.setEmail((String)userDetails.get("email"));
    }

    public Driver getUser() {
        return this.driver;
    }

    @Override
    public String getName() {
        return null;
    }
}

