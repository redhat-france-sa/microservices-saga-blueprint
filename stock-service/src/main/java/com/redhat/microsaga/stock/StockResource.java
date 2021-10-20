package com.redhat.microsaga.stock;

import com.redhat.microsaga.stock.service.StockMapper;
import com.redhat.microsaga.stock.service.StockRelease;
import com.redhat.microsaga.stock.service.StockReservation;
import com.redhat.microsaga.stock.service.StockService;
import com.redhat.microsaga.stock.dto.ProductItemDTO;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/api/stock")
/**
 * @author laurent
 */
public class StockResource {

   /** Get a JBoss logging logger. */
   private final Logger logger = Logger.getLogger(getClass());

   @Inject
   StockService stockService;

   @Inject
   StockMapper stockMapper;

   @GET
   @Path("/{id}/check")
   @Produces(MediaType.APPLICATION_JSON)
   public Uni<Boolean> checkStock(@PathParam("id") String productId, @QueryParam("quantity") long quantity) {
      logger.infof("Checking available stocks for productId '%s' with quantity '%d'", productId, quantity);
      return stockService.checkStock(productId, quantity);
   }

   @POST
   @Path("/reserve")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Uni<List<StockReservation>> reserveStock(List<ProductItemDTO> productItems) {
      logger.infof("Reserving stocks for %s", productItems);
      return stockService.reserveStock(stockMapper.fromResources(productItems));
   }

   @POST
   @Path("/release")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Uni<List<StockRelease>> releaseStock(List<ProductItemDTO> productItems) {
      logger.infof("Releasing stocks for %s", productItems);
      return stockService.releaseStock(stockMapper.fromResources(productItems));

   }
}
