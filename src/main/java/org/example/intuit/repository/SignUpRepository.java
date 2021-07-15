package org.example.intuit.repository;

import org.example.intuit.model.DriverDetails;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SignUpRepository extends MongoRepository<DriverDetails, String>{
    DriverDetails findByEmail(String  emailId);
}