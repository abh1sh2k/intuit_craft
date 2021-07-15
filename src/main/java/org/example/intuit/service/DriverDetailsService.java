package org.example.intuit.service;

import org.example.intuit.model.DriverDetails;
import org.example.intuit.model.DriverStatus;
import org.example.intuit.model.SignUpRequest;
import org.example.intuit.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DriverDetailsService {
    @Autowired
    SignUpRepository signUpRepository;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Autowired
    KafkaProducerService kafkaProducerService;

    public DriverDetails getDriverDetails(String emailId) {
        DriverDetails driverDetails = signUpRepository.findByEmail(emailId);
        return driverDetails;
    }

    public DriverDetails signUp(SignUpRequest signUpRequest) {
        DriverDetails driverDetails = new DriverDetails();
        driverDetails.setEmail(signUpRequest.getEmail());
        driverDetails.setPassword(signUpRequest.getPassword());

        kafkaProducerService.send(driverDetails.get_id(), "signedp_complete", kafkaTopic);
        return signUpRepository.insert(driverDetails);
    }

    public void markReady(DriverDetails driverDetails) {
        driverDetails.setDriverStatus(DriverStatus.READY);
        signUpRepository.save(driverDetails);
    }
}
