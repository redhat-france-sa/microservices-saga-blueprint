package com.redhat.outbox.kafka;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.apache.kafka.connect.transforms.util.SimpleConfig;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.errors.DataException;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Main project class implementing JSON payload string transformation.
 * @author laurent
 */
abstract class ExpandJSONPayload<R extends ConnectRecord<R>> implements Transformation<R> {

   private static final Logger LOGGER = LoggerFactory.getLogger(ExpandJSONPayload.class);

   private static final ConfigDef CONFIG_DEF = new ConfigDef();

   private static final String PURPOSE = "JSON Payload field expansion";

   private String delimiterSplit = "\\.";
   private String delimiterJoin = ".";

   @Override
   public void configure(Map<String, ?> configs) {
      final SimpleConfig config = new SimpleConfig(CONFIG_DEF, configs);
   }

   @Override
   public R apply(R record) {
      if (operatingSchema(record) == null) {
         LOGGER.info("Schemaless records not supported");
         return null;
      } else {
         return applyWithSchema(record);
      }
   }

   private R applyWithSchema(R record) {
      try {
         Object recordValue = operatingValue(record);
         if (recordValue == null) {
            LOGGER.info("ExpandJSONPayload received record is null");
            LOGGER.info(record.toString());
            return record;
         }

         final String stringValue = requireString(recordValue);

         final BsonDocument bsonPayload = parseJsonPayload(stringValue);
         final Schema updatedSchema = makeUpdatedSchema(bsonPayload);
         final Struct updatedValue = makeUpdatedValue(updatedSchema, bsonPayload);

         return newRecord(record, updatedSchema, updatedValue);
      } catch (DataException e) {
         LOGGER.warn("ExpandJSON fields missing from record: " + record.toString(), e);
         return record;
      }
   }

   private static BsonDocument parseJsonPayload(String jsonString) {
      BsonDocument bson;
      try {
         if (jsonString.startsWith("{")) {
            bson = BsonDocument.parse(jsonString);
         } else if (jsonString.startsWith("[")) {
            final BsonArray bsonArray = BsonArray.parse(jsonString);
            bson = new BsonDocument();
            bson.put("array", bsonArray);
         } else {
            String msg = String.format("Unable to parse payload starting with '%s'", jsonString.charAt(0));
            throw new Exception(msg);
         }
      } catch (Exception ex) {
         LOGGER.warn(ex.getMessage(), ex);
         bson = new BsonDocument();
         bson.put("value", new BsonString(jsonString));
         bson.put("error", new BsonString(ex.getMessage()));
      }
      return bson;
   }

   /**
    * Update schema using parsed JSON document.
    * @return New schema for output record.
    */
   private static Schema makeUpdatedSchema(BsonDocument jsonParsedPayload) {
      return SchemaParser.bsonDocument2Schema(jsonParsedPayload);
   }

   /**
    * Copy original fields value or take parsed JSON from collection.
    * @return Output record with parsed JSON values.
    */
   private static Struct makeUpdatedValue(Schema updatedSchema, BsonDocument jsonParsedPayload) {
      return DataConverter.jsonStr2Struct(jsonParsedPayload, updatedSchema);
   }

   private static String requireString(Object value) {
      if (!(value instanceof String)) {
         throw new DataException("Only String objects supported for [" + PURPOSE + "], found: " + nullSafeClassName(value));
      } else {
         return (String)value;
      }
   }

   private static String nullSafeClassName(Object x) {
      return x == null ? "null" : x.getClass().getName();
   }

   @Override
   public ConfigDef config() {
      return CONFIG_DEF;
   }

   @Override
   public void close() { }

   protected abstract Schema operatingSchema(R record);

   protected abstract Object operatingValue(R record);

   protected abstract R newRecord(R record, Schema updatedSchema, Object updatedValue);

   public static class Value<R extends ConnectRecord<R>> extends ExpandJSONPayload<R> {

      @Override
      protected Schema operatingSchema(R record) {
         return record.valueSchema();
      }

      @Override
      protected Object operatingValue(R record) {
         return record.value();
      }

      @Override
      protected R newRecord(R record, Schema updatedSchema, Object updatedValue) {
         return record.newRecord(record.topic(), record.kafkaPartition(), record.keySchema(), record.key(), updatedSchema, updatedValue, record.timestamp());
      }
   }
}