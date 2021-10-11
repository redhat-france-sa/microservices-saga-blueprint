package com.redhat.microsaga.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.microsaga.rest.ShippingRemoteServices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ShippingService {
    @Inject
    @RestClient
    ShippingRemoteServices shippingRemoteServices;

    //@Fallback(fallbackMethod = "")
    public String scheduleShipping(String id, String schippingId) {
        return shippingRemoteServices.scheduleShipping(id,  schippingId);
    }
    public String cancelShipping(String id, String schippingId) {
        return shippingRemoteServices.cancelShipping(id,  schippingId);
    }

}
