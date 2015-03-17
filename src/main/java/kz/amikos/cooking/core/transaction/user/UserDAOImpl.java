package kz.amikos.cooking.core.transaction.user;

import java.util.List;

import kz.amikos.cooking.web.models.User;

import kz.amikos.cooking.web.models.UserRole;
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

	public void save(User user) {
		this.sessionFactory.getCurrentSession().persist(user);
	}

	public List<User> getAll() {
		return this.sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}

	public User getByUsername(String userName) {
		return (User) this.sessionFactory.getCurrentSession().load(User.class, userName);
	}
	
}
