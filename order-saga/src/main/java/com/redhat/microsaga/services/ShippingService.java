package com.redhat.microsaga.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.microsaga.model.Order;
import com.redhat.microsaga.rest.ShippingRemoteServices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ShippingService {
    @Inject
    @RestClient
    ShippingRemoteServices shippingRemoteServices;

    //@Fallback(fallbackMethod = "")
    public String scheduleShipping(Order order) {
        return shippingRemoteServices.scheduleShipping(order);
    }
    public String cancelShipping(String shippingId) {
        return shippingRemoteServices.cancelShipping(shippingId);
    }

}
