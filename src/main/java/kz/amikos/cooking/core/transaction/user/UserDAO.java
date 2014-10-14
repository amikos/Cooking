package kz.amikos.cooking.core.transaction.user;

import java.util.List;

import kz.amikos.cooking.web.models.User;

public interface UserDAO {

	public void addUser(User user);
	public List<User> getAllUsers();
	public User loadUserByUsername(String userName);
}
