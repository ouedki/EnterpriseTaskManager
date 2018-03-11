package com.tax.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tax.dao.CompanyRepositoryDao;
import com.tax.dao.TaxRepositoryDao;
import com.tax.models.Company;
import com.tax.models.Tax;

@Controller
public class TaxController {
	
	@Autowired
	CompanyRepositoryDao companyRepositoryDao;
	
	@Autowired
	TaxRepositoryDao taxRepositoryDao;
	
	
	@RequestMapping("/companies")
	public String companies(Model model, 
			@RequestParam(name="keyWord", defaultValue="")String keyWord, 
			@RequestParam(name="page", defaultValue="0")int page, 
			@RequestParam(name="size", defaultValue="5")int size) {
		Page<Company> companies = companyRepositoryDao.searchByKeyWord("%"+keyWord+"%", new PageRequest(page, size));
		model.addAttribute("companies", companies);
		int [] pages = new int [companies.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("currentPage", page);
		return "index";
	}
	
	@RequestMapping("/addCompany")
	public String companies(Model model) {
		model.addAttribute("company", new Company());
		return "addCompanies";
	}
	
	@RequestMapping(value="/saveCompany", method=RequestMethod.POST)
	public String saveCompany(Model model,@Valid Company c, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addCompanies";
		}
		companyRepositoryDao.save(c);
		return "redirect:/companies";
	}
	
	@RequestMapping("/taxes")
	public String taxesPerCompany(Model model, 
			@RequestParam(name="id", defaultValue="-1") Long id) {
		model.addAttribute("companies", companyRepositoryDao.findAll());
		if(id==-1) {
			model.addAttribute("taxes", new ArrayList<Tax>());
		}else {
			Company c = companyRepositoryDao.getOne(id);
			List<Tax> taxes = taxRepositoryDao.findByCompany(c);
			model.addAttribute("taxes", taxes);
		}
		
		return "taxes";
	}

}
