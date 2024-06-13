package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressIdNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressNotFoundException;
import com.petshop.in.model.Addresses;

public interface AddressService {
	List<Addresses> getAllAddresses() ;
	Addresses getAddressById(int address_id) ;
	SuccessResponse addAddress(Addresses address) throws MismatchDataTypeException;
	SuccessResponse updateAddress(int address_id,Addresses updatedAddress)throws MismatchDataTypeException,AddressIdNotFoundException;

}
