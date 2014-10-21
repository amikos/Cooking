package kz.amikos.cooking.core.transaction.image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kz.amikos.cooking.web.models.Image;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ImageDAOImpl extends JdbcDaoSupport implements ImageDAO{
	
	private String INSERT_IMAGE_DAO = "insert into images (reciept_id, image_name, image_byte) values (?, ?, ?)";
	private String SELECT_IMAGES_DAO = "select image_id, image_name, image_byte from images";

	@Override
	public void addImage(Image image) {
		getJdbcTemplate().update(INSERT_IMAGE_DAO, image.getReciept_id(), image.getImageName(), image.getImageByte());
	}
	
	@Override
	public Image getImage(int id) {
		Image image = null;
		try {
			//TODO Change
			image = getJdbcTemplate().query(SELECT_IMAGES_DAO + " where image_id=" + id, new ResultSetExtractor<Image>() {
		        @Override
		        public Image extractData(ResultSet rs) throws SQLException, DataAccessException  {
		        	rs.next();
		        	Image image = new Image();
		        	image.setImageName(rs.getString("image_name"));
		        	image.setImageByte(rs.getBytes("image_byte"));
		            return image;
		        }
		    });
//			image = (Image) getJdbcTemplate().querqueryForObject(SELECT_IMAGES_DAO + " where image_id=" + id, new BeanPropertyRowMapper<Image>(Image.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	@Override
	public List<Image> getImagesByRecieptId(int id) {
		return getJdbcTemplate().query(SELECT_IMAGES_DAO + " where reciept_id=" + id +"", new RowMapper<Image>() {
			public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
				Image image = new Image();
				image.setImage_id(rs.getInt("image_id"));
				image.setImageName(rs.getString("image_name"));
				image.setImageByte(rs.getBytes("image_byte"));
				return image;
			}}
		);
	}

}
