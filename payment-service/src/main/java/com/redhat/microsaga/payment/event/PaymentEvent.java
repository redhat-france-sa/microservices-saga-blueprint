package com.redhat.microsaga.payment.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.outbox.quarkus.ExportedEvent;

import java.time.Instant;

/**
 * Definie common attributes for PaymentEvents maanged by Debezium outbox plugin.
 * @author laurent
 */
public abstract class PaymentEvent implements ExportedEvent<String, JsonNode> {

   protected static ObjectMapper mapper = new ObjectMapper();

   private static final String TYPE = "Payment";

   protected static final String CE_VERSION = "1.0";
   protected static final String CE_SOURCE = "/payment-service";
   protected static final String CE_DATACONTENT = "application/json";

   protected final String paymentId;
   protected final JsonNode jsonNode;
   protected final Instant timestamp;

   public PaymentEvent(String paymentId, JsonNode payment) {
      this.paymentId = paymentId;
      this.timestamp = Instant.now();;
      this.jsonNode = payment;
   }

   @Override
   public String getAggregateId() {
      return paymentId;
   }

   @Override
   public String getAggregateType() {
      return TYPE;
   }

   @Override
   public abstract String getType();

   @Override
   public Instant getTimestamp() {
      return timestamp;
   }

   @Override
   public JsonNode getPayload() {
      return jsonNode;
   }
}
