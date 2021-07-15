package org.example.intuit.model;

public enum DriverStatus {
    SIGNED(0), READY(1);
    int id;
    DriverStatus(int id){
        this.id = id;
    }
}
