package kz.amikos.cooking.core.transaction.receipt;

import kz.amikos.cooking.web.models.Receipt;
import kz.amikos.cooking.web.models.User;

import java.util.List;

public interface ReceiptDAO {

	public Receipt getReceipt(int id);
	public void updateReceipt(final Receipt receipt);
	public int addReceipt(Receipt receipt);
	public List<Receipt> getUserReceipts(User user);
	public List<Receipt> getAllReceipts();
	
}
