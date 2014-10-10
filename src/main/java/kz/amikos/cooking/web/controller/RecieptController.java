package kz.amikos.cooking.web.controller;

import java.util.List;

import kz.amikos.cooking.web.models.Reciept;
import kz.amikos.cooking.web.models.User;
import kz.amikos.cooking.core.service.reciept.RecieptServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecieptController {

	@Autowired
	RecieptServiceImpl recieptService;
	
	@Autowired
	User currentUser;

	@RequestMapping(value = { "/reciept/recieptList" }, method = RequestMethod.GET)
	public ModelAndView recieptList() {
		
		ModelAndView model = new ModelAndView();
		
		List<Reciept> reciepts =  recieptService.getUserReciepts(currentUser);
		
		System.out.println(reciepts.size());

		model.addObject("recieptList", recieptService.getUserReciepts(currentUser));
		
		model.setViewName("/reciept/recieptList");
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
		
		reciept.setUsername("zhansar");
		
		recieptService.addReciept(reciept);
		
		return "redirect:/welcome";
	}
	
	@ModelAttribute("reciept")
	private Reciept getReciept() {
		return new Reciept();
	}
	
	@ModelAttribute("recieptList")
	private List<Reciept> getRecieptList() {
		return recieptService.getUserReciepts(currentUser);
	}

}
