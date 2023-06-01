package com.stackroute.searchservice.collection;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
	
	public ProductDTO convertEntityToDto(Product product) {
        ModelMapper modelMapper = new ModelMapper();
        
        return modelMapper.map(product, ProductDTO.class);
    }

	public Product convertDtoToEntity(ProductDTO productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        
        return modelMapper.map(productDTO, Product.class);
    }
	
}
