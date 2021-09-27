package com.redhat.microsaga.rest;


import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.redhat.microsaga.model.ProductItem;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface StockRemoteServices {
    @PUT
    @Path("/stock/{id}/reserve")
    @Produces("application/json")
    String reserveStock(@PathParam("id") String id, ArrayList<ProductItem> productItems);

    @PUT
    @Path("/stock/{id}/release")
    @Produces("application/json")
    String releaseStock(@PathParam("id") String id, ArrayList<ProductItem> productItems);  
}
