package com.petshop.in.controller.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.groomingvaccination.VaccinationInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsNotFoundException;
import com.petshop.in.model.Vaccinations;
import com.petshop.in.repository.VaccinationRepository;
import com.petshop.in.service.VaccinationService;


@Controller
public class VaccinationControllerThymeleaf {
	@Autowired
	private VaccinationService vaccinationService;
	@Autowired
	private VaccinationRepository vacrepo;

	@GetMapping("/api1/v1/vaccinations/Form")
	public String showVaccinationIdForm() {
	    return "VaccinationIdForm"; 
	}

	@GetMapping("/api1/v1/vaccinations")
	public String getAllVaccinations(Model model) throws VaccinationsNotFoundException {
	    List<Vaccinations> allVaccinations = vaccinationService.getAllVaccinations();
	    model.addAttribute("AllVac", allVaccinations);
	    return "AllVaccinations";
	}

	@GetMapping("/api1/v1/vaccinations/id")
	public String getVaccinationsByServiceId(@RequestParam("vaccinationId") Integer vaccinationId, Model model)
	        throws VaccinationsIdNotFoundException, MismatchDataTypeException {
	    Vaccinations vaccination = vaccinationService.getVaccinationsByServiceId(vaccinationId);
	    model.addAttribute("VaccinationsById", vaccination); // Modified attribute name
	    return "VaccinationsById";
	}
	@GetMapping("/api1/v1/vaccinations/addform")
	public String showVaccinationspostForm() {
		return "VaccinationPostForm";
	}

	// Adds new supplier
	@PostMapping("/api1/v1/vaccinations/add")
	public String addVaccinationService(@ModelAttribute("vaccination") Vaccinations vaccination, Model model)
			throws VaccinationInvalidInputException, MismatchDataTypeException {
		SuccessResponse sucres = vaccinationService.addVaccinationService(vaccination);
		model.addAttribute("post_vaccination", sucres);
		return "VaccinationPost";

	}
	
	@GetMapping("/api1/v1/vaccinations/updates")
	public String showdetailsforupdate() {
		return "VaccinationUpdateById";
	}

	@GetMapping("/api1/v1/vaccinations/update")
	public String updateVaccination(@RequestParam("vaccinationId") Integer vaccinationId, Model model) throws VaccinationsIdNotFoundException, MismatchDataTypeException{
		Vaccinations vaccination = vaccinationService.getVaccinationsByServiceId(vaccinationId);
		model.addAttribute("vaccinationId", vaccination);
		return "VaccinationUpdateForm";
	}

	@GetMapping("/api1/v1/vaccinations/updatedetails")
	public String updateVaccinationform(@RequestParam("vaccinationId") Integer vaccinationId, Model model,
			@ModelAttribute("updateVaccination") Vaccinations vaccination) throws  VaccinationsIdNotFoundException,MismatchDataTypeException {
		SuccessResponse sucres = vaccinationService.updateVaccinationService(vaccinationId, vaccination);
		model.addAttribute("updateVaccination", sucres);
		return "UpdateVaccinationDetails";

	}
	
	@GetMapping("/api1/v1/vaccinations/available")
	public String getAllAvailableVaccinations(@ModelAttribute("available") Vaccinations available,Model model){
	    List<Vaccinations> vaccination = vaccinationService.getAllVaccinationsAvailable();
	    model.addAttribute("AllAvailableVaccinations",vaccination );
	    return "VaccinationsAvailable";
	}
	
	@GetMapping("/api1/v1/vaccinations/unavailable")
	public String getAllUnavailableVaccinations(@ModelAttribute("unavailable") Vaccinations unavailable,Model model){
	    List<Vaccinations> vaccination = vaccinationService.getAllVaccinationsUnavailable();
	    model.addAttribute("AllUnavailableVaccinations",vaccination );
	    return "VaccinationsUnavailable";
	}
	
}