package kz.amikos.cooking.core.service.reciept;

import java.util.List;

import kz.amikos.cooking.web.models.Reciept;
import kz.amikos.cooking.web.models.User;
import kz.amikos.cooking.core.transaction.reciept.RecieptDAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecieptServiceImpl implements RecieptService {
	
	@Autowired
	RecieptDAOImpl recieptDAO;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int addReciept(Reciept reciept) {
		return recieptDAO.addReciept(reciept);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Reciept> getUserReciepts(User user) {
		return recieptDAO.getUserReciepts(user);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Reciept> getAllReciepts(){
		return recieptDAO.getAllReciepts();
	}
	
}
