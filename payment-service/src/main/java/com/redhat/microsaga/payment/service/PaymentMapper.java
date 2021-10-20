package com.redhat.microsaga.payment.service;

import com.redhat.microsaga.payment.domain.Payment;
import com.redhat.microsaga.payment.dto.PaymentDTO;
import com.redhat.microsaga.payment.dto.PaymentInfoDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "cdi")
/**
 * This is a MapStruct mapper definition for Payment model.
 * @author laurent
 */
public interface PaymentMapper {

   default String map(UUID value) {
      return value.toString();
   }

   PaymentDTO toResource(Payment payment);

   Payment fromResource(PaymentInfoDTO paymentInfo);
}
