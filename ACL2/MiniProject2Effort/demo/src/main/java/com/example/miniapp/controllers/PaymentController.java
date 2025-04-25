package com.example.miniapp.controllers;

import com.example.miniapp.models.Payment;
import com.example.miniapp.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);
    }

    @GetMapping("/allPayments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/update/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "Payment deleted successfully.";
    }

    @GetMapping("/findByTripId")
    public List<Payment> findPaymentsByTripId(@RequestParam Long tripId) {
        return paymentService.findPaymentsByTripId(tripId);
    }

    @GetMapping("/findByAmountThreshold")
    public List<Payment> findByAmountThreshold(@RequestParam Double threshold) {
        return paymentService.findByAmountThreshold(threshold);
    }
}
