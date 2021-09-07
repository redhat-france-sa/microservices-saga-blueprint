package com.redhat.microsaga.services;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.microsaga.model.ProductItem;
import com.redhat.microsaga.rest.RemoteServices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class StockService {
    @Inject
    @RestClient
    RemoteServices remoteServices;

    //@Fallback(fallbackMethod = "")
    public String reserveStock(ArrayList<ProductItem> productItems) {
        return remoteServices.reserveStock(productItems);
    }
    public String releaseStock(ArrayList<ProductItem> productItems) {
        return remoteServices.releaseStock(productItems);
    }
    

}
