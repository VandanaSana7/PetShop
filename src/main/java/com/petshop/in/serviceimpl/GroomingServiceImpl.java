package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Validationclass;
import com.petshop.in.exceptions.groomingvaccination.GroomingServiceInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.ServiceAvailableException;
import com.petshop.in.exceptions.groomingvaccination.ServiceIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.ServiceUnavailableException;
import com.petshop.in.exceptions.groomingvaccination.SevicesNotFoundException;
import com.petshop.in.model.GroomingServices;
import com.petshop.in.repository.GroomingServicesRepository;
import com.petshop.in.service.GroomingService;

@Service
public class GroomingServiceImpl  implements GroomingService{
    @Autowired
    GroomingServicesRepository groomingserviceRepo;
    
    @Override
    public List<GroomingServices> getAllGroomingServices() throws SevicesNotFoundException
    {       
    	List<GroomingServices> gsl=groomingserviceRepo.findAll();
    	if(gsl.isEmpty()) {
    		throw new SevicesNotFoundException("Validation failed:No grooming services ");
    	}
    		return groomingserviceRepo.findAll();
    }
    
    @Override
    public GroomingServices getGroomingServicesByServiceId(int serviceId) throws ServiceIdNotFoundException 
    {	
    	try {
    		GroomingServices gs = groomingserviceRepo.findById(serviceId).get();
    		return gs;
    	}
    	catch(NoSuchElementException e) {
    		throw new ServiceIdNotFoundException("Validation failed:Service Id not found");
    	}
    	
    }
    
    @Override
    public List<GroomingServices> getAvailableGroomingServices()   {
    	List<GroomingServices> ga=groomingserviceRepo.findAllAvailable();
    	if(ga.isEmpty()) {
    		throw new ServiceAvailableException("Validation failed:No grooming services available ");
    	}
    	return groomingserviceRepo.findAllAvailable();
    }
    
    @Override
    public List<GroomingServices> getUnavailableGroomingServices() {
    	List<GroomingServices> gu=groomingserviceRepo.findAllUnavailable();
    	if(gu.isEmpty()) {
    		throw new ServiceUnavailableException("Validation failed:Grooming services unavailable ");
    	}
    	return groomingserviceRepo.findAllUnavailable();
    }
    
    @Override
    public SuccessResponse addGroomingService(GroomingServices groomingservices) throws GroomingServiceInvalidInputException,MismatchDataTypeException {
    	try {
    		if(!Validationclass.ValidationInt(groomingservices.getName())) {
    			throw new MismatchDataTypeException("Name should be string");
    		}
    		if(!Validationclass.ValidationInt(groomingservices.getDescription())) {
    			throw new MismatchDataTypeException("Description should be string");
    		}

    		if(!(groomingservices.getAvailable()==0 || groomingservices.getAvailable()==1)) {
    			throw new MismatchDataTypeException("Invalid available status");
    		}
    		GroomingServices g= groomingserviceRepo.save(groomingservices);
    		SuccessResponse s=new SuccessResponse();
    		s.setMessage("Data added"+g);
    		s.setStatus("Success");
    		s.setTimestamp(LocalDate.now());
    		return s;
    	}
    	catch(Exception e) {
    		throw new GroomingServiceInvalidInputException("Validation failed:Grooming Service cannot be added");
    	}
    	
    }
    
    @Override
    public SuccessResponse updateGroomingService(int serviceId,GroomingServices updatedgroomingService) throws ServiceIdNotFoundException, MismatchDataTypeException 
	{
    	try
    	{
		GroomingServices groomingservice=groomingserviceRepo.findById(serviceId).get();
		if(groomingservice!=null)
		{
			if(!Validationclass.ValidationInt(updatedgroomingService.getName())) {
    			throw new MismatchDataTypeException("Name should be string");
    		}
    		if(!Validationclass.ValidationInt(updatedgroomingService.getDescription())) {
    			throw new MismatchDataTypeException("Description should be string");
    		}

    		if(!(updatedgroomingService.getAvailable()==0 || updatedgroomingService.getAvailable()==1)) {
    			throw new MismatchDataTypeException("Invalid available status");
    		}
		
		groomingservice.setName(updatedgroomingService.getName());
		groomingservice.setDescription(updatedgroomingService.getDescription());
		groomingservice.setPrice(updatedgroomingService.getPrice());
		groomingservice.setAvailable(updatedgroomingService.getAvailable());
		groomingserviceRepo.save(groomingservice);
		
		}
		SuccessResponse s=new SuccessResponse();
		s.setMessage("Data Updated"+groomingservice);
		s.setStatus("Success");
		s.setTimestamp(LocalDate.now());
		return s;
    	}
    	
    	catch(NoSuchElementException ex)
    	{
    		throw new ServiceIdNotFoundException("Validation Failed:Invalid Vaccination Id");
    	}
		
	}


}
