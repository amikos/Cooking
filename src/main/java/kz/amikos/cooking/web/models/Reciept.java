package kz.amikos.cooking.web.models;

import java.util.ArrayList;
import java.util.List;

public class Reciept {
	private int recieptId;
	private String username;
	private String recieptName;
	private String recieptDescription;
	private List<Image> images;
	private String recieptData;
	

	public String getRecieptData() {
		return recieptData;
	}

	public void setRecieptData(String recieptData) {
		this.recieptData = recieptData;
	}

	public int getFirstImageId(){
		if (images != null){
			if (!images.isEmpty()) {
				return images.get(0).getImage_id();
			}
		}
		return 0;
	}
	
	public void addImage(Image image){
		if (images == null) images = new ArrayList<Image>();
		images.add(image);
	}
	
	public int getRecieptId() {
		return recieptId;
	}

	public void setRecieptId(int recieptId) {
		this.recieptId = recieptId;
	}
	
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRecieptName() {
		return recieptName;
	}

	public void setRecieptName(String recieptName) {
		this.recieptName = recieptName;
	}

	public String getRecieptDescription() {
		return recieptDescription;
	}

	public void setRecieptDescription(String recieptDescription) {
		this.recieptDescription = recieptDescription;
	}
	
	
}
