package com.petshop.in.controller.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressIdNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierInputInvalidException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierNameNotFoundException;
import com.petshop.in.model.Addresses;
import com.petshop.in.model.Suppliers;
import com.petshop.in.service.SuppliersService;

@Controller
public class SupplierControllerThymleaf {
	@Autowired
	private SuppliersService supservice;

	// Form to get id from user
	@GetMapping("/api1/v1/suppliers/Idform")
	public String showSuppliersIdForm() {
		return "SupplierIdForm";
	}

	// to get all supplier details
	@GetMapping("/api1/v1/suppliers")
	public String retreiveAllSuppliers(Model model) {
		List<Suppliers> suppliers = supservice.getAllSuppliers();
		model.addAttribute("AllSuppliers", suppliers);
		return "AllSuppliers";
	}

	// retrieve suppliers using supplier Id
	@GetMapping("/api1/v1/suppliers/id")
	public String retrieveSupplierBySupplierId(@RequestParam("supplierId") Integer supplierId, Model model) {

		Suppliers supplier = supservice.getSupplierById(supplierId);
		model.addAttribute("SupplierById", supplier);
		return "SupplierById";

	}

	// form to get name from user
	@GetMapping("/api1/v1/suppliers/nameform")
	public String showSuppliersNameForm() {
		return "SupplierNameForm";
	}

	// retrieve supplier details by name
	@GetMapping("/api1/v1/suppliers/byname")
	public String retrieveSupplierBySupplierName(@RequestParam("SupplierName") String name, Model model)
			throws SupplierNameNotFoundException, MismatchDataTypeException {
		List<Suppliers> supplier = supservice.getSuppliersByName(name);
		model.addAttribute("SupplierName", supplier);
		return "SupplierByName";

	}

	// form to get supplier details by city
	@GetMapping("/api1/v1/suppliers/cityform")
	public String showSuppliersCityForm() {
		return "SupplierByCityForm";
	}

	// Retrieve suppliers by city name
	@GetMapping("/api1/v1/suppliers/bycity")
	public String retrieveSuppliersByCity(@RequestParam("cityName") String cityName, Model model)
			throws SupplierNameNotFoundException, MismatchDataTypeException {
		List<Suppliers> supplier = supservice.getSuppliersByCity(cityName);
		model.addAttribute("SupplierByCity", supplier);
		return "SupplierByCity";
	}

	// form to get supplier details using state
	@GetMapping("/api1/v1/suppliers/stateform")
	public String showSuppliersStateForm() {
		return "SupplierByStateForm";
	}

	// Retrieve suppliers by state
	@GetMapping("/api1/v1/suppliers/state")
	public String retrieveSuppliersByState(@RequestParam("state") String state, Model model)
			throws MismatchDataTypeException {
		List<Suppliers> supplier = supservice.getSuppliersByState(state);
		model.addAttribute("SupplierByState", supplier);
		return "SupplierByState";
	}

	// form to get all details to add supplier to table
	@GetMapping("/api1/v1/suppliers/addform")
	public String showSupplierspostForm() {
		return "SupplierPostForm";
	}

	// Adds new supplier
	@PostMapping("/api1/v1/suppliers/add")
	public String addSupplier(@ModelAttribute("supplier") Suppliers supplier, Model model)
			throws SupplierInputInvalidException, MismatchDataTypeException {
		SuccessResponse sucres = supservice.addSupplier(supplier);
		model.addAttribute("post_supplier", sucres);
		return "PostSupplier";

	}

	// form to get all details to add supplier to table
	@GetMapping("/home")
	public String showSuppliersHomePage() {
		return "HomePage";
	}

	@GetMapping("/api1/v1/suppliers/updates")
	public String showdetailsforupdate() {
		return "UpdateSupplierIdForm";
	}

	@GetMapping("/api1/v1/suppliers/update")
	public String updateAddress(@RequestParam("supplierId") Integer supplierId, Model model) {
		Suppliers supplier = supservice.getSupplierById(supplierId);
		model.addAttribute("supplierId", supplier);
		return "UpdateSupplierForm";
	}

	@GetMapping("/api1/v1/suppliers/updatedetails")
	public String updateAddressform(@RequestParam("supplierId") Integer supplierId, Model model,
			@ModelAttribute("updateSupplier") Suppliers supplier) throws MismatchDataTypeException {
		SuccessResponse sucres = supservice.updateSupplierById(supplierId, supplier);
		model.addAttribute("updateSupplier", sucres);

		return "UpdateSupplierDetails";

	}
}