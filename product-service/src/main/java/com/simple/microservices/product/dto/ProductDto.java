package com.simple.microservices.product.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.simple.microservices.product.entity.Attribute;

public class ProductDto {
	private String id;
	private String name;
	private String sku;
	private String description;
	private String category;
	private BigDecimal price;
	private Integer availableQuantity;
	private Boolean inStock;
	/* private Map<String, Object> attributes; */
	private Attribute attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}
//
//	public Map<String, Object> getAttributes() {
//		return attributes;
//	}
//
//	public void setAttributes(Map<String, Object> attributes) {
//		this.attributes = attributes;
//	}

	public Attribute getAtttribute() {
		return attributes;
	}

	public void setAtttribute(Attribute atttribute) {
		this.attributes = atttribute;
	}
	
	
}
