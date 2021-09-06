package com.redhat.microsaga.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.microsaga.rest.RemoteServices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class PaymentService {
    @Inject
    @RestClient
    RemoteServices remoteServices;

    //@Fallback(fallbackMethod = "")
    public String payment(String paymentInfo) {
        return remoteServices.payment(paymentInfo);
    }

    public String cancelPayment(String payment) {
        return remoteServices.cancelPayment(payment);
    }
    
    

}
