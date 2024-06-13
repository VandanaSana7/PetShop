package com.petshop.in.controller.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressIdNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressNotFoundException;
import com.petshop.in.model.Addresses;
import com.petshop.in.repository.AddressRepository;
import com.petshop.in.service.AddressService;

@Controller
public class AddressControllerThymleaf {
	@Autowired
	private AddressService addressService;
	@Autowired
	private AddressRepository addrepo;

	@GetMapping("/api1/v1/addresses/Form")
	public String showAddressIdForm() {
		return "AddressIdForm";
	}

	@GetMapping("/api1/v1/addresses")
	public String getAllAddresses(Model model) throws AddressIdNotFoundException {

		List<Addresses> AddressesList = addressService.getAllAddresses();
		model.addAttribute("AllAddresses", AddressesList);
		return "AddressesList";
	}

	@GetMapping("/api1/v1/addresses/id")
	public String getAddressesById(@RequestParam("addressId") Integer addressId, Model model)
			throws AddressIdNotFoundException {
		Addresses address = addressService.getAddressById(addressId);
		model.addAttribute("AddressById", address);
		return "AddressById";

	}

	@GetMapping("/api1/v1/addresses/postform")
	public String showPostAddressForm() {
		return "postaddressform";
	}

	@PostMapping("/api1/v1/addresses/add")
	public String addAddress(Model model, @ModelAttribute("post_address") Addresses address)
			throws AddressNotFoundException, MismatchDataTypeException {
		SuccessResponse sucres = addressService.addAddress(address);
		model.addAttribute("post_address", sucres);

		return "postaddress";

	}

	@GetMapping("/api1/v1/addresses/updateform")
	public String showPostAddressupdateForm() {
		return "UpdateAddressIdform";
	}

	@GetMapping("/api1/v1/addresses/update")
	public String updateAddress(@RequestParam("addressId") Integer addressId, Model model)
			throws MismatchDataTypeException, AddressIdNotFoundException {
		Addresses address = addressService.getAddressById(addressId);
		model.addAttribute("AddressId", address);
		return "UpdateAddressForm";

	}

	@GetMapping("/api1/v1/addresses/updatedetails")
	public String updateAddressform(@RequestParam("addressId") Integer addressId, Model model,
			@ModelAttribute("updateAddress") Addresses address) throws MismatchDataTypeException {
		SuccessResponse sucres = addressService.updateAddress(addressId, address);
		model.addAttribute("updateaddress", sucres);

		return "UpdateAddress";

	}

}
