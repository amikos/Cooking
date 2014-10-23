package kz.amikos.cooking.core.transaction.reciept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import kz.amikos.cooking.core.service.image.ImageService;
import kz.amikos.cooking.web.models.Image;
import kz.amikos.cooking.web.models.Reciept;
import kz.amikos.cooking.web.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class RecieptDAOImpl extends JdbcDaoSupport implements RecieptDAO{
	
	private String INSERT_RECIEPT_DAO = "insert into user_reciepts (username, reciept_name, reciept_description) values (?, ?, ?)";
	private String SELECT_RECIEPT_DAO = "select reciept_id, username, reciept_name, reciept_description from user_reciepts";
	
	@Autowired
	ImageService imageService;
	
	@Override
	public Reciept getReciept(int id) {
		
		return getJdbcTemplate().query(SELECT_RECIEPT_DAO + " where reciept_id=" + id + "", new ResultSetExtractor<Reciept>() {
	        @Override
	        public Reciept extractData(ResultSet rs) throws SQLException, DataAccessException  {
	        	if (rs.next()) {
	        		Reciept reciept = new Reciept();
	        		reciept.setRecieptId(rs.getInt("reciept_id"));
	        		reciept.setRecieptDescription(rs.getString("reciept_description"));
	        		reciept.setRecieptName(rs.getString("reciept_name"));
	        		
	        		return reciept;
	        	}
	        	return null;
	        }
	    });
		
//		User user = (User) getJdbcTemplate().queryForObject(SELECT_USER_DAO + " where username='" + userName + "'", new BeanPropertyRowMapper<User>(User.class));
	}

	public int addReciept(final Reciept reciept) {
		
		//TODO Change
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	            PreparedStatement ps =
	                connection.prepareStatement(INSERT_RECIEPT_DAO, Statement.RETURN_GENERATED_KEYS);
	            ps.setString(1, reciept.getUsername());
	            ps.setString(2, reciept.getRecieptName());
	            ps.setString(3, reciept.getRecieptDescription());
	            return ps;
	        }
		};
		
		final KeyHolder keyHolder = new GeneratedKeyHolder();
	        
		getJdbcTemplate().update(psc, keyHolder);
				
		return Integer.parseInt(keyHolder.getKeys().get("reciept_id").toString());
	}

	public List<Reciept> getUserReciepts(User user) {
		return getJdbcTemplate().query(SELECT_RECIEPT_DAO + " where username='" + user.getUsername() +"'", new RowMapper<Reciept>() {
			public Reciept mapRow(ResultSet rs, int rowNum) throws SQLException {
				Reciept reciept = new Reciept();
				reciept.setRecieptId(rs.getInt("reciept_id"));
				reciept.setUsername(rs.getString("username"));
				reciept.setRecieptName(rs.getString("reciept_name"));
				reciept.setRecieptDescription(rs.getString("reciept_description"));
				
				List<Image> images = imageService.getImagesByRecieptId(reciept.getRecieptId());
				reciept.setImages(images);
				
				return reciept;
			}}
		);
	}

	@Override
	public List<Reciept> getAllReciepts() {
		return getJdbcTemplate().query(SELECT_RECIEPT_DAO, new RowMapper<Reciept>() {
			public Reciept mapRow(ResultSet rs, int rowNum) throws SQLException {
				Reciept reciept = new Reciept();
				reciept.setRecieptId(rs.getInt("reciept_id"));
				reciept.setUsername(rs.getString("username"));
				reciept.setRecieptName(rs.getString("reciept_name"));
				reciept.setRecieptDescription(rs.getString("reciept_description"));
				
				List<Image> images = imageService.getImagesByRecieptId(reciept.getRecieptId());
				reciept.setImages(images);
				
				return reciept;
			}}
		);
	}
	
}
