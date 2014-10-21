package kz.amikos.cooking.core.transaction.reciept;

import java.util.List;

import kz.amikos.cooking.web.models.Reciept;
import kz.amikos.cooking.web.models.User;

public interface RecieptDAO {

	public int addReciept(Reciept reciept);
	public List<Reciept> getUserReciepts(User user);
	public List<Reciept> getAllReciepts();
	
}
