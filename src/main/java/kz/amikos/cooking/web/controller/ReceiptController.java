package kz.amikos.cooking.web.controller;

import kz.amikos.cooking.core.provider.CustomAuthenticationProvider;
import kz.amikos.cooking.core.service.receipt.ReceiptService;
import kz.amikos.cooking.core.service.user.UserService;
import kz.amikos.cooking.web.models.Receipt;
import kz.amikos.cooking.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReceiptController {

	@Autowired
	ReceiptService receiptService;

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/receipt/myreceipts" }, method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public ModelAndView myReceiptList() {

		ModelAndView model = new ModelAndView();

		User currentUser = userService.loadUserByUsername(CustomAuthenticationProvider.getAuthenticatedUser().getUsername());

		model.addObject("myReceiptList", receiptService.getUserReceipts(currentUser));
		model.setViewName("/receipt/myreceipts");

		return model;
	}

	@RequestMapping(value = { "/receipt/allreceipts" }, method = RequestMethod.GET)
	public ModelAndView allReceiptList() {

		ModelAndView model = new ModelAndView();

		model.addObject("allReceiptList", receiptService.getAllReceipts());

		model.setViewName("/receipt/allreceipts");
		return model;

	}

	@RequestMapping(value = { "/receipt/editreceipt{id}" }, method = RequestMethod.GET)
	public ModelAndView editReceipt(@RequestParam("id") final Integer id) {

		ModelAndView model = new ModelAndView();

		Receipt receipt = receiptService.getReceipt(id);

		model.addObject("receipt", receipt);

		model.setViewName("/receipt/editreceipt");
		return model;

	}

	@RequestMapping(value = { "/receipt/editreceipt" }, method = RequestMethod.POST)
	public String editReceipt(@ModelAttribute("receipt") Receipt receipt) {

		receiptService.updateReceipt(receipt);

		return "redirect:/receipt/myreceipts";
	}

	@RequestMapping(value = { "/receipt/newreceipt" }, method = RequestMethod.GET)
	public ModelAndView receiptForm() {

		ModelAndView model = new ModelAndView();

		model.addObject("receipt", new Receipt());

		model.setViewName("/receipt/newreceipt");
		return model;

	}

	@RequestMapping(value = { "/receipt/newreceipt" }, method = RequestMethod.POST)
	public String addReceipt(@ModelAttribute("receipt") Receipt receipt) {

        receipt.setUsername(CustomAuthenticationProvider.getAuthenticatedUser().getUsername());

		receiptService.addReceipt(receipt);

		return "redirect:/receipt/myreceipts";
		
	}
}
