package kz.amikos.cooking.core.service.user;

import java.util.List;

import kz.amikos.cooking.web.models.User;
import kz.amikos.cooking.core.transaction.user.UserDAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAOImpl userDao;

	public void save(User user) {
		userDao.save(user);
	}
	public List<User> getAll() { return userDao.getAll(); }
	public User getByUsername(String userName) {
		return userDao.getByUsername(userName);
	}
	
}
