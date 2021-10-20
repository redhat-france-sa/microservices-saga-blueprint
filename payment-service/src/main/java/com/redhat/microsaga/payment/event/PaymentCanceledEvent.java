package com.redhat.microsaga.payment.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redhat.microsaga.payment.domain.Payment;

/**
 * Definition of PaymentCanceledEvent managed by Debezium outbox plugin.
 * @author laurent
 */
public class PaymentCanceledEvent extends PaymentEvent {

   private static final String EVENT_TYPE = "PaymentCanceled";

   private PaymentCanceledEvent(String id, JsonNode payment) {
      super(id, payment);
   }

   public static PaymentCanceledEvent of(Payment payment) {
      ObjectNode asJson = mapper.createObjectNode()
            .put("paymentId", payment.id)
            .put("orderId", payment.orderId)
            .put("paymentCardId", payment.paymentCardId);

      return new PaymentCanceledEvent(payment.id, asJson);
   }

   @Override
   public String getType() {
      return EVENT_TYPE;
   }
}
