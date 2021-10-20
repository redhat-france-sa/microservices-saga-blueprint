package com.redhat.microsaga.stock.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Stock domain object.
 * @author laurent
 */
@Entity
@Table(name="stocks")
public class Stock {

   @Id
   @GeneratedValue(generator="UUID")
   @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
   private String id;

   private String productId;

   private Long quantity;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getProductId() {
      return productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public Long getQuantity() {
      return quantity;
   }

   public void setQuantity(Long quantity) {
      this.quantity = quantity;
   }

   @Override
   public String toString() {
      return "Stock{" +
            "id='" + id + '\'' +
            ", productId='" + productId + '\'' +
            ", quantity=" + quantity +
            '}';
   }
}
