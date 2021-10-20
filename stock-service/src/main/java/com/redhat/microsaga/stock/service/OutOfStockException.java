package com.redhat.microsaga.stock.service;

/**
 * Business exception that occurs when running out of stock.
 * @author laurent
 */
public class OutOfStockException extends Exception {

   public OutOfStockException(String productId, long quantity) {
      super("Product " + productId + " has no " + quantity + " items available");
   }
}
