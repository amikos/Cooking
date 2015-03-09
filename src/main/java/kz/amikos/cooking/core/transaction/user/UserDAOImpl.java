package kz.amikos.cooking.core.transaction.user;

import java.util.List;

import kz.amikos.cooking.web.models.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
        List<User> usersList = session.createQuery("from Users").list();
        return usersList;
	}

	@Override
	public User loadUserByUsername(String userName) {
		Session session = this.sessionFactory.getCurrentSession();     
        User user = (User) session.load(User.class, userName);
        return user;
	}
	
}
