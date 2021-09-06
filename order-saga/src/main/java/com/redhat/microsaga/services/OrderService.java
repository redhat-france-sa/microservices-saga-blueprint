package com.redhat.microsaga.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.microsaga.model.Order;
import com.redhat.microsaga.rest.RemoteServices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class OrderService {
    @Inject
    @RestClient
    RemoteServices remoteServices;

       //@Fallback(fallbackMethod = "")
    public String createOrder(Order order) {
        return remoteServices.createOrder(order);
    }

    public String failOrder(String orderId) {
        return remoteServices.failOrder(orderId);
    }
}
