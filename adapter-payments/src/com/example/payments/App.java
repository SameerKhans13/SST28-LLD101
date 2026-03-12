package com.example.payments;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Earlier, our OrderService was tightly coupled with specific SDKs like FastPay and SafeCash.
 * If we wanted to add a new payment provider, we had to change the core OrderService code (violating OCP open/closed principal).
 * 
 * Solution: We used the Adapter Design Pattern here. We created a common PaymentGateway interface.
 * The adapters (FastPayAdapter & SafeCashAdapter) act as a bridge between our system and the outside SDKs.
 * Now, OrderService only talks to the interface, which makes our design clean and flexible.
 */
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