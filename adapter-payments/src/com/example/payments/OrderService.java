package com.example.payments;

import java.util.Objects;

public class OrderService {
    private final PaymentGateway gateway;

    public OrderService(PaymentGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway, "gateway");
    }

    /**
     * Create an order by charging the given customer the specified amount.
     * @return transaction id from the payment gateway
     */
    public String createOrder(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        return gateway.charge(customerId, amountCents);
    }
}