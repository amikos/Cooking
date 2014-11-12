package kz.amikos.cooking.web.controller;

import java.io.IOException;

import kz.amikos.cooking.web.models.Image;
import kz.amikos.cooking.web.models.Reciept;
import kz.amikos.cooking.web.models.User;
import kz.amikos.cooking.core.provider.CustomAuthenticationProvider;
import kz.amikos.cooking.core.service.image.ImageService;
import kz.amikos.cooking.core.service.reciept.RecieptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecieptController {

	@Autowired
	RecieptService recieptService;
	
	@Autowired
	ImageService imageService;

	@RequestMapping(value = { "/reciept/myReciepts" }, method = RequestMethod.GET)
	public ModelAndView myRecieptList() {
		 
		ModelAndView model = new ModelAndView();
		
		User currentUser = CustomAuthenticationProvider.getAuthenticatedUser();
		
		model.addObject("myRecieptList", recieptService.getUserReciepts(currentUser));
		
		model.setViewName("/reciept/myReciepts");
		return model;

	}
	
	@RequestMapping("/getImage{id}")
	public @ResponseBody byte[] getImage(@RequestParam("id") final Integer id) throws IOException {
		Image image = imageService.getImage(id);
		System.out.println("lenght=" + image.getImageByte().length);
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
		
		model.addObject("reciept", recieptService.getReciept(id));
		
		model.setViewName("/reciept/editReciept");
		return model;

	}
	
	@RequestMapping(value = { "/reciept/editReciept" }, method = RequestMethod.POST)
	public String editReciept(@ModelAttribute("reciept") Reciept reciept, @RequestParam("file") MultipartFile file) {
		recieptService.updateReciept(reciept);
		
		System.out.println(reciept.getRecieptId());
		
		System.out.println(reciept.getRecieptName());
		
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
	public String addReciept(@ModelAttribute("reciept") Reciept reciept, @RequestParam("file") MultipartFile file) {
		
		byte[] imageByte = null;
		if (!file.isEmpty()) {
            try {
            	imageByte = file.getBytes();
            } catch (Exception e) {
            	e.printStackTrace();
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
            
            System.out.println("image byte[] " + imageByte.length);
            	
            reciept.setUsername(CustomAuthenticationProvider.getAuthenticatedUser().getUsername());
            
            Image image = new Image();
            image.setImageByte(imageByte);
            image.setImageName(file.getName());
            
    		int recieptId = recieptService.addReciept(reciept);
    		
    		image.setReciept_id(recieptId);
    		
    		imageService.addImage(image);
    		
    		return "redirect:/reciept/myReciepts";
            
        } else {
            return "You failed to upload " + file.getName() + " because the file was empty.";
        }
		
	}
	
	
	
}
