package com.petshop.in.controller.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.pets.NoPetsFoundException;
import com.petshop.in.exceptions.pets.PetAlreadyExistsException;
import com.petshop.in.exceptions.pets.PetCannotBeAddedException;
import com.petshop.in.exceptions.pets.PetCategoryNotFoundException;
import com.petshop.in.exceptions.pets.PetIdNotFoundException;
import com.petshop.in.model.Pets;
import com.petshop.in.service.PetService;


@Controller
@RequestMapping("/pets")
public class PetsThymeleaf {
	
	@Autowired
	private PetService petService;
	
//	@Autowired
//	private PetGroomingRelationship petGrooming;

	@GetMapping("/getAllPets")
	public String getAllPets(Model model) throws NoPetsFoundException
	{
		List<Pets> petsList = petService.retreiveAllPets();
		model.addAttribute("AllPets", petsList);
		return "PetsList";
	}
	
	@GetMapping("/form")
	public String showPetIdForm() {
		return "PetIdForm";
	}
	
	@GetMapping("/getPetId/petId")
	public String getPetById(@RequestParam("petId") Integer petId, Model model) throws PetIdNotFoundException
	{
		Pets pet = petService.retreiveById(petId);
		model.addAttribute("PetById", pet);
		return "PetById";
	}
	
	@GetMapping("/categoryform")
	public String showPetByCategoryForm() {
		return "PetCategoryForm";
	}
	
	@GetMapping("/getPet/categoryname")
	public String getPetsByCategory(@RequestParam("categoryName") String categoryName, Model model) throws PetCategoryNotFoundException 
	{
		List<Pets> pet = petService.getPetsByCategory(categoryName);
		model.addAttribute("CategoryName", pet);
		return "PetCategoryByName";
	}
	
//	@GetMapping("/groomingservicesform")
//	public String showPetByGroomingServicesForm() {
//		return "GroomingServicesForm";
//	}
//	
//	@GetMapping("/getPet/groomingservices/petId")
//	public String getPetsByGroomingServices(@RequestParam("petId") Integer petId, Model model) throws NoGroomingServicesFoundException, PetIdNotFoundException
//	{
//		List<Object> pet = petService.getPetsByGroomingServices(petId);
//		model.addAttribute("ByGroomingService", pet);
//		return "PetsByGroomingServices";
//	}
	
	@GetMapping("/postpetsform")
	public String showPostPetForm()
	{
		return "PostPetsForm";
	}
	
	@PostMapping("/add")
	public String addPets(Model model, @ModelAttribute("pet") Pets pet) throws PetCannotBeAddedException, MismatchDataTypeException, PetAlreadyExistsException 
	{
		SuccessResponse s = petService.addPets(pet);
		model.addAttribute("AddPet", s);
		return "PostAddPet";
	}
	
	@GetMapping("/updatepetidform")
	public String showPostUpdatePetIdForm() {
		return "UpdatePetIdForm";
	}

	@GetMapping("/updatepetform")
	public String updatePet(@RequestParam("petId") Integer petId, Model model) throws PetIdNotFoundException {
		Pets pet = petService.retreiveById(petId);
		model.addAttribute("Pet", pet);
		return "UpdatePetForm";

	}

	@GetMapping("/updatepetdetails")
	public String updatePets(Model model, @ModelAttribute("updatePet") Pets pet, 
			@RequestParam("petId") Integer petId)  {
		SuccessResponse s = petService.updatePets(pet, petId);
		model.addAttribute("UpdatePet", s);

		return "UpdatePet";

	}
	
}
