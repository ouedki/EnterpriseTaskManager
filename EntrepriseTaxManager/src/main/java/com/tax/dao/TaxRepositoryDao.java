package com.tax.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tax.models.Company;
import com.tax.models.Tax;

public interface TaxRepositoryDao extends JpaRepository<Tax, Long> {
	public List<Tax> findByCompany(Company c);
}
