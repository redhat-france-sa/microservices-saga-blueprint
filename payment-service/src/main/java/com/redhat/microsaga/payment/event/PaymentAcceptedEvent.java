package com.redhat.microsaga.payment.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redhat.microsaga.payment.domain.Payment;

import java.time.Instant;
import java.util.UUID;

/**
 * Definition of PaymentAcceptedEvent managed by Debezium outbox plugin.
 * @author laurent
 */
public class PaymentAcceptedEvent extends PaymentEvent {

   private static final String EVENT_TYPE = "PaymentAccepted";

   private PaymentAcceptedEvent(String id, JsonNode payment) {
      super(id, payment);
   }

   public static PaymentAcceptedEvent of(Payment payment) {
      ObjectNode asJson = mapper.createObjectNode()
            .put("specversion", CE_VERSION)
            .put("type", EVENT_TYPE)
            .put("source", CE_SOURCE)
            .put("datacontenttype", CE_DATACONTENT)
            .put("id", UUID.randomUUID().toString())
            .put("time", Instant.now().toString())
            .put("kogitoprocrefid", payment.orderId)
            .set("data", mapper.convertValue(payment, JsonNode.class));

      return new PaymentAcceptedEvent(payment.id, asJson);
   }

   @Override
   public String getType() {
      return EVENT_TYPE;
   }
}
