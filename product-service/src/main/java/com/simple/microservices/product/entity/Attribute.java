package com.simple.microservices.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Attribute {
@jakarta.persistence.Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
private String brand;
private String color;
private String storage;
public Long getId() {
	return Id;
}
public void setId(Long id) {
	Id = id;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public String getStorage() {
	return storage;
}
public void setStorage(String storage) {
	this.storage = storage;
}


}
