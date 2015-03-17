package kz.amikos.cooking.core.transaction.receipt;

import kz.amikos.cooking.web.models.Receipt;
import kz.amikos.cooking.web.models.User;

import java.util.List;

public interface ReceiptDAO {

	void update(final Receipt receipt);

	Receipt getById(int id);

	Long save(Receipt receipt);

	List<Receipt> getAllByUser(User user);

	List<Receipt> getAll();
	
}
