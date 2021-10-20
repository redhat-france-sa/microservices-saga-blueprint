package com.redhat.microsaga.stock.service;

/**
 * Simple bean representing a product + reservation tuple.
 * @author laurent
 */
public class StockReservation {

   private String productId;
   private boolean reserved;

   public StockReservation(String productId, boolean reserved) {
      this.productId = productId;
      this.reserved = reserved;
   }

   public String getProductId() {
      return productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public boolean isReserved() {
      return reserved;
   }

   public void setReserved(boolean reserved) {
      this.reserved = reserved;
   }

   @Override
   public String toString() {
      return "StockReservation{" +
            "productId='" + productId + '\'' +
            ", reserved=" + reserved +
            '}';
   }
}
