package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierIdNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierInputInvalidException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierNameNotFoundException;
import com.petshop.in.model.Suppliers;

public interface SuppliersService {
	List<Suppliers> getAllSuppliers();
	Suppliers getSupplierById(int supplierId) throws SupplierIdNotFoundException;
	List<Suppliers> getSuppliersByName(String name) throws SupplierNameNotFoundException,MismatchDataTypeException;
	List<Suppliers> getSuppliersByCity(String city) throws SupplierNameNotFoundException,MismatchDataTypeException;
	List<Suppliers> getSuppliersByState(String state) throws MismatchDataTypeException;
	SuccessResponse addSupplier(Suppliers supplier) throws SupplierInputInvalidException,MismatchDataTypeException;
	SuccessResponse updateSupplierById(int supplierId, Suppliers updatedSupplier)throws MismatchDataTypeException;

}
