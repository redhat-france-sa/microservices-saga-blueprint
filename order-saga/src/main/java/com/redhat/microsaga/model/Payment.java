/*
 * Payment API
 * Payment API for https://github.com/redhat-france-sa/microservices-saga-blueprint
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.redhat.microsaga.model;

import java.util.Objects;


/**
 * Payment
 */
public class Payment {

  private String id;
  
  private String status;

  private String paymentCardId;

  private Double amount;

  private String currency;

  private String orderId;


   /**
   * Unique identifier of Payment
   * @return id
  **/

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }



   /**
   * Status of Payment
   * @return status
  **/

  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }


  public void paymentCardId(String paymentCardId) {
    this.paymentCardId = paymentCardId;
  }

   /**
   * Card identifier for payment
   * @return paymentCardId
  **/

  public String getPaymentCardId() {
    return paymentCardId;
  }


  public void setPaymentCardId(String paymentCardId) {
    this.paymentCardId = paymentCardId;
  }




   /**
   * The amount to be charged
   * @return amount
   **/

  public Double getAmount() {
    return amount;
  }


  public void setAmount(Double amount) {
    this.amount = amount;
  }


   /**
   * Get currency
   * @return currency
  **/

  public String getCurrency() {
    return currency;
  }


  public void setCurrency(String currency) {
    this.currency = currency;
  }

   /**
   * Get orderId
   * @return orderId
  **/

  public String getOrderId() {
    return orderId;
  }


  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payment payment = (Payment) o;
    return Objects.equals(this.id, payment.id) &&
        Objects.equals(this.status, payment.status) &&
        Objects.equals(this.paymentCardId, payment.paymentCardId) &&
        Objects.equals(this.amount, payment.amount) &&
        Objects.equals(this.currency, payment.currency) &&
        Objects.equals(this.orderId, payment.orderId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, paymentCardId, amount, currency, orderId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payment {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    paymentCardId: ").append(toIndentedString(paymentCardId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
