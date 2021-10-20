package com.redhat.microsaga.payment;

import com.redhat.microsaga.payment.domain.Payment;
import com.redhat.microsaga.payment.dto.PaymentInfoDTO;
import com.redhat.microsaga.payment.service.PaymentMapper;
import com.redhat.microsaga.payment.service.PaymentService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/payment")
/**
 * JAX-RS resource exposing Payment API.
 * @author laurent
 */
public class PaymentResource {

   /** Get a JBoss logging logger. */
   private final Logger logger = Logger.getLogger(getClass());

   @Inject
   PaymentService paymentService;

   @Inject
   PaymentMapper paymentMapper;

   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getPayment(@PathParam("id") String id) {
      logger.infof("Get payment with id %s", id);
      Payment payment = Payment.findById(id);
      return Response.ok(paymentMapper.toResource(payment)).build();
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createPayment(PaymentInfoDTO info) {
      logger.infof("Creating payment with %s", info.toString());
      Payment payment = paymentService.createPayment(paymentMapper.fromResource(info));
      return Response.status(Response.Status.CREATED).entity(paymentMapper.toResource(payment)).build();
   }

   @PUT
   @Path("/{id}/cancel")
   @Produces(MediaType.APPLICATION_JSON)
   public Response cancelPayment(@PathParam("id") String id) {
      logger.infof("Cancelling payment with id %s", id);
      Payment payment = paymentService.cancelPayment(id);
      return Response.ok(paymentMapper.toResource(payment)).build();
   }
}
