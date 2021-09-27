package com.redhat.microsaga.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.microsaga.model.PaymentInfo;
import com.redhat.microsaga.rest.PaymentRemoteServices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class PaymentService {
    @Inject
    @RestClient
    PaymentRemoteServices paymentRemoteServices;

    //@Fallback(fallbackMethod = "")
    public String payment(PaymentInfo paymentInfo) {
        return paymentRemoteServices.payment(paymentInfo);
    }

    public String cancelPayment(String paymentId) {
        return paymentRemoteServices.cancelPayment(paymentId);
    }
}
