package kz.amikos.cooking.web.controller;

import java.io.IOException;

import kz.amikos.cooking.web.models.Image;
import kz.amikos.cooking.web.models.Reciept;
import kz.amikos.cooking.web.models.User;
import kz.amikos.cooking.core.provider.CustomAuthenticationProvider;
import kz.amikos.cooking.core.service.image.ImageService;
import kz.amikos.cooking.core.service.reciept.RecieptService;
import kz.amikos.cooking.core.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecieptController {

	@Autowired
	RecieptService recieptService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ImageService imageService;

	@RequestMapping(value = { "/reciept/myReciepts" }, method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public ModelAndView myRecieptList() {
		 
		ModelAndView model = new ModelAndView();
		
		User currentUser = userService.loadUserByUsername(CustomAuthenticationProvider.getAuthenticatedUser().getUsername());
		
		model.addObject("myRecieptList", recieptService.getUserReciepts(currentUser));
		
		model.setViewName("/reciept/myReciepts");
		return model;

	}
	
	@RequestMapping("/getImage{id}")
	public @ResponseBody byte[] getImage(@RequestParam("id") final Integer id) throws IOException {
		Image image = imageService.getImage(id);
	    return image.getImageByte();
	}
	
	@RequestMapping(value = { "/reciept/allReciepts" }, method = RequestMethod.GET)
	public ModelAndView allRecieptList() {
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("allRecieptList", recieptService.getAllReciepts());
		
		model.setViewName("/reciept/allReciepts");
		return model;

	}
	
	@RequestMapping(value = { "/reciept/editReciept{id}" }, method = RequestMethod.GET)
	public ModelAndView editReciept(@RequestParam("id") final Integer id) {
		
		ModelAndView model = new ModelAndView();
		
		Reciept reciept = recieptService.getReciept(id);
		
		model.addObject("reciept", reciept);
		
		model.setViewName("/reciept/editReciept");
		return model;

	}
	
	@RequestMapping(value = { "/reciept/editReciept" }, method = RequestMethod.POST)
	public String editReciept(@ModelAttribute("reciept") Reciept reciept) {
		recieptService.updateReciept(reciept);
		
		System.out.println(reciept.getRecieptData().toString());
		
		System.out.println("reciept.getRecieptImage()=" + reciept.getRecieptImage());
		
		return "redirect:/reciept/myReciepts";
	}
	
	@RequestMapping(value = { "/reciept/newReciept" }, method = RequestMethod.GET)
	public ModelAndView recieptForm() {
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("reciept", new Reciept());
		
		model.setViewName("/reciept/newReciept");
		return model;

	}
	
	@RequestMapping(value = { "/reciept/newReciept" }, method = RequestMethod.POST)
	public String addReciept(@ModelAttribute("reciept") Reciept reciept) {
		
        reciept.setUsername(CustomAuthenticationProvider.getAuthenticatedUser().getUsername());
        
		recieptService.addReciept(reciept);
		
		return "redirect:/reciept/myReciepts";
		
	}
	
	
	
}
