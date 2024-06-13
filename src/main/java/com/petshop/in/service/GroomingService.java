package com.petshop.in.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.groomingvaccination.GroomingServiceInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.ServiceIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.SevicesNotFoundException;
import com.petshop.in.model.GroomingServices;

public interface GroomingService {
	List<GroomingServices> getAllGroomingServices() throws SevicesNotFoundException;
	GroomingServices getGroomingServicesByServiceId(int serviceId) throws ServiceIdNotFoundException ;
	List<GroomingServices> getAvailableGroomingServices() throws SevicesNotFoundException ;
	List<GroomingServices> getUnavailableGroomingServices() throws SevicesNotFoundException;
	SuccessResponse addGroomingService(GroomingServices groomingservices) throws GroomingServiceInvalidInputException,MismatchDataTypeException;
	SuccessResponse updateGroomingService(int serviceId,GroomingServices updatedgroomingService) throws ServiceIdNotFoundException, MismatchDataTypeException  ;
}
