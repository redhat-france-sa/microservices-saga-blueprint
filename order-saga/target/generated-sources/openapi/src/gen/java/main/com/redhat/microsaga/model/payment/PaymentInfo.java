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


package com.redhat.microsaga.model.payment;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * PaymentInfo
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-09-06T17:56:06.923067+02:00[Europe/Paris]")
public class PaymentInfo {
  public static final String SERIALIZED_NAME_PAYMENT_CARD_ID = "paymentCardId";
  @SerializedName(SERIALIZED_NAME_PAYMENT_CARD_ID)
  private String paymentCardId;

  public static final String SERIALIZED_NAME_AMOUNT = "amount";
  @SerializedName(SERIALIZED_NAME_AMOUNT)
  private Double amount;

  /**
   * Gets or Sets currency
   */
  @JsonAdapter(CurrencyEnum.Adapter.class)
  public enum CurrencyEnum {
    EUR("EUR"),
    
    USD("USD");

    private String value;

    CurrencyEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static CurrencyEnum fromValue(String value) {
      for (CurrencyEnum b : CurrencyEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<CurrencyEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final CurrencyEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public CurrencyEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return CurrencyEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_CURRENCY = "currency";
  @SerializedName(SERIALIZED_NAME_CURRENCY)
  private CurrencyEnum currency;

  public static final String SERIALIZED_NAME_ORDER_ID = "orderId";
  @SerializedName(SERIALIZED_NAME_ORDER_ID)
  private String orderId;


  public PaymentInfo paymentCardId(String paymentCardId) {
    
    this.paymentCardId = paymentCardId;
    return this;
  }

   /**
   * Card identifier for payment
   * @return paymentCardId
  **/
  @ApiModelProperty(required = true, value = "Card identifier for payment")

  public String getPaymentCardId() {
    return paymentCardId;
  }


  public void setPaymentCardId(String paymentCardId) {
    this.paymentCardId = paymentCardId;
  }


  public PaymentInfo amount(Double amount) {
    
    this.amount = amount;
    return this;
  }

   /**
   * The amount to be charged
   * @return amount
  **/
  @ApiModelProperty(required = true, value = "The amount to be charged")

  public Double getAmount() {
    return amount;
  }


  public void setAmount(Double amount) {
    this.amount = amount;
  }


  public PaymentInfo currency(CurrencyEnum currency) {
    
    this.currency = currency;
    return this;
  }

   /**
   * Get currency
   * @return currency
  **/
  @ApiModelProperty(required = true, value = "")

  public CurrencyEnum getCurrency() {
    return currency;
  }


  public void setCurrency(CurrencyEnum currency) {
    this.currency = currency;
  }


  public PaymentInfo orderId(String orderId) {
    
    this.orderId = orderId;
    return this;
  }

   /**
   * Get orderId
   * @return orderId
  **/
  @ApiModelProperty(required = true, value = "")

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
    PaymentInfo paymentInfo = (PaymentInfo) o;
    return Objects.equals(this.paymentCardId, paymentInfo.paymentCardId) &&
        Objects.equals(this.amount, paymentInfo.amount) &&
        Objects.equals(this.currency, paymentInfo.currency) &&
        Objects.equals(this.orderId, paymentInfo.orderId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentCardId, amount, currency, orderId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentInfo {\n");
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

