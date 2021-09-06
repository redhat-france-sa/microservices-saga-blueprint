package com.redhat.microsaga.rest;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

public interface RemoteServices {

    @PUT
    @Path("/stock/{id}/reserve")
    @Produces("application/json")
    String reserveStock(@PathParam("productitems")  ArrayList<String> productItems);

    @PUT
    @Path("/stock/{id}/release")
    @Produces("application/json")
    String releaseStock(@PathParam("productitems") ArrayList<String> productItems);

    @POST
    @Path("/payment")
    @Produces("application/json")
    String payment(@PathParam("paymentInfo") String paymentInfo);

    @PUT
    @Path("/payment/{id}/cancel")
    @Produces("application/json")
    String cancelPayment(@PathParam("payment") String payment);


    @POST
    @Path("/shipping")
    @Produces("application/json")
    String scheduleShipping(@PathParam("order") String order);

    @DELETE
    @Path("/shipping/{id}")
    @Produces("application/json")
    String cancelShipping(@PathParam("schippingId") String schippingId);
   
    
}
