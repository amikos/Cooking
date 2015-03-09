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
	public void updateReceipt(final Receipt receipt) {
		sessionFactory.getCurrentSession().merge(receipt);
	}
	
	@Override
	public Receipt getReceipt(int id) {
		return (Receipt) sessionFactory.getCurrentSession().get(Receipt.class, id);
	}

	@Override
	public int addReceipt(final Receipt receipt) {
		return (Integer) sessionFactory.getCurrentSession().save(receipt);
	}

	@Override
	public List<Receipt> getUserReceipts(User user) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Receipt.class);
		criteria.add(Restrictions.eq("username", user.getUsername()));
		return criteria.list();
	}

	@Override
	public List<Receipt> getAllReceipts() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Receipt.class);

		System.out.println(criteria.list().size());
		return criteria.list();
	}
	
}
