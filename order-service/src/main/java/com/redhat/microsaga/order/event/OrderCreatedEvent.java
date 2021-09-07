package com.redhat.microsaga.order.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redhat.microsaga.order.domain.Order;
import io.debezium.outbox.quarkus.ExportedEvent;

import java.time.Instant;

/**
 * Definition of OrderCreatedEvent managed by Debezium outbox plugin.
 * @author laurent
 */
public class OrderCreatedEvent implements ExportedEvent<String, JsonNode> {

   private static ObjectMapper mapper = new ObjectMapper();

   private static final String TYPE = "Order";
   private static final String EVENT_TYPE = "OrderCreated";

   private final String orderId;
   private final JsonNode jsonNode;
   private final Instant timestamp;

   private OrderCreatedEvent(String id, JsonNode order) {
      this.orderId = id;
      this.timestamp = Instant.now();;
      this.jsonNode = order;
   }

   public static OrderCreatedEvent of(Order order) {
      ObjectNode asJson = mapper.createObjectNode()
            .put("orderId", order.getId())
            .put("customerId", order.getCustomerId())
            .put("paymentCardId", order.getPaymentCardId())
            .put("shippingAddressId", order.getShippingAddressId());

      return new OrderCreatedEvent(order.getId(), asJson);
   }

   @Override
   public String getAggregateId() {
      return orderId;
   }

   @Override
   public String getAggregateType() {
      return TYPE;
   }

   @Override
   public String getType() {
      return EVENT_TYPE;
   }

   @Override
   public Instant getTimestamp() {
      return timestamp;
   }

   @Override
   public JsonNode getPayload() {
      return jsonNode;
   }
}
