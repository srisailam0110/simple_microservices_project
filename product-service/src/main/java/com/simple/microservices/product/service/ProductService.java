package com.simple.microservices.product.service;

import com.simple.microservices.product.dto.ProductDto;
import com.simple.microservices.product.entity.Attribute;
import com.simple.microservices.product.entity.Product;
import com.simple.microservices.product.exception.ResourceNotFoundException;
import com.simple.microservices.product.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductDto create(ProductDto dto) {
		Product product = map(dto);
		product.setId(null);
		return map(productRepository.save(product));
	}

	public List<ProductDto> getAll() {
		return productRepository.findAll().stream().map(this::map).collect(Collectors.toList());
	}

	public ProductDto getById(String id) {
		return map(getEntity(id));
	}

	public ProductDto update(String id, ProductDto dto) {
		Product product = getEntity(id);
		product.setName(dto.getName());
		product.setSku(dto.getSku());
		product.setDescription(dto.getDescription());
		product.setCategory(dto.getCategory());
		product.setPrice(dto.getPrice());
		product.setAvailableQuantity(dto.getAvailableQuantity());
		product.setInStock(dto.getInStock());	
		product.setAttributes(dto.getAtttribute());
		return map(productRepository.save(product));
	}

	public ProductDto updateInventory(String id, Integer availableQuantity) {
		Product product = getEntity(id);
		product.setAvailableQuantity(availableQuantity);
		product.setInStock(availableQuantity != null && availableQuantity > 0);
		return map(productRepository.save(product));
	}

	public void delete(String id) {
		productRepository.delete(getEntity(id));
	}

	public ProductDto internal(String id) {
		return map(getEntity(id));
	}

	private Product getEntity(String id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
	}

	private ProductDto map(Product product) {
		ProductDto dto = new ProductDto();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setSku(product.getSku());
		dto.setDescription(product.getDescription());
		dto.setCategory(product.getCategory());
		dto.setPrice(product.getPrice());
		dto.setAvailableQuantity(product.getAvailableQuantity());
		dto.setInStock(product.getInStock());
		dto.setAtttribute(product.getAttributes());
		return dto;
	}

	private Product map(ProductDto dto) {
		Product p = new Product();
		p.setId(dto.getId());
		p.setName(dto.getName());
		p.setSku(dto.getSku());
		p.setDescription(dto.getDescription());
		p.setCategory(dto.getCategory());
		p.setPrice(dto.getPrice());
		p.setAvailableQuantity(dto.getAvailableQuantity());
		p.setInStock(dto.getInStock());
		p.setAttributes(dto.getAtttribute());
		return p;
	}
}
