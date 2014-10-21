package kz.amikos.cooking.core.service.image;

import java.util.List;

import kz.amikos.cooking.web.models.Image;
import kz.amikos.cooking.core.transaction.image.ImageDAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	ImageDAOImpl imageDAOImpl;
	
	@Override
	public Image getImage(int id) {
		return imageDAOImpl.getImage(id);
	}

	@Override
	public List<Image> getImagesByRecieptId(int recieptId) {
		return imageDAOImpl.getImagesByRecieptId(recieptId);
	}

	@Override
	public void addImage(Image image) {
		imageDAOImpl.addImage(image);
	}

	@Override
	public void addImages(List<Image> images) {
		for (Image image : images) {
			imageDAOImpl.addImage(image);
		}
	}
	
}
