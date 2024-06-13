package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Validationclass;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressIdNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressInputInvalidException;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressNotFoundException;
import com.petshop.in.model.Addresses;
import com.petshop.in.repository.AddressRepository;
import com.petshop.in.service.AddressService;
@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	private AddressRepository addressRepo;
	//Retrieve All Addresses from Addresses table
	@Override
	public List<Addresses> getAllAddresses() throws AddressIdNotFoundException
	{
		try
		{
			return addressRepo.findAll();
		}
		catch(AddressNotFoundException ex)
		{
			 throw new AddressIdNotFoundException("Validation Failed {Address list is Empty}");
		}
		
	}
	//Retrieves Address by given Id
	@Override
	public Addresses getAddressById(int address_id) throws AddressIdNotFoundException
	{
		try
		{
			
			Addresses address=addressRepo.findById(address_id).get();
			
			return address;
		}
		catch(NoSuchElementException ex)
		{
			 throw new AddressIdNotFoundException("Validation Failed {Id is not Found}");
		}
		
			
	}
	//Add new address into Addresses table using Post
	@Override
	public SuccessResponse addAddress(Addresses address) throws  MismatchDataTypeException
	{
		try
		{
			if(!Validationclass.ValidationInt(address.getStreet()))
			{
				throw new MismatchDataTypeException("Street should be string");
			}
			if(!Validationclass.ValidationInt(address.getCity()))
			{
				throw new MismatchDataTypeException("City should be string");
			}
			if(!Validationclass.ValidationInt(address.getState()))
			{
				throw new MismatchDataTypeException("State should be string");
			}
			if(Validationclass.ValidationInt(address.getZipCode()))
			{
				throw new MismatchDataTypeException("ZipCode should not be string");
			}
			
			Addresses addressobj =addressRepo.save(address);
			SuccessResponse sucres=new SuccessResponse();
			sucres.setMessage("Addresss Added Successfully\n"+"\n"+addressobj);
			sucres.setTimestamp(LocalDate.now());
			sucres.setStatus(HttpStatus.ACCEPTED.toString());
			return sucres;
		}
		catch(AddressNotFoundException ex)
		{
			throw new AddressNotFoundException("Validation Failed {Address is not valid}");
		}
		
	}
	//updates the addresses based on given id
	@Override
	
	public SuccessResponse updateAddress(int address_id,Addresses updatedAddress) throws MismatchDataTypeException
	{
		
		try
		{
			if(!Validationclass.ValidationInt(updatedAddress.getStreet()))
			{
				throw new MismatchDataTypeException("Street should be string");
			}
			if(!Validationclass.ValidationInt(updatedAddress.getCity()))
			{
				throw new MismatchDataTypeException("City should be string");
			}
			if(!Validationclass.ValidationInt(updatedAddress.getState()))
			{
				throw new MismatchDataTypeException("State should be string");
			}
			if(Validationclass.ValidationInt(updatedAddress.getZipCode()))
			{
				throw new MismatchDataTypeException("ZipCode should not be string");
			}
			if(updatedAddress.getStreet().isEmpty())
			{
				throw new MismatchDataTypeException("Street should not be null.{Existing data should not removed}");
			}
			if(updatedAddress.getCity().isEmpty())
			{
				throw new MismatchDataTypeException("City should not be null.{Existing data should not removed}");
			}
			if(updatedAddress.getState().isEmpty())
			{
				throw new MismatchDataTypeException("State should not be null.{Existing data should not removed}");
			}
			if(updatedAddress.getZipCode().isEmpty())
			{
				throw new MismatchDataTypeException("ZipCode should not be null.{Existing data should not removed}");
			}
			Addresses address=addressRepo.findById(address_id).get();
			if(address!=null)
			{
				address.setStreet(updatedAddress.getStreet());
				address.setCity(updatedAddress.getCity());
				address.setState(updatedAddress.getState());
				address.setZipCode(updatedAddress.getZipCode());
			}
			addressRepo.save(address);
			SuccessResponse sucres=new SuccessResponse();
			sucres.setMessage("Addresss updated Successfully\n"+address);
			sucres.setTimestamp(LocalDate.now());
			sucres.setStatus(HttpStatus.ACCEPTED.toString());
			return sucres;
		}
		catch(NoSuchElementException ex)
		{
			throw new AddressIdNotFoundException("Validation Failed {Invalid Id}");
		}
		
	}

}
