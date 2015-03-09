package kz.amikos.cooking.core.service.receipt;

import kz.amikos.cooking.core.service.receipt.ReceiptService;
import kz.amikos.cooking.core.transaction.receipt.ReceiptDAOImpl;
import kz.amikos.cooking.web.models.Receipt;
import kz.amikos.cooking.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReceiptServiceImpl implements ReceiptService {
	
	@Autowired
	ReceiptDAOImpl receiptDAO;
	
	public int addReceipt(Receipt receipt) {
		return receiptDAO.addReceipt(receipt);
	}

	public List<Receipt> getUserReceipts(User user) {
		return receiptDAO.getUserReceipts(user);
	}
	
	public List<Receipt> getAllReceipts(){
		return receiptDAO.getAllReceipts();
	}

	public Receipt getReceipt(int id) {
		return receiptDAO.getReceipt(id);
	}

	public void updateReceipt(Receipt receipt) {
		receiptDAO.updateReceipt(receipt);
	}
	
}
