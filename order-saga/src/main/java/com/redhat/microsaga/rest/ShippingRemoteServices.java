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
    @Path("/shipping/{id}/schedule")
    @Produces("application/json")
    String scheduleShipping(@PathParam("id") String id, String schippingId);

    @DELETE
    @Path("/shipping/{id}/cancel")
    @Produces("application/json")
    String cancelShipping(@PathParam("id") String id, String schippingId);
}
