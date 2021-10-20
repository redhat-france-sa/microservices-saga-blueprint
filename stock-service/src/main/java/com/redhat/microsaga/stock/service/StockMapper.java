package com.redhat.microsaga.stock.service;

import com.redhat.microsaga.stock.dto.ProductItemDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "cdi")
/**
 * This is a MapStruct mapper definition for Stock model and helpful beans.
 * @author laurent
 */
public interface StockMapper {

   default String map(UUID value) {
      return value.toString();
   }

   List<ProductQuantity> fromResources(List<ProductItemDTO> productItems);
}
