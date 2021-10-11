package com.redhat.microsaga.rest;


import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.redhat.microsaga.model.Payment;
import com.redhat.microsaga.model.PaymentInfo;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface PaymentRemoteServices {
    @PUT
    @Path("/payment/{id}/pay")
    @Produces("application/json")
    String payment(@PathParam("id") String id,  String paymentCardId);

    @PUT
    @Path("/payment/{id}/cancel")
    @Produces("application/json")
    String cancelPayment(@PathParam("id") String id, String paymentCardId);

}
