package com.redhat.microsaga.payment.service;

import com.redhat.microsaga.payment.domain.Payment;
import com.redhat.microsaga.payment.domain.PaymentStatus;
import com.redhat.microsaga.payment.event.PaymentAcceptedEvent;
import com.redhat.microsaga.payment.event.PaymentCanceledEvent;
import com.redhat.microsaga.payment.event.PaymentCreatedEvent;
import com.redhat.microsaga.payment.event.PaymentDeniedEvent;
import io.debezium.outbox.quarkus.ExportedEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Random;

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
      paymentInfo.status = PaymentStatus.CREATED;
      paymentInfo.persist();

      // Send event and start processing before return.
      logger.info("Firing an PaymentCreatedEvent");
      paymentEvent.fire(PaymentCreatedEvent.of(paymentInfo));

      processPayment(paymentInfo);

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

   protected void processPayment(Payment payment) {
      PaymentProcessingThread thread = new PaymentProcessingThread(payment);
      thread.start();
   }

   @Transactional
   protected void processAcceptedPayment(Payment payment) {
      // Need to re-attach the entity to the entity manager.
      payment = Payment.findById(payment.id);
      payment.status = PaymentStatus.ACCEPTED;
      payment.persist();

      // Send denied event.
      logger.info("Firing an PaymentAcceptedEvent");
      paymentEvent.fire(PaymentAcceptedEvent.of(payment));
   }

   @Transactional
   protected void processDeniedPayment(Payment payment) {
      // Need to re-attach the entity to the entity manager.
      payment = Payment.findById(payment.id);
      payment.status = PaymentStatus.DENIED;
      payment.persist();

      // Send denied event.
      logger.info("Firing an PaymentDeniedEvent");
      paymentEvent.fire(PaymentDeniedEvent.of(payment));
   }

   /**
    * Simple thread simulating asynchronous and delayed processing of Payment.
    * Will randomly wait until 20 sec and fail payment 1/3 of times.
    */
   class PaymentProcessingThread extends Thread {

      /** Get a JBoss logging logger. */
      private final Logger logger = Logger.getLogger(getClass());

      private final Payment payment;

      /**
       * Build a new thread for processing a payment.
       * @param payment The Payment to process.
       */
      public PaymentProcessingThread(Payment payment) {
         this.payment = payment;
      }

      @Override
      public void run() {
         logger.infof("Starting processing payment %s", payment);

         // Now simulate a processing time.
         int processingTime = new Random().nextInt(1000 * 19) + 1000;
         logger.infof("Processing time estimated to %d seconds", processingTime / 1000);
         synchronized (this) {
            try {
               sleep(processingTime);
            } catch (InterruptedException e) {
               logger.warn("Got an interrupted exception while waiting procession");
            }
         }

         // Simulate some "payment deny" errors based on generated random
         // 1/3 should be enough to not wait for hours...
         if (processingTime % 3 == 0) {
            logger.infof("Payment with id '%s' is DENIED", payment.id);
            processDeniedPayment(payment);
         } else {
            logger.infof("Payment with id '%s' is ACCEPTED", payment.id);
            processAcceptedPayment(payment);
         }
      }
   }
}
