package kz.amikos.cooking.core.service.user;

import java.util.List;

import kz.amikos.cooking.web.models.User;

public interface UserService {
	
	void addUser(User user);
	
	List<User> getAllUsers();
	
}
