package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Validationclass;
import com.petshop.in.exceptions.groomingvaccination.VaccinationInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsNotFoundException;
import com.petshop.in.model.Vaccinations;
import com.petshop.in.repository.VaccinationRepository;
import com.petshop.in.service.VaccinationService;



@Service
public class VaccinationServiceImpl implements VaccinationService{
	@Autowired
	private VaccinationRepository vaccinationrepos;
	
	@Override
	public List<Vaccinations>getAllVaccinations() throws VaccinationsNotFoundException
    {
		List<Vaccinations> vsl=vaccinationrepos.findAll();
    	if(vsl.isEmpty()) {
    		throw new VaccinationsNotFoundException("Validation failed:No vaccination services ");
    	}
		return vsl;	
    }
	
	@Override
	public  Vaccinations getVaccinationsByServiceId(int vaccinationId) throws VaccinationsIdNotFoundException, MismatchDataTypeException 
	 {
		try {
			
			Vaccinations  vs = vaccinationrepos.findById(vaccinationId).get();	
    		return vs;
    	}
    	catch(NoSuchElementException e) {
    		throw new VaccinationsIdNotFoundException("Validation Failed:Vaccination Id not found");
    	}
	 }

	@Override
	public List<Vaccinations> getAllVaccinationsAvailable() {
		// TODO Auto-generated method stub
		return vaccinationrepos.findByAvailable();
	}
	
	@Override

	public List<Vaccinations> getAllVaccinationsUnavailable() {
		// TODO Auto-generated method stub
		return vaccinationrepos.findByUnavailable();
	}

	@Override
	public SuccessResponse addVaccinationService(Vaccinations vaccinations) throws VaccinationInvalidInputException, MismatchDataTypeException
	{
		try {
			if(!Validationclass.ValidationInt(vaccinations.getName())) {
				throw new MismatchDataTypeException("Name shoutld be string");
			}
			if(!Validationclass.ValidationInt(vaccinations.getDescription())) {
				throw new MismatchDataTypeException("Description should be string");
			}
			if(!(vaccinations.getAvailable()==0 || vaccinations.getAvailable()==1)) {
				throw new MismatchDataTypeException("Invalid value");
			}
			Vaccinations v=vaccinationrepos.save(vaccinations);
			SuccessResponse s=new SuccessResponse();
    		s.setMessage("Data added"+v);
    		s.setStatus("Success");
    		s.setTimestamp(LocalDate.now());
    		return s;
    	}
    	catch(Exception e) {
    		throw new VaccinationInvalidInputException("Validation failed:Vaccination cannot be added");
    	}
	}
	  
	@Override
	public SuccessResponse updateVaccinationService(int vaccinationId,Vaccinations updatedVaccinationService) throws VaccinationsIdNotFoundException, MismatchDataTypeException
		{
		try {
			Vaccinations vaccinationservice=vaccinationrepos.findById(vaccinationId).get();
			if(vaccinationservice!=null)
			{
				if(!Validationclass.ValidationInt(updatedVaccinationService.getName())) {
					throw new MismatchDataTypeException("Name should be string");
				}
				if(!Validationclass.ValidationInt(updatedVaccinationService.getDescription())) {
					throw new MismatchDataTypeException("Description should be string");
				}
				if(!(updatedVaccinationService.getAvailable()==0 || updatedVaccinationService.getAvailable()==1)) {
					throw new MismatchDataTypeException("Invalid value");
				}
			vaccinationservice.setName( updatedVaccinationService.getName());
			vaccinationservice.setDescription(updatedVaccinationService.getDescription());
			vaccinationservice.setPrice(updatedVaccinationService.getPrice());
			vaccinationservice.setAvailable(updatedVaccinationService.getAvailable());
			}
			 vaccinationrepos.save(vaccinationservice);
			 
			 SuccessResponse s=new SuccessResponse();
				s.setMessage("Data Updated"+vaccinationservice);
				s.setStatus("Success");
				s.setTimestamp(LocalDate.now());
				return s;
		}
		catch(NoSuchElementException exe) {
			throw new VaccinationsIdNotFoundException("Validation Failed:Invalid Vaccination Id");
		}
		}
	
	
	
}
