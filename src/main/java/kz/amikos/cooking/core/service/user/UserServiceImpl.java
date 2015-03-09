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
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public User loadUserByUsername(String userName) {
		return userDao.loadUserByUsername(userName);
	}
	
}
