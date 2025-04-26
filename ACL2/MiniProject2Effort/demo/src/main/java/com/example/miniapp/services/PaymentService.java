package com.example.miniapp.services;

import com.example.miniapp.models.Payment;
import com.example.miniapp.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment addPayment(Payment payment) {
        if (payment == null) {
            throw new InvalidDataAccessApiUsageException("Payment must not be null");
        }
        if (payment.getAmount() == null || payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid payment amount");
        }
        if (payment.getPaymentMethod() == null || payment.getPaymentMethod().isBlank()) {
            throw new IllegalArgumentException("Payment method is required");
        }
        if (payment.getPaymentStatus() == null) {
            throw new IllegalArgumentException("Payment status must be specified");
        }

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        if (id == null) return null;
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        if (updatedPayment == null) {
            throw new InvalidDataAccessApiUsageException("Payment must not be null");
        }
        if (!paymentRepository.existsById(id)) {
            return null;
        }

        updatedPayment.setId(id);
        return paymentRepository.save(updatedPayment);
    }

    public void deletePayment(Long id) {
        if (id != null && paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        }
    }

    public List<Payment> findPaymentsByTripId(Long tripId) {
        if (tripId == null) {
            throw new IllegalArgumentException("Trip ID must not be null");
        }
        return paymentRepository.findByTripId(tripId);
    }

    public List<Payment> findByAmountThreshold(Double threshold) {
        if (threshold == null || threshold < 0) {
            throw new IllegalArgumentException("Invalid amount threshold");
        }
        return paymentRepository.findByAmountGreaterThan(threshold);
    }
}