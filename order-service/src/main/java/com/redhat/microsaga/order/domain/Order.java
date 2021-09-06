package com.redhat.microsaga.order.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author laurent
 */
@Entity
@Table(name="orders")
public class Order {

   @Id
   @GeneratedValue(generator="UUID")
   @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
   private String id;

   private String customerId;

   private Double totalPrice;

   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name = "orderId")
   private List<ProductItem> productItems;

   private String paymentCardId;

   private String shippingAddressId;


   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getCustomerId() {
      return customerId;
   }

   public void setCustomerId(String customerId) {
      this.customerId = customerId;
   }

   public Double getTotalPrice() {
      return totalPrice;
   }

   public void setTotalPrice(Double totalPrice) {
      this.totalPrice = totalPrice;
   }

   public List<ProductItem> getProductItems() {
      return productItems;
   }

   public void setProductItems(List<ProductItem> productItems) {
      this.productItems = productItems;
   }

   public String getPaymentCardId() {
      return paymentCardId;
   }

   public void setPaymentCardId(String paymentCardId) {
      this.paymentCardId = paymentCardId;
   }

   public String getShippingAddressId() {
      return shippingAddressId;
   }

   public void setShippingAddressId(String shippingAddressId) {
      this.shippingAddressId = shippingAddressId;
   }

   @Override
   public String toString() {
      return "Order{" +
            "id='" + id + '\'' +
            ", customerId='" + customerId + '\'' +
            ", totalPrice=" + totalPrice +
            ", productItems=" + productItems +
            ", paymentCardId='" + paymentCardId + '\'' +
            ", shippingAddressId='" + shippingAddressId + '\'' +
            '}';
   }
}
