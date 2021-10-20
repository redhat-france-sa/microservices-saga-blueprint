package com.redhat.microsaga.payment.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Payment domain object.
 * @author laurent
 */
@Entity
@Table(name="payments")
public class Payment extends PanacheEntityBase {

   @Id
   @GeneratedValue(generator="UUID")
   @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
   public String id;

   public Date createdOn;
   public double amount;
   public String currency;

   public String orderId;
   public String paymentCardId;

   public PaymentStatus status;
}
