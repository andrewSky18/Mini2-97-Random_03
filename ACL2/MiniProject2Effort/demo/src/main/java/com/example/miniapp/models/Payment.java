package com.example.miniapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String paymentMethod;

    private Boolean paymentStatus;

    @OneToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;

    // Default constructor
    public Payment() {
    }

    // Constructor with required fields
    public Payment(Double amount, String paymentMethod, Boolean paymentStatus) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    /**
     * Logical constructor for testing with all fields
     * @param id payment ID (can be null for new payments)
     * @param amount payment amount
     * @param paymentMethod method of payment (e.g., CREDIT_CARD, CASH)
     * @param paymentStatus payment status (true=completed, false=pending)
     * @param trip the associated trip (can be null)
     */
    public Payment(Long id, Double amount, String paymentMethod, Boolean paymentStatus, Trip trip) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.trip = trip;
    }

    // Constructor for tests
    public Payment(Long id, Double amount, String paymentMethod, Boolean paymentStatus) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != null ? !id.equals(payment.id) : payment.id != null) return false;
        if (amount != null ? !amount.equals(payment.amount) : payment.amount != null) return false;
        if (paymentMethod != null ? !paymentMethod.equals(payment.paymentMethod) : payment.paymentMethod != null)
            return false;
        return paymentStatus != null ? paymentStatus.equals(payment.paymentStatus) : payment.paymentStatus == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (paymentStatus != null ? paymentStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
