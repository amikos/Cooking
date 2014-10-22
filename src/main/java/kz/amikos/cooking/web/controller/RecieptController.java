package kz.amikos.cooking.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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
	public void getImage(HttpServletResponse response, @RequestParam("id") final Integer id) throws IOException {
		
		if (id != 0) { 
		    response.setContentType("image/jpeg");
		    Image image = imageService.getImage(id);
		    
		    response.getOutputStream().write(image.getImageByte());
		    response.getOutputStream().flush();
		}
	}
	
	@RequestMapping(value = { "/reciept/allReciepts" }, method = RequestMethod.GET)
	public ModelAndView allRecieptList() {
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("allRecieptList", recieptService.getAllReciepts());
		
		model.setViewName("/reciept/allReciepts");
		return model;

	}
	
	@RequestMapping(value = { "/reciept/newReciept" }, method = RequestMethod.GET)
	public String recieptForm() {
		
		return "/reciept/newReciept";

	}
	
	@RequestMapping(value = { "/reciept/addReciept" }, method = RequestMethod.POST)
	public String addReciept(@ModelAttribute("reciept") Reciept reciept, @RequestParam("file") MultipartFile file) {
		
		byte[] imageByte = null;
		if (!file.isEmpty()) {
            try {
            	imageByte = file.getBytes();
            } catch (Exception e) {
            	e.printStackTrace();
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
            	
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
	
	@ModelAttribute("reciept")
	private Reciept getReciept() {
		return new Reciept();
	}
	
}
