package com.example.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.PaypalService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/paypal")
public class WebhookController {
     
	@Autowired
	private PaypalService paypalservice;
    private final ObjectMapper objectMapper;

    public WebhookController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/webhook")
    public Map<String, Object> handleWebhook(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Log the raw payload for debugging
            System.out.println("Webhook received: " + objectMapper.writeValueAsString(payload));

            // Check the event type
            String eventType = (String) payload.get("event_type");
            if ("PAYMENT.SALE.COMPLETED".equals(eventType)) {
                // Extract resource data
                Map<String, Object> resource = (Map<String, Object>) payload.get("resource");
               
                // Populate response with details
                response.put("status", "Payment Success");
                response.put("paymentId", resource.get("id"));
                response.put("state", resource.get("state"));
                response.put("createTime", resource.get("create_time"));

                // Extract amount details
                Map<String, Object> amount = (Map<String, Object>) resource.get("amount");
                response.put("amount", amount.get("total"));
                response.put("currency", amount.get("currency"));

                // Add description if available
                String description = (String) resource.getOrDefault("invoice_number", "No description available");
                response.put("description", description);

                // Log the formatted response
                System.out.println("Formatted Webhook Data: " + response);
            } else {
                response.put("status", "Unhandled event type: " + eventType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "Webhook processing failed");
            response.put("error", e.getMessage());
        }
        return response;
    }
}


