package org.example.intuit.controller;

import org.example.intuit.authentication.AuthHelper;
import org.example.intuit.constants.ResourceUrl;
import org.example.intuit.model.DriverDetails;
import org.example.intuit.model.SignUpRequest;
import org.example.intuit.service.DriverDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController extends BaseController {

    @Autowired
    AuthHelper authHelper;
    @Autowired
    DriverDetailsService driverDetailsService;


    @PostMapping(ResourceUrl.LOGIN)
    ResponseEntity login(@RequestBody SignUpRequest signUpRequest) {

        DriverDetails driverDetails = driverDetailsService.getDriverDetails(signUpRequest.getEmail());

        if (driverDetails == null)
            return failure("user does not exist", HttpStatus.FORBIDDEN);

        String token = authHelper.getJWTToken(driverDetails);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("id", driverDetails.get_id());
        return success(response, HttpStatus.OK, null);

    }


}
