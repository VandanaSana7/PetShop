package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Validationclass;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierCityNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierIdNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierInputInvalidException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierNameNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierNotFoundException;
import com.petshop.in.model.Suppliers;
import com.petshop.in.repository.SuppliersRepository;
import com.petshop.in.service.SuppliersService;
@Service
public class SuppliersServiceImpl implements SuppliersService{
	@Autowired
	SuppliersRepository suppliersRepo;
	
	@Override
	public List<Suppliers> getAllSuppliers()
	{
		List<Suppliers> suppliers=suppliersRepo.findAll();
		if(suppliers.isEmpty())
			throw new SupplierNotFoundException("Validation Failed {No suppliers are present}");
		return suppliers; 
		
	}
	@Override
	public Suppliers getSupplierById(int supplierId) throws SupplierIdNotFoundException
	{
		try
		{
			Suppliers supplier=suppliersRepo.findById(supplierId).get();
			return supplier;
		}
		catch(NoSuchElementException  ex)
		{
			throw new SupplierIdNotFoundException("Validation Failed. {Id not found}");
		}
		
	}
	
	@Override
	public List<Suppliers> getSuppliersByName(String name) throws SupplierNameNotFoundException, MismatchDataTypeException
	{
		if(!Validationclass.ValidationInt(name))
		{
			throw new MismatchDataTypeException("Name should be String");
		}
			List<Suppliers> supplier=suppliersRepo.findByName(name);
			if(supplier.isEmpty())
				throw new SupplierNameNotFoundException("Validation Failed.{Supplier with this name not found}");
			return supplier;
		
	}
	
	@Override
	public List<Suppliers> getSuppliersByCity(String city) throws SupplierNameNotFoundException, MismatchDataTypeException 
	{
		if(!Validationclass.ValidationInt(city))
		{
			throw new MismatchDataTypeException("Name should be String");
		}
		List<Suppliers> supplier=suppliersRepo.findByCity(city);
		if(supplier.isEmpty())
			throw new SupplierNameNotFoundException("Validation Failed");
		return supplier;
	}
	
	@Override
	public List<Suppliers> getSuppliersByState(String state) throws MismatchDataTypeException 
	{
		if(!Validationclass.ValidationInt(state))
		{
			throw new MismatchDataTypeException("Name should be String");
		}
		List<Suppliers> supplier=suppliersRepo.findByState(state);
		if(supplier.isEmpty())
			throw new SupplierCityNotFoundException("Validation Failed.{Supplier with this city is not found}");
		return supplier;
	}
	
	@Override
	public SuccessResponse addSupplier(Suppliers supplier) throws SupplierInputInvalidException, MismatchDataTypeException{
		try
		{
			if(!Validationclass.ValidationInt(supplier.getName()))
			{
				throw new MismatchDataTypeException("Name should be String");
			}
			if(!Validationclass.ValidationInt(supplier.getContactPerson()))
			{
				throw new MismatchDataTypeException("ContactPerson should be String");
			}
			if(!Validationclass.ValidationInt(supplier.getEmail()))
			{
				throw new MismatchDataTypeException("Email should be String");
			}
			Suppliers supplierobj=suppliersRepo.save(supplier);
			SuccessResponse sucres=new SuccessResponse();
			sucres.setMessage("Supplier Added Successfully\n"+supplierobj);
			sucres.setTimestamp(LocalDate.now());
			sucres.setStatus(HttpStatus.ACCEPTED.toString());
			return sucres;
		}
		catch(Exception e)
		{
			throw new SupplierInputInvalidException("Validation Failed {Invalid Input}");
		}
        
    }
	
	@Override
	public SuccessResponse updateSupplierById(int supplierId, Suppliers updatedSupplier) throws MismatchDataTypeException
	{
		try
		{
			if(!Validationclass.ValidationInt(updatedSupplier.getName()))
			{
				throw new MismatchDataTypeException("Name should be String");
			}
			if(!Validationclass.ValidationInt(updatedSupplier.getContactPerson()))
			{
				throw new MismatchDataTypeException("ContactPerson should be String");
			}
			if(!Validationclass.ValidationInt(updatedSupplier.getEmail()))
			{
				throw new MismatchDataTypeException("Email should be String");
			}
			if(updatedSupplier.getName().isEmpty())
			{
				throw new MismatchDataTypeException("Name should not be null.{Existing data should not removed}");
			}
			if(updatedSupplier.getPhoneNumber().isEmpty())
			{
				throw new MismatchDataTypeException("PhoneNumber should not be null.{Existing data should not removed}");
			}
			if(updatedSupplier.getEmail().isEmpty())
			{
				throw new MismatchDataTypeException("Email should not be null.{Existing data should not removed}");
			}
			
			Suppliers supplier=suppliersRepo.findById(supplierId).get();
			if(supplier!=null)
			{
				supplier.setName(updatedSupplier.getName());
				supplier.setAddress(updatedSupplier.getAddress());
				supplier.setContactPerson(updatedSupplier.getContactPerson());
				supplier.setEmail(updatedSupplier.getEmail());
				supplier.setPhoneNumber(updatedSupplier.getPhoneNumber());
			}
			suppliersRepo.save(supplier);
			
			SuccessResponse sucres=new SuccessResponse();
			sucres.setMessage("Supplier updated Successfully\n"+supplier);
			sucres.setTimestamp(LocalDate.now());
			sucres.setStatus(HttpStatus.ACCEPTED.toString());
			return sucres;
		}
		catch(NoSuchElementException ex)
		{
			throw new SupplierIdNotFoundException("Validation Failed {Id is not found}");
		}
		
		
        
    }
}
