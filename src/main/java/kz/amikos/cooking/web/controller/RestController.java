package kz.amikos.cooking.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kz.amikos.cooking.core.service.user.UserService;
import kz.amikos.cooking.web.models.UserRole;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	UserService userService;

    @RequestMapping("/roles")
    public List<UserRole> greeting(@RequestParam(value="username", required=true) String username) {
    	List<UserRole> roles = (List<UserRole>) userService.loadUserByUsername(username).getUserRole();
    	System.out.println(roles);
    	return roles;
    }
}
