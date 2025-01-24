package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@RestController // Use RestController for API calls from Postman
public class PaymentController {

    @Autowired
    private PaypalService paypalService;

    // Endpoint to create a payment (called from backend/Postman)
    @PostMapping("/payment/create")
    public String createPayment() throws Exception {
        try {
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";

            // Call the service to create the payment
            Payment payment = paypalService.createPayment(
                20.0, // Amount
                "USD", // Currency
                "paypal", // Payment method
                "sale", // Intent
                "Payment Description", // Payment description
                cancelUrl, // Cancel URL
                successUrl  // Success URL
            );

            
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return link.getHref(); // Return approval URL for redirection
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return "Error occurred while creating payment.";
        }
        return "Error: Payment approval link not found.";
    }

  
    @GetMapping("/payment/success")
    public String paymentSuccess(@RequestParam String paymentId,
                                 @RequestParam("PayerID") String payerId) throws Exception {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "Payment Success";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "Payment Failed";
    }

    // Endpoint for payment cancellation
    @GetMapping("/payment/cancel")
    public String paymentCancel() {
        return "Payment Cancelled";
    }

    // Endpoint for payment error
    @GetMapping("/payment/error")
    public String paymentError() {
        return "Payment Error";
    }
}
