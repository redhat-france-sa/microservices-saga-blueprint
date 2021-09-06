package com.redhat.microsaga.rest;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.redhat.microsaga.model.order.Order;
import com.redhat.microsaga.model.order.ProductItem;
import com.redhat.microsaga.model.payment.Payment;
import com.redhat.microsaga.model.payment.PaymentInfo;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

public interface RemoteServices {

    @PUT
    @Path("/stock/{id}/reserve")
    @Produces("application/json")
    String reserveStock(@PathParam("productitem")  ProductItem productItem);

    @PUT
    @Path("/stock/{id}/release")
    @Produces("application/json")
    String releaseStock(@PathParam("productitem") ProductItem productItem);

    @POST
    @Path("/payment")
    @Produces("application/json")
    String payment(@PathParam("paymentInfo") PaymentInfo paymentInfo);

    @PUT
    @Path("/payment/{id}/cancel")
    @Produces("application/json")
    String cancelPayment(@PathParam("payment") Payment payment);


    @POST
    @Path("/shipping")
    @Produces("application/json")
    String scheduleShipping(@PathParam("order") Order order);

    @DELETE
    @Path("/shipping/{id}")
    @Produces("application/json")
    String cancelShipping(@PathParam("schippingId") String schippingId);
   
    
}
