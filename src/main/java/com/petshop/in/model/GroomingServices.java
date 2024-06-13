package com.petshop.in.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grooming_services")
public class GroomingServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price",columnDefinition = "Decimal(10,2)")
    private Double price;

    @Column(name = "available",columnDefinition = "tinyint")
    private int available;

	public int getServiceId() {
		return serviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public GroomingServices(int serviceId, String name, String description, Double price, int available) {
		super();
		this.serviceId = serviceId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.available = available;
	}

	public GroomingServices(String name, String description, Double price, int available) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.available = available;
	}

	public GroomingServices() {
		super();
	}

	@Override
	public String toString() {
		return "GroomingServices [serviceId=" + serviceId + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", available=" + available + "]";
	}

    
}

