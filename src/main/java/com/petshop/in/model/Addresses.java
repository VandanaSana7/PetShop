package com.petshop.in.model;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Addresses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;

	@Column(name = "street", length = 255)
	private String street;

	@Column(name = "city", length = 100)
	private String city;

	@Column(name = "state", length = 50)
	private String state;

	@Column(name = "zip_code", length = 20)
	private String zipCode;

	public int getAddressId() {
		return addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Addresses(String street, String city, String state, String zipCode) {

		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public Addresses() {

	}

	@Override
	public String toString() {
		return "Addresses [addressId=" + addressId + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + "]";
	}

}
