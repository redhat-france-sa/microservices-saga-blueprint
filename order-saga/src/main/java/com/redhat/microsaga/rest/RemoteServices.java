package com.redhat.microsaga.rest;


import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.redhat.microsaga.model.Order;
import com.redhat.microsaga.model.ProductItem;
import com.redhat.microsaga.model.Payment;
import com.redhat.microsaga.model.PaymentInfo;

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
    String cancelPayment(@PathParam("id") String paymentId);


    @POST
    @Path("/shipping")
    @Produces("application/json")
    String scheduleShipping(@PathParam("order") Order order);

    @DELETE
    @Path("/shipping/{id}")
    @Produces("application/json")
    String cancelShipping(@PathParam("schippingId") String schippingId);

    @POST
    @Path("/order")
    @Produces("application/json")
    String createOrder(@PathParam("order") Order order);

    @PUT
    @Path("/order/{id}/fail")
    @Produces("application/json")
    String failOrder(@PathParam("id") String orderId);
   
    
}
