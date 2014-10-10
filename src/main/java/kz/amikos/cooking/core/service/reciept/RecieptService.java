package kz.amikos.cooking.core.service.reciept;

import java.util.List;

import kz.amikos.cooking.web.models.Reciept;
import kz.amikos.cooking.web.models.User;

public interface RecieptService {
		
	void addReciept(Reciept reciept);
	
	List<Reciept> getUserReciepts(User user);

}
