package kz.amikos.cooking.core.transaction.image;

import java.util.List;

import kz.amikos.cooking.web.models.Image;

public interface ImageDAO {

	public Image getImage(int id);
	
	public List<Image> getImagesByRecieptId(int recieptId);
	
	public void addImage(Image image);
	
}
