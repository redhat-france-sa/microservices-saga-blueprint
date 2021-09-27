package com.redhat.microsaga.rest;


import java.util.ArrayList;

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

@RegisterRestClient
public interface OrderRemoteServices {
    @POST
    @Path("/order")
    @Produces("application/json")
    String createOrder(@PathParam("order") Order order);

    @PUT
    @Path("/order/{id}/fail")
    @Produces("application/json")
    String failOrder(@PathParam("id") String orderId);
   
    
}
