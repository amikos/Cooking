package kz.amikos.cooking.core.service.receipt;

import kz.amikos.cooking.web.models.Receipt;
import kz.amikos.cooking.web.models.User;

import java.util.List;

public interface ReceiptService {
	
	void updateReceipt(final Receipt receipt);

	Receipt getReceipt(int id);
		
	int addReceipt(Receipt receipt);
	
	List<Receipt> getUserReceipts(User user);
	
	List<Receipt> getAllReceipts();
	
}
