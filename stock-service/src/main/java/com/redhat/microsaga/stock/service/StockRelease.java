package com.redhat.microsaga.stock.service;

/**
 * Simple bean representing a product + release tuple.
 * @author laurent
 */
public class StockRelease {

   private String productId;
   private boolean released;

   public StockRelease(String productId, boolean released) {
      this.productId = productId;
      this.released = released;
   }

   public String getProductId() {
      return productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public boolean isReleased() {
      return released;
   }

   public void setReleased(boolean released) {
      this.released = released;
   }

   @Override
   public String toString() {
      return "StockRelease{" +
            "productId='" + productId + '\'' +
            ", released=" + released +
            '}';
   }
}
