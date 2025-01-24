/*
 * package com.example.model;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
 * 
 * @Entity public class PaymentDetails {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 * 
 * private String transactionId; private String payerId; private String
 * paymentId; private Double amount; private String currency; private String
 * status; public PaymentDetails() { super(); // TODO Auto-generated constructor
 * stub } public PaymentDetails(Long id, String transactionId, String payerId,
 * String paymentId, Double amount, String currency, String status) { super();
 * this.id = id; this.transactionId = transactionId; this.payerId = payerId;
 * this.paymentId = paymentId; this.amount = amount; this.currency = currency;
 * this.status = status; } public Long getId() { return id; } public String
 * getTransactionId() { return transactionId; } public String getPayerId() {
 * return payerId; } public String getPaymentId() { return paymentId; } public
 * Double getAmount() { return amount; } public String getCurrency() { return
 * currency; } public String getStatus() { return status; } public void
 * setId(Long id) { this.id = id; } public void setTransactionId(String
 * transactionId) { this.transactionId = transactionId; } public void
 * setPayerId(String payerId) { this.payerId = payerId; } public void
 * setPaymentId(String paymentId) { this.paymentId = paymentId; } public void
 * setAmount(Double amount) { this.amount = amount; } public void
 * setCurrency(String currency) { this.currency = currency; } public void
 * setStatus(String status) { this.status = status; }
 * 
 * @Override public String toString() { return "PaymentDetails [id=" + id +
 * ", transactionId=" + transactionId + ", payerId=" + payerId + ", paymentId="
 * + paymentId + ", amount=" + amount + ", currency=" + currency + ", status=" +
 * status + "]"; }
 * 
 * }
 */