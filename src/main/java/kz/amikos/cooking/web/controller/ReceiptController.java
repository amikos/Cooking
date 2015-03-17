package kz.amikos.cooking.web.controller;

import kz.amikos.cooking.core.provider.CustomAuthenticationProvider;
import kz.amikos.cooking.core.service.comment.CommentService;
import kz.amikos.cooking.core.service.receipt.ReceiptService;
import kz.amikos.cooking.core.service.user.UserService;
import kz.amikos.cooking.web.models.Comment;
import kz.amikos.cooking.web.models.Receipt;
import kz.amikos.cooking.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@SessionAttributes(types = Receipt.class)
public class ReceiptController {

	@Autowired
	ReceiptService receiptService;

	@Autowired
	UserService userService;

	@Autowired
	CommentService commentService;

	@RequestMapping(value = { "/receipt/myreceipts" }, method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public ModelAndView myReceiptList() {

		ModelAndView model = new ModelAndView();

		User currentUser = userService.getByUsername(CustomAuthenticationProvider.getAuthenticatedUser().getUsername());

		model.addObject("myReceiptList", receiptService.getAllByUser(currentUser));
		model.setViewName("/receipt/myreceipts");

		return model;
	}

	@RequestMapping(value = { "/receipt/allreceipts" }, method = RequestMethod.GET)
	public ModelAndView allReceiptList() {

		ModelAndView model = new ModelAndView();

		model.addObject("allReceiptList", receiptService.getAll());

		model.setViewName("/receipt/allreceipts");
		return model;
	}

	@RequestMapping(value = { "/receipt/editreceipt" }, method = RequestMethod.GET)
	public ModelAndView editReceipt(@RequestParam("id") final Integer id) {

		ModelAndView model = new ModelAndView();

		Receipt receipt = receiptService.getById(id);

		model.addObject("receipt", receipt);

		model.setViewName("/receipt/editreceipt");
		return model;
	}

	@RequestMapping(value = { "/receipt/editreceipt" }, method = RequestMethod.POST)
	public String updateReceipt(Receipt receipt) {
		receiptService.update(receipt);
		return "redirect:/receipt/myreceipts";
	}

	@RequestMapping(value = { "/receipt/savecomment" }, method = RequestMethod.POST)
	public String saveComment(Receipt receipt, @RequestParam("commenttext") String commentText, Map<String, Object> model) {
		Comment comment = new Comment();
		comment.setText(commentText);
		comment.setReceipt(receipt);

		commentService.save(comment);

		model.put("id", receipt.getReceiptId());
		return "redirect:/receipt/editreceipt";
	}

	@RequestMapping(value = { "/receipt/newreceipt" }, method = RequestMethod.GET)
	public ModelAndView newReceipt() {

		ModelAndView model = new ModelAndView();

		model.addObject("receipt", new Receipt());

		model.setViewName("/receipt/newreceipt");
		return model;

	}

	@RequestMapping(value = { "/receipt/newreceipt" }, method = RequestMethod.POST)
	public String addReceipt(@ModelAttribute("receipt") Receipt receipt) {

        receipt.setUsername(CustomAuthenticationProvider.getAuthenticatedUser().getUsername());

		receiptService.save(receipt);

		return "redirect:/receipt/myreceipts";
	}
}
