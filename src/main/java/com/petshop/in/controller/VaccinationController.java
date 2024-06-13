package com.petshop.in.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.groomingvaccination.VaccinationInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsNotFoundException;
import com.petshop.in.model.Vaccinations;
import com.petshop.in.serviceimpl.VaccinationServiceImpl;

@RestController
@RequestMapping("/api/v1/vaccinations")
public class VaccinationController {
	@Autowired
	private VaccinationServiceImpl vac_service;
	
	 @GetMapping
	    public List<Vaccinations> retrieveAll() throws VaccinationsNotFoundException{
			return vac_service.getAllVaccinations();	
	    }
	
	 
	 @GetMapping("/{vaccinationId}")
	    public Vaccinations retrieveByVaccinationId(@PathVariable("vaccinationId") int vaccinationId) throws VaccinationsIdNotFoundException, MismatchDataTypeException {
			return vac_service.getVaccinationsByServiceId(vaccinationId);
	    	
	    }
	@RequestMapping("/available")
	public List<Vaccinations> getAllVacAvailable(){
		return vac_service.getAllVaccinationsAvailable();

	}
	
	@RequestMapping("/unavailable")
	public List<Vaccinations> getAllVacUnavailable(){
		return vac_service.getAllVaccinationsUnavailable();

	}
	
	 @PostMapping("/add")
	 public SuccessResponse addVaccinationService(@RequestBody Vaccinations vaccinations) throws VaccinationInvalidInputException, MismatchDataTypeException
	 {
		return vac_service.addVaccinationService(vaccinations);	
	 }
	 
	 @PutMapping("/update/{vaccinationId}")
		public SuccessResponse updateVaccinationService(@PathVariable("vaccinationId") Integer vaccinationId,@RequestBody Vaccinations vaccinationservice) throws VaccinationsIdNotFoundException, MismatchDataTypeException
		{
			return vac_service.updateVaccinationService(vaccinationId,vaccinationservice);
		}
	

}
