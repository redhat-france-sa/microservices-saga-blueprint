package com.redhat.microsaga.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.microsaga.rest.RemoteServices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ShippingService {
    @Inject
    @RestClient
    RemoteServices remoteServices;

    //@Fallback(fallbackMethod = "")
    public String scheduleShipping(String order) {
        return remoteServices.scheduleShipping(order);
    }
    public String cancelShipping(String shippingId) {
        return remoteServices.cancelShipping(shippingId);
    }

}
