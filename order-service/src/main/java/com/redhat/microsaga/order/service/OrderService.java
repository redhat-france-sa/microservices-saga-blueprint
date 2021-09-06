package com.redhat.microsaga.order.service;

import com.redhat.microsaga.order.domain.Order;
import com.redhat.microsaga.order.domain.ProductItem;
import com.redhat.microsaga.order.repository.OrderRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
/**
 * @author laurent
 */
public class OrderService {

   /** Get a JBoss logging logger. */
   private final Logger logger = Logger.getLogger(getClass());

   @Inject
   OrderRepository orderRepository;

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
      return orderInfo;
   }
}
