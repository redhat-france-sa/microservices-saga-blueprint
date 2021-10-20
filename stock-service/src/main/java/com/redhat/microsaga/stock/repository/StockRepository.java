package com.redhat.microsaga.stock.repository;

import com.redhat.microsaga.stock.domain.Stock;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.LockModeType;

/**
 * Repository for Stock domain objects.
 * @author laurent
 */
@ApplicationScoped
public class StockRepository implements PanacheRepositoryBase<Stock, String> {

   public Uni<Stock> findByProductId(String productId){
      return find("productId", productId).firstResult();
   }
   public Uni<Stock> findByProductId(String productId, LockModeType lockModeType){
      return find("productId", productId).withLock(lockModeType).firstResult();
   }
}
