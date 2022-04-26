package com.techcam.mapper;

import com.techcam.dto.response.product.ProductResponseDTO;
import com.techcam.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {

    ProductResponseDTO toProductResponseDTO(ProductEntity product);

    List<ProductResponseDTO> toProductResponseDTOs(List<ProductEntity> products);
}
