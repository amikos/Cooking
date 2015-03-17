package kz.amikos.cooking.core.transaction.user;

import java.util.List;

import kz.amikos.cooking.web.models.User;

public interface UserDAO {

	public void save(User user);
	public List<User> getAll();
	public User getByUsername(String userName);
}
