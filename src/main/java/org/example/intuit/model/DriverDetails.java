package org.example.intuit.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "driver.details")
public class DriverDetails {
    @Id
    protected String _id;
    String email ;
    String password;

    DriverStatus driverStatus = DriverStatus.SIGNED;

}
