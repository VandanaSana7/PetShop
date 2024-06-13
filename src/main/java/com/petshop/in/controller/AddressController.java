package com.petshop.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressIdNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressNotFoundException;
import com.petshop.in.model.*;
import com.petshop.in.service.AddressService;
import com.petshop.in.serviceimpl.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public List<Addresses> getAllAddresses() 
	{
		return addressService.getAllAddresses();
		
	}
	@GetMapping("/{addressId}")
	public Addresses getAddressesById(@PathVariable("addressId") Integer addressId) 
	{
		return addressService.getAddressById(addressId);
		
	}
	@PostMapping("/add")
	public SuccessResponse addAddress(@RequestBody Addresses address) throws MismatchDataTypeException 
	{
		return addressService.addAddress(address);
		
	}
	@PutMapping("/update/{address_id}")
	public SuccessResponse updateAddress(@PathVariable("address_id") Integer address_id,@RequestBody Addresses address) throws AddressIdNotFoundException, MismatchDataTypeException 
	{
		return addressService.updateAddress(address_id,address);
		
	}

}
