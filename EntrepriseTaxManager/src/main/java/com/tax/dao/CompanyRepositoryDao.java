package com.tax.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tax.models.Company;

public interface CompanyRepositoryDao extends JpaRepository<Company, Long>{
	
	@Query("select c from Company c where name like:x")
	public Page<Company> searchByKeyWord(@Param("x") String name, Pageable pageable);

}
