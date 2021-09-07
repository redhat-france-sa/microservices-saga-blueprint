package com.redhat.microsaga.order;

import com.redhat.microsaga.order.domain.Order;
import com.redhat.microsaga.order.dto.OrderInfoDTO;
import com.redhat.microsaga.order.service.OrderMapper;
import com.redhat.microsaga.order.service.OrderService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/order")
/**
 * JAX-RS resource exposing Order API.
 * @author laurent
 */
public class OrderResource {

   /** Get a JBoss logging logger. */
   private final Logger logger = Logger.getLogger(getClass());

   @Inject
   OrderService orderService;

   @Inject
   OrderMapper orderMapper;

   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOrder(@PathParam("id") String id) {
      logger.infof("Get order with id %s", id);
      Order order = orderService.getOrder(id);
      return Response.ok(order).build();
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createOrder(OrderInfoDTO info) {
      logger.infof("Creating order with %s", info.toString());
      Order order = orderService.saveOrder(orderMapper.fromResource(info));
      return Response.status(Response.Status.CREATED).entity(orderMapper.toResource(order)).build();
   }
}
