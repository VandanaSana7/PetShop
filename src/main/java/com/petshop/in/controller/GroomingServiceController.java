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
import com.petshop.in.exceptions.groomingvaccination.GroomingServiceInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.ServiceAvailableException;
import com.petshop.in.exceptions.groomingvaccination.ServiceIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.SevicesNotFoundException;
import com.petshop.in.model.GroomingServices;
import com.petshop.in.serviceimpl.*;

@RestController
@RequestMapping("/api/v1/services")
public class GroomingServiceController {
    @Autowired
    GroomingServiceImpl grooming_service;
    
    
    @GetMapping
    public List<GroomingServices> retrieveAll() throws SevicesNotFoundException{
		return grooming_service.getAllGroomingServices();
	
    }
    
    @GetMapping("/{service_id}")
    public GroomingServices retrieveByGroomingServiceId(@PathVariable("service_id") int serviceId) throws ServiceIdNotFoundException {
    	
		return grooming_service.getGroomingServicesByServiceId( serviceId);
    	
    }
    
    @GetMapping("/available")
    public List<GroomingServices> getAvailableServices() throws ServiceAvailableException {
    	
        return grooming_service.getAvailableGroomingServices();
    }
    
    @GetMapping("/unavailable")
    public List<GroomingServices> getUnAvailableServices() throws SevicesNotFoundException {
    	
        return grooming_service.getUnavailableGroomingServices();
    }
    
    @PostMapping("/add")
	 public SuccessResponse addGroomingService(@RequestBody GroomingServices groomingservices) throws GroomingServiceInvalidInputException, MismatchDataTypeException
	 {
		return grooming_service.addGroomingService(groomingservices);
	 }
    
    @PutMapping("/update/{service_id}")
	public SuccessResponse updateGroomingService(@PathVariable("service_id") Integer service_id,@RequestBody GroomingServices groomingservice) throws ServiceIdNotFoundException, MismatchDataTypeException
	{
		return grooming_service.updateGroomingService(service_id,groomingservice);
	}
}
