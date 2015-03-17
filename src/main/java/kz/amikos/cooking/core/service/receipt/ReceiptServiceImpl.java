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
	
	public Long save(Receipt receipt) {
		return receiptDAO.save(receipt);
	}

	public List<Receipt> getAllByUser(User user) {
		return receiptDAO.getAllByUser(user);
	}
	
	public List<Receipt> getAll(){
		return receiptDAO.getAll();
	}

	public Receipt getById(int id) {
		return receiptDAO.getById(id);
	}

	public void update(Receipt receipt) {
		receiptDAO.update(receipt);
	}
	
}
