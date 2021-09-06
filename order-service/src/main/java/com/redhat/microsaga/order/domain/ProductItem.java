package com.redhat.microsaga.order.domain;

import javax.persistence.*;

/**
 * @author laurent
 */
@Entity
@Table(name="product_items")
public class ProductItem {

   @Id
   @GeneratedValue
   private Long id;

   private String productId;

   private Integer quantity;

   private Double price;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getProductId() {
      return productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   @Override
   public String toString() {
      return "ProductItem{" +
            "id=" + id +
            ", productId='" + productId + '\'' +
            ", quantity=" + quantity +
            ", price=" + price +
            '}';
   }
}
