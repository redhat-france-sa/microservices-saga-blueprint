package com.redhat.microsaga.payment.service;

import com.redhat.microsaga.payment.domain.Payment;
import com.redhat.microsaga.payment.domain.PaymentStatus;
import com.redhat.microsaga.payment.event.PaymentCanceledEvent;
import com.redhat.microsaga.payment.event.PaymentCreatedEvent;
import io.debezium.outbox.quarkus.ExportedEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;

@ApplicationScoped
/**
 * Service for creating and retrieving Payments.
 * @author laurent
 */
public class PaymentService {

   /** Get a JBoss logging logger. */
   private final Logger logger = Logger.getLogger(getClass());

   @Inject
   Event<ExportedEvent<?, ?>> paymentEvent;

   @Transactional
   public Payment createPayment(Payment paymentInfo) {
      logger.infof("Creating paymentInfo %s", paymentInfo.toString());
      paymentInfo.status = PaymentStatus.ACCEPTED;
      paymentInfo.persist();

      // Send event and return.
      logger.info("Firing an PaymentCreatedEvent");
      paymentEvent.fire(PaymentCreatedEvent.of(paymentInfo));
      return paymentInfo;
   }

   @Transactional
   public Payment cancelPayment(String id) {
      Payment payment = Payment.findById(id);
      payment.status = PaymentStatus.CANCELED;
      payment.persist();

      // Send event and return.
      logger.info("Firing an PaymentCanceledEvent");
      paymentEvent.fire(PaymentCanceledEvent.of(payment));
      return payment;
   }
}
