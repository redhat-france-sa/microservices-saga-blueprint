package com.redhat.microsaga.stock.service;

/**
 * Simple bean representing a product + quantity tuple.
 * @author laurent
 */
public class ProductQuantity {

   private String productId;
   private long quantity;

   public String getProductId() {
      return productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public long getQuantity() {
      return quantity;
   }

   public void setQuantity(long quantity) {
      this.quantity = quantity;
   }

   @Override
   public String toString() {
      return "ProductQuantity{" +
            "productId='" + productId + '\'' +
            ", quantity=" + quantity +
            '}';
   }
}
