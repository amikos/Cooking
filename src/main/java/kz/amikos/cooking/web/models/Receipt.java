package kz.amikos.cooking.web.models;

import javax.persistence.*;

@Entity
@Table(name = "user_reciepts")
public class Receipt {
	@Id()
	@GeneratedValue()
	@Column(name = "reciept_id")
	private int receiptId;

	@Column(name = "username")
	private String username;

	@Column(name = "reciept_name")
	private String receiptName;

	@Column(name = "reciept_description")
	private String receiptDescription;

	@Column(name = "reciept_data")
	private String receiptData;

	@Column(name = "reciept_image")
	private String receiptImage;

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReceiptName() {
		return receiptName;
	}

	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}

	public String getReceiptDescription() {
		return receiptDescription;
	}

	public void setReceiptDescription(String receiptDescription) {
		this.receiptDescription = receiptDescription;
	}

	public String getReceiptData() {
		return receiptData;
	}

	public void setReceiptData(String receiptData) {
		this.receiptData = receiptData;
	}

	public String getReceiptImage() {
		return receiptImage;
	}

	public void setReceiptImage(String receiptImage) {
		this.receiptImage = receiptImage;
	}
}
