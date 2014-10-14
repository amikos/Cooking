package kz.amikos.cooking.web.controller;

import java.util.List;

import kz.amikos.cooking.web.models.Reciept;
import kz.amikos.cooking.web.models.User;
import kz.amikos.cooking.core.provider.CustomAuthenticationProvider;
import kz.amikos.cooking.core.service.reciept.RecieptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecieptController {

	@Autowired
	RecieptService recieptService;
	

	@RequestMapping(value = { "/reciept/myReciepts" }, method = RequestMethod.GET)
	public ModelAndView myRecieptList() {
		
		ModelAndView model = new ModelAndView();
		
		User currentUser = CustomAuthenticationProvider.getAuthenticatedUser();
		
		model.addObject("myRecieptList", recieptService.getUserReciepts(currentUser));
		
		model.setViewName("/reciept/myReciepts");
		return model;

	}
	
	@RequestMapping(value = { "/reciept/allReciepts" }, method = RequestMethod.GET)
	public ModelAndView allRecieptList() {
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("allRecieptList", recieptService.getAllReciepts());
		
		model.setViewName("/reciept/allReciepts");
		return model;

	}
	
	@RequestMapping(value = { "/reciept/recieptForm" }, method = RequestMethod.GET)
	public String recieptForm() {
		
		return "/reciept/recieptForm";

	}
	
	@RequestMapping(value = { "/reciept/addReciept" }, method = RequestMethod.POST)
	public String addReciept(@ModelAttribute("reciept") Reciept reciept) {
		
		//TODO Validate reciept
		System.out.println("Name=" + reciept.getRecieptName());
		System.out.println("Description=" + reciept.getRecieptDescription());
		
		reciept.setUsername(CustomAuthenticationProvider.getAuthenticatedUser().getUsername());
		
		recieptService.addReciept(reciept);
		
		return "redirect:/reciept/myReciepts";
	}
	
	@ModelAttribute("reciept")
	private Reciept getReciept() {
		return new Reciept();
	}
	
//	@ModelAttribute("myRecieptList")
//	private List<Reciept> getMyRecieptList() {
//		return recieptService.getUserReciepts(CustomAuthenticationProvider.getAuthenticatedUser());
//	}
//	
//	@ModelAttribute("allRecieptList")
//	private List<Reciept> getAllRecieptList() {
//		return recieptService.getAllReciepts();
//	}

}
