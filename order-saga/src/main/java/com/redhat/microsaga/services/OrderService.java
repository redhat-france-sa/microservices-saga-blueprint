package com.redhat.microsaga.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.microsaga.model.Order;
import com.redhat.microsaga.rest.OrderRemoteServices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class OrderService {
    @Inject
    @RestClient
    OrderRemoteServices orderRemoteServices;

       //@Fallback(fallbackMethod = "")
    public String createOrder(Order order) {
        return orderRemoteServices.createOrder(order);
    }

    public String failOrder(String orderId) {
        return orderRemoteServices.failOrder(orderId);
    }
}
