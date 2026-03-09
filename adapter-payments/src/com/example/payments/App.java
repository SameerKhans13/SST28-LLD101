package com.example.payments;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, PaymentGateway> registry = new HashMap<>();
        registry.put("fastpay", new FastPayAdapter(new FastPayClient()));
        registry.put("safecash", new SafeCashAdapter(new SafeCashClient()));

        // create a service for each provider and exercise it
        for (Map.Entry<String, PaymentGateway> entry : registry.entrySet()) {
            String name = entry.getKey();
            PaymentGateway gateway = entry.getValue();
            OrderService svc = new OrderService(gateway);
            String tx = svc.createOrder("cust-" + name, 1299);
            System.out.println(name + " -> " + tx);
        }
    }
}