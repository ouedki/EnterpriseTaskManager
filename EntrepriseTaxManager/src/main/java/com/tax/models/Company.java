package com.tax.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class Company implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Size(min=2, max=10)
	private String name;
	@NotNull
	@Size(min=9, max=12)
	private String phone;
	@NotNull
	@Size(min=6, max=12)
	private String taxIdNumber;
	@NotNull
	@Email(message="Please provide a valid email address")
	private String email;
	@OneToMany(mappedBy="company", fetch=FetchType.LAZY)
	private Collection<Tax> taxes;

	public Company(String name, String phone, String taxIdNumber, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.taxIdNumber = taxIdNumber;
		this.email = email;
	}

	public Company() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTaxIdNumber() {
		return taxIdNumber;
	}

	public void setTaxIdNumber(String taxIdNumber) {
		this.taxIdNumber = taxIdNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Tax> getTaxes() {
		return taxes;
	}

	public void setTaxes(Collection<Tax> taxes) {
		this.taxes = taxes;
	}
	
	
	

}
