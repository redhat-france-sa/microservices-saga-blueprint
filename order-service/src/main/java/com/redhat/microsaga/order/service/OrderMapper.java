package com.redhat.microsaga.order.service;

import com.redhat.microsaga.order.domain.Order;
import com.redhat.microsaga.order.domain.ProductItem;
import com.redhat.microsaga.order.dto.OrderDTO;
import com.redhat.microsaga.order.dto.OrderInfoDTO;
import com.redhat.microsaga.order.dto.ProductItemDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "cdi")
/**
 * This is a MapStruct mapper definition for Order model.
 * @author laurent
 */
public interface OrderMapper {

   default String map(UUID value) {
      return value.toString();
   }

   OrderDTO toResource(Order order);

   Order fromResource(OrderInfoDTO orderInfo);

   ProductItem fromResource(ProductItemDTO productItemDTO);
}
