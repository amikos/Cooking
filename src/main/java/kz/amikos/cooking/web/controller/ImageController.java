package kz.amikos.cooking.web.controller;

import java.util.List;

import kz.amikos.cooking.core.service.image.ImageService;
import kz.amikos.cooking.web.models.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImageController {
	
	@Autowired
	ImageService imageService;
	
	@RequestMapping(value = { "/images/byreciept{id}" }, method = RequestMethod.GET)
	public ModelAndView allRecieptList(@RequestParam("id") final Integer id) {
		
		ModelAndView model = new ModelAndView();
		
		List<Image> images = imageService.getImagesByRecieptId(id);
		
		model.addObject("images", images);
		
		model.setViewName("/reciept/byreciept");
		return model;

	}
}
