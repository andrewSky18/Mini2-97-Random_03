package com.example.miniapp.controllers;

import com.example.miniapp.models.Payment;
import com.example.miniapp.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/addPayment")
    public ResponseEntity<?> addPayment(@RequestBody Payment payment) {
        try {
            return ResponseEntity.ok(paymentService.addPayment(payment));
        } catch (InvalidDataAccessApiUsageException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/allPayments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        try {
            Payment updated = paymentService.updatePayment(id, payment);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found.");
            }
            return ResponseEntity.ok(updated);
        } catch (InvalidDataAccessApiUsageException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Payment deleted successfully.");
    }

    @GetMapping("/findByTripId")
    public ResponseEntity<?> findPaymentsByTripId(@RequestParam Long tripId) {
        try {
            return ResponseEntity.ok(paymentService.findPaymentsByTripId(tripId));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/findByAmountThreshold")
    public ResponseEntity<?> findPaymentsWithAmountGreaterThan(@RequestParam Double threshold) {
        try {
            return ResponseEntity.ok(paymentService.findByAmountThreshold(threshold));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }
}