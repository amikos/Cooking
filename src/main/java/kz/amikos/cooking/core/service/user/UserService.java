package kz.amikos.cooking.core.service.user;

import java.util.List;

import kz.amikos.cooking.web.models.User;

public interface UserService {
	
	void save(User user);
	
	List<User> getAll();
	
	User getByUsername(String userName);
	
}
