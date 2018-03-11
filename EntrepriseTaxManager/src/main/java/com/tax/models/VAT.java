package com.tax.models;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VAT")
public class VAT extends Tax {

	public VAT() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VAT(String name, Date date, double amount, Company company) {
		super(name, date, amount, company);
		// TODO Auto-generated constructor stub
	}
	

}
