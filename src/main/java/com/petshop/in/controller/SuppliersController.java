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
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierInputInvalidException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierNameNotFoundException;
import com.petshop.in.model.Suppliers;
import com.petshop.in.service.SuppliersService;
import com.petshop.in.serviceimpl.SuppliersServiceImpl;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SuppliersController {
	@Autowired
	SuppliersService supservice;
	//Retrieves all suppliers
	@GetMapping
	public List<Suppliers> retreiveAllSuppliers()
	{
		return supservice.getAllSuppliers();
	}
	//retrieve suppliers using supplier Id
	@GetMapping("/{supplierId}")
	public Suppliers retrieveSupplierBySupplierId(@PathVariable("supplierId") Integer supplierId)
	{
		
		
		return supservice.getSupplierById(supplierId);
		
	}
	//Retrieve suppliers by name
	@GetMapping("/name/{name}")
	public List<Suppliers> retrieveSupplierBySupplierName(@PathVariable("name") String name) throws SupplierNameNotFoundException, MismatchDataTypeException 
	{
		
			return supservice.getSuppliersByName(name);
		
		
	}
	//Retrieve suppliers by city name
	 @GetMapping("/city/{cityName}")
	    public List<Suppliers> retrieveSuppliersByCity(@PathVariable("cityName") String cityName) throws SupplierNameNotFoundException, MismatchDataTypeException {
	        return supservice.getSuppliersByCity(cityName);
	    }
	//Retrieve suppliers by state
	 @GetMapping("/state/{state}")
	    public List<Suppliers> retrieveSuppliersByState(@PathVariable("state") String state) throws MismatchDataTypeException {
	        return supservice.getSuppliersByState(state);
	    }
	 //Adds new supplier
	 @PostMapping("/add")
	 public SuccessResponse addSupplier(@RequestBody Suppliers supplier) throws SupplierInputInvalidException, MismatchDataTypeException
	 {
		return supservice.addSupplier(supplier);
		 
	 }
	 //Updates the existing supplier
	 @PutMapping("/update/{supplier-id}")
	 public SuccessResponse  updateSupplier(@PathVariable("supplier-id") Integer supplierId,@RequestBody Suppliers supplier) throws MismatchDataTypeException
	 {
		
		return supservice.updateSupplierById(supplierId, supplier);
		 
	 }
	
}
