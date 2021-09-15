package com.redhat.outbox.kafka;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author laurent
 */
public class ExpandJSONPayloadTest {

   private ExpandJSONPayload<SinkRecord> xform = new ExpandJSONPayload.Value<>();

   @Test
   public void basicCase() {
      final Schema schema = SchemaBuilder.STRING_SCHEMA;
      final String payloadValue = "{\"orderId\":\"cbc41087-40ff-44b6-a3bb-b88a47b49ffd\",\"customerId\":\"azertyuiop\",\"paymentCardId\":\"5137\",\"shippingAddressId\":123456}";

      final SinkRecord record = new SinkRecord("orders", 0, null, null, schema, payloadValue, 0);
      final SinkRecord transformedRecord = xform.apply(record);

      final Struct updatedValue = (Struct) transformedRecord.value();
      assertEquals(4, updatedValue.schema().fields().size());
      assertEquals("string", updatedValue.schema().field("orderId").schema().type().getName());
      assertEquals("string", updatedValue.schema().field("customerId").schema().type().getName());
      assertEquals("string", updatedValue.schema().field("paymentCardId").schema().type().getName());
      assertEquals("int32", updatedValue.schema().field("shippingAddressId").schema().type().getName());
      assertEquals("cbc41087-40ff-44b6-a3bb-b88a47b49ffd", updatedValue.getString("orderId"));
      assertEquals(Integer.valueOf(123456), updatedValue.getInt32("shippingAddressId"));
   }
}
