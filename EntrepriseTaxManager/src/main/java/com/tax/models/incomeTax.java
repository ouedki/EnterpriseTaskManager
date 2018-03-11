package com.tax.models;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IT")
public class incomeTax extends Tax {

	public incomeTax() {
		super();
		// TODO Auto-generated constructor stub
	}

	public incomeTax(String name, Date date, double amount, Company company) {
		super(name, date, amount, company);
		// TODO Auto-generated constructor stub
	}
	
}
