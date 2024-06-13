package com.petshop.in.controller.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.groomingvaccination.GroomingServiceInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.ServiceAvailableException;
import com.petshop.in.exceptions.groomingvaccination.ServiceIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.ServiceUnavailableException;
import com.petshop.in.exceptions.groomingvaccination.SevicesNotFoundException;
import com.petshop.in.model.GroomingServices;
import com.petshop.in.serviceimpl.GroomingServiceImpl;

@Controller
@RequestMapping("/services")
public class GroomingServicesThymeleaf {

	@Autowired
	private GroomingServiceImpl groomingService;
	
//	@GetMapping("/home")
//	public String showHomePage()
//	{
//		return "HomePage";
//	}
	
	@GetMapping("/form")
	public String showServiceIdForm() {
		return "ServiceIdForm";
	}
	
	@GetMapping("/getAllServices")
	public String getAllGroomingServices(Model model) throws SevicesNotFoundException
	{
		List<GroomingServices> servicesList = groomingService.getAllGroomingServices();
		model.addAttribute("AllGroomingServices", servicesList);
		return "ServicesList";
	}
	
	@GetMapping("/getServiceId/serviceId")
	public String getServiceById(@RequestParam("serviceId") Integer serviceId, Model model) throws ServiceIdNotFoundException
	{
		GroomingServices service = groomingService.getGroomingServicesByServiceId(serviceId);
		model.addAttribute("ServiceById", service);
		return "ServiceById";
	}
	
	@GetMapping("/available")
	public String getAllAvailableServices(Model model) throws ServiceAvailableException, SevicesNotFoundException
	{
		List<GroomingServices> availableServices = groomingService.getAllGroomingServices();
		model.addAttribute("AllAvailableServices", availableServices);
		return "AvailableGroomingServices";
	}
	
	@GetMapping("/unavailable")
	public String getAllUnavailableServices(Model model) throws ServiceUnavailableException
	{
		List<GroomingServices> unavailableServices = groomingService.getUnavailableGroomingServices();
		model.addAttribute("AllUnavailableServices", unavailableServices);
		return "UnavailableGroomingServices";
	}
	
	@GetMapping("/postserviceform")
	public String showPostServiceForm()
	{
		return "PostServiceForm";
	}
	
	@PostMapping("/add")
	public String addGroomingService(Model model, @ModelAttribute("service") GroomingServices service) throws GroomingServiceInvalidInputException, MismatchDataTypeException
	{
		SuccessResponse s = groomingService.addGroomingService(service);
		model.addAttribute("AddService", s);
		return "PostAddService";
	}
	
	@GetMapping("/updateidform")
	public String showPostUpdateServiceIdForm() {
		return "UpdateServiceIdForm";
	}

	@GetMapping("/updateserviceform")
	public String updateService(@RequestParam("serviceId") Integer serviceId, Model model) throws ServiceIdNotFoundException {
		GroomingServices service = groomingService.getGroomingServicesByServiceId(serviceId);
		model.addAttribute("Service", service);
		return "UpdateServiceForm";

	}

	@GetMapping("/updatedetails")
	public String updateGroomingService(Model model, @ModelAttribute("updateService") GroomingServices service, 
			@RequestParam("serviceId") Integer serviceId) throws ServiceIdNotFoundException, MismatchDataTypeException {
		SuccessResponse s = groomingService.updateGroomingService(serviceId, service);
		model.addAttribute("UpdateService", s);

		return "UpdateService";

	}
	
	
	
}
