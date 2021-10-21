package com.redhat.microsaga.order.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redhat.microsaga.order.domain.Order;
import io.debezium.outbox.quarkus.ExportedEvent;

import java.time.Instant;
import java.util.UUID;

/**
 * Definition of OrderCreatedEvent managed by Debezium outbox plugin.
 * @author laurent
 */
public class OrderCreatedEvent implements ExportedEvent<String, JsonNode> {

   private static ObjectMapper mapper = new ObjectMapper();

   private static final String TYPE = "Order";
   private static final String EVENT_TYPE = "OrderCreated";

   private static final String CE_VERSION = "1.0";
   private static final String CE_SOURCE = "/order-service";
   private static final String CE_DATACONTENT = "application/json";

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
            .put("specversion", CE_VERSION)
            .put("type", EVENT_TYPE)
            .put("source", CE_SOURCE)
            .put("datacontenttype", CE_DATACONTENT)
            .put("id", UUID.randomUUID().toString())
            .put("time", Instant.now().toString())
            .set("data", mapper.convertValue(order, JsonNode.class));

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
