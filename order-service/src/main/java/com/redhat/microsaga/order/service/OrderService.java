package com.redhat.microsaga.order.service;

import com.redhat.microsaga.order.domain.Order;
import com.redhat.microsaga.order.domain.ProductItem;
import com.redhat.microsaga.order.event.OrderCreatedEvent;
import com.redhat.microsaga.order.repository.OrderRepository;
import io.debezium.outbox.quarkus.ExportedEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
/**
 * Service for creating and retrieving Orders.
 * @author laurent
 */
public class OrderService {

   /** Get a JBoss logging logger. */
   private final Logger logger = Logger.getLogger(getClass());

   @Inject
   OrderRepository orderRepository;

   @Inject
   Event<ExportedEvent<?, ?>> orderEvent;

   public Order getOrder(String orderId) {
      return orderRepository.findById(orderId);
   }

   @Transactional
   public Order saveOrder(Order orderInfo) {
      // Compute order total price.
      double totalPrice = 0.0;
      for (ProductItem item : orderInfo.getProductItems()) {
         totalPrice += item.getPrice();
      }
      orderInfo.setTotalPrice(totalPrice);

      // Persist using repository.
      logger.infof("Saving orderInfo %s", orderInfo.toString());
      orderRepository.persist(orderInfo);

      // Send event and return.
      logger.info("Firing an OrderCreatedEvent");
      orderEvent.fire(OrderCreatedEvent.of(orderInfo));
      return orderInfo;
   }
}
