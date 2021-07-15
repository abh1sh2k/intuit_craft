package org.example.intuit.controller;

import org.example.intuit.authentication.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public class BaseController<T> {
    @Autowired
    protected AuthHelper authHelper;

    public ResponseEntity<T> success(T t, HttpStatus status) {
        return ResponseEntity.ok(t);
    }

    public ResponseEntity<T> success(T t, HttpStatus status, @Nullable String message) {
        return ResponseEntity.ok(t);
    }

    public ResponseEntity<T> empty(HttpStatus status, @Nullable String message) {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<T> failure(T t, HttpStatus status, @Nullable String message) {
        return ResponseEntity.status(status).body(t);
    }

    public ResponseEntity<T> failure(T t, HttpStatus status) {
        return ResponseEntity.status(status).body(t);
    }
}
