package kz.amikos.cooking.web.models;

public class Image {
	private int image_id;
	private String imageName;
	private byte[] imageByte;
	private int reciept_id;
	
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public int getReciept_id() {
		return reciept_id;
	}
	public void setReciept_id(int reciept_id) {
		this.reciept_id = reciept_id;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public byte[] getImageByte() {
		return imageByte;
	}
	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}
	
}
