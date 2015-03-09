package kz.amikos.cooking.core.transaction.user;

import java.util.List;

import kz.amikos.cooking.web.models.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhansar
 */
@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
	}

	public List<User> getAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
        List<User> usersList = session.createCriteria(User.class).list();
        return usersList;
	}

	public User loadUserByUsername(String userName) {
		Session session = this.sessionFactory.getCurrentSession();     
        User user = (User) session.load(User.class, userName);
        return user;
	}
	
}
