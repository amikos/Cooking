package kz.amikos.cooking.core.transaction.receipt;

import kz.amikos.cooking.web.models.Receipt;
import kz.amikos.cooking.web.models.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReceiptDAOImpl implements ReceiptDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void update(final Receipt receipt) {
		sessionFactory.getCurrentSession().merge(receipt);
	}
	
	@Override
	public Receipt getById(int id) {
		return (Receipt) sessionFactory.getCurrentSession().get(Receipt.class, id);
	}

	@Override
	public Long save(final Receipt receipt) {
		return (Long) sessionFactory.getCurrentSession().save(receipt);
	}

	@Override
	public List<Receipt> getAllByUser(User user) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Receipt.class);
		criteria.add(Restrictions.eq("username", user.getUsername()));
		return criteria.list();
	}

	@Override
	public List<Receipt> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Receipt.class);

		return criteria.list();
	}
	
}
