package kz.amikos.cooking.core.service.image;

import java.util.List;

import kz.amikos.cooking.web.models.Image;

public interface ImageService {
		
	Image getImage(int id);
	
	List<Image> getImagesByRecieptId(int recieptId);
	
	void addImage(Image image);
	
	void addImages(List<Image> images);
}
