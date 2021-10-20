package com.redhat.microsaga.stock.service;

import com.redhat.microsaga.stock.repository.StockRepository;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.groups.UniJoin;
import io.smallrye.mutiny.unchecked.Unchecked;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
/**
 * Service for checking, reserving and releasing Stocks.
 * @author laurent
 */
public class StockService {

   /** Get a JBoss logging logger. */
   private final Logger logger = Logger.getLogger(getClass());

   @Inject
   StockRepository stockRepository;


   public Uni<Boolean> checkStock(String productId, long quantity) {
      return stockRepository.findByProductId(productId)
            .map(item -> item.getQuantity() >= quantity)
            .onFailure().recoverWithItem(false);
   }

   @ReactiveTransactional
   public Uni<List<StockReservation>> reserveStock(List<ProductQuantity> productQuantities) {
      // Mutiny !!!
      UniJoin.Builder<StockReservation> builder = Uni.join().builder();

      // For each product, decrement the quantity in stock.
      for (ProductQuantity pq : productQuantities) {
         Uni<StockReservation> reservationUni = stockRepository.findByProductId(pq.getProductId(), LockModeType.PESSIMISTIC_READ)
               .invoke(Unchecked.consumer(stock -> {
                  if (stock.getQuantity() >= pq.getQuantity()) {
                     stock.setQuantity(stock.getQuantity() - pq.getQuantity());
                     stockRepository.persist(stock);
                  } else {
                     // Can throw a checked exception here because we've wrapped the Consumer with Unchecked.
                     // See https://smallrye.io/smallrye-mutiny/guides/unchecked
                     throw new OutOfStockException(pq.getProductId(), pq.getQuantity());
                  }
               }))
               .map(item -> new StockReservation(item.getProductId(), true))
               .onFailure().recoverWithItem(failure -> {
                  logger.error(failure);
                  return new StockReservation(pq.getProductId(), false);
               });
         builder.add(reservationUni);
      }
      return builder.joinAll().andCollectFailures();
   }

   @ReactiveTransactional
   public Uni<List<StockRelease>> releaseStock(List<ProductQuantity> productQuantities) {
      // Mutiny !!!
      List<Uni<? extends StockRelease>> releasesUnis = new ArrayList<>();

      // For each product, increment the quantity in stock.
      for (ProductQuantity pq : productQuantities) {
         Uni<StockRelease> reservationUni = stockRepository.findByProductId(pq.getProductId(), LockModeType.PESSIMISTIC_READ)
               .invoke(Unchecked.consumer(stock -> {
                  stock.setQuantity(stock.getQuantity() + pq.getQuantity());
                  stockRepository.persist(stock);
               }))
               .map(item -> new StockRelease(item.getProductId(), true))
               .onFailure().retry().atMost(3).invoke(failure -> {
                  logger.error(failure);
               });
         releasesUnis.add(reservationUni);
      }
      return Uni.join().all(releasesUnis).andCollectFailures();
   }
}
