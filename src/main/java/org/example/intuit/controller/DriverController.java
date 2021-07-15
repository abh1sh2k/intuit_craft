package org.example.intuit.controller;

import org.example.intuit.authentication.AuthHelper;
import org.example.intuit.authentication.Driver;
import org.example.intuit.constants.ResourceUrl;
import org.example.intuit.model.DriverDetails;
import org.example.intuit.service.DriverDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController extends BaseController{

    @Autowired
    AuthHelper authHelper;
    @Autowired
    DriverDetailsService driverDetailsService;


    @GetMapping(ResourceUrl.READY)
    ResponseEntity ready() {
        Driver driver = authHelper.getAuthenticatedUser();

        DriverDetails driverDetails  = driverDetailsService.getDriverDetails(driver.getEmail());

        if (driverDetails == null)
            return failure("user does not exist", HttpStatus.FORBIDDEN);


        driverDetailsService.markReady(driverDetails);
        return success("success", HttpStatus.OK, null);

    }
}
