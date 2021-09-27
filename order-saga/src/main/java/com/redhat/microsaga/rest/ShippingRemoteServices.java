package com.redhat.microsaga.rest;



import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.redhat.microsaga.model.Order;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface ShippingRemoteServices {
    @POST
    @Path("/shipping")
    @Produces("application/json")
    String scheduleShipping(@PathParam("order") Order order);

    @DELETE
    @Path("/shipping/{id}")
    @Produces("application/json")
    String cancelShipping(@PathParam("schippingId") String schippingId);
}
