package com.poisonedyouth.springSecurity.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="address_id")
	private Long id;
	@Column(name="street")
	@NotEmpty(message = "*Please provide your street")
	private String street;
	@Column(name = "zip")
	@NotEmpty(message = "*Please provide your zip code")
	private String zip;
	@Column(name = "city")
	@NotEmpty(message = "*Please provide your city")
	private String city;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
