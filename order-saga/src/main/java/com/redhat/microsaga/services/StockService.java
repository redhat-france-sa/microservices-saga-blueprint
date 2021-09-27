package com.redhat.microsaga.services;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.microsaga.model.ProductItem;
import com.redhat.microsaga.rest.StockRemoteServices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class StockService {
    @Inject
    @RestClient
    StockRemoteServices stockRemoteServices;

    //@Fallback(fallbackMethod = "")
    public String reserveStock(String id, ArrayList<ProductItem> productItems) {
        return stockRemoteServices.reserveStock(id, productItems);
    }
    public String releaseStock(String id, ArrayList<ProductItem> productItems) {
        return stockRemoteServices.releaseStock(id, productItems);
    }
}
