package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.groomingvaccination.VaccinationInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsNotFoundException;
import com.petshop.in.model.Vaccinations;

public interface VaccinationService {
	List<Vaccinations>getAllVaccinations() throws VaccinationsNotFoundException;
	Vaccinations getVaccinationsByServiceId(int vaccinationId) throws VaccinationsIdNotFoundException,MismatchDataTypeException;
	List<Vaccinations> getAllVaccinationsAvailable();
	List<Vaccinations> getAllVaccinationsUnavailable();
	SuccessResponse addVaccinationService(Vaccinations vaccinations) throws VaccinationInvalidInputException, MismatchDataTypeException;
	SuccessResponse updateVaccinationService(int vaccinationId,Vaccinations updatedVaccinationService) throws VaccinationsIdNotFoundException,MismatchDataTypeException;
	 
}
