package kz.amikos.cooking.core.transaction.reciept;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kz.amikos.cooking.web.models.Reciept;
import kz.amikos.cooking.web.models.User;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class RecieptDAOImpl extends JdbcDaoSupport implements RecieptDAO{
	
	private String INSERT_RECIEPT_DAO = "insert into user_reciepts (username, reciept_name, reciept_description) values (?, ?, ?)";
	private String SELECT_RECIEPT_DAO = "select username, reciept_name, reciept_description from user_reciepts";

	public void addReciept(Reciept reciept) {
		getJdbcTemplate().update(INSERT_RECIEPT_DAO, reciept.getUsername(), reciept.getRecieptName(), reciept.getRecieptDescription());
	}

	public List<Reciept> getUserReciepts(User user) {
		return getJdbcTemplate().query(SELECT_RECIEPT_DAO + " where username='" + user.getUsername() +"'", new RowMapper<Reciept>() {
			public Reciept mapRow(ResultSet rs, int rowNum) throws SQLException {
				Reciept reciept = new Reciept();
				reciept.setUsername(rs.getString("username"));
				reciept.setRecieptName(rs.getString("reciept_name"));
				reciept.setRecieptDescription(rs.getString("reciept_description"));
				return reciept;
			}}
		);
	}

	@Override
	public List<Reciept> getAllReciepts() {
		return getJdbcTemplate().query(SELECT_RECIEPT_DAO, new RowMapper<Reciept>() {
			public Reciept mapRow(ResultSet rs, int rowNum) throws SQLException {
				Reciept reciept = new Reciept();
				reciept.setUsername(rs.getString("username"));
				reciept.setRecieptName(rs.getString("reciept_name"));
				reciept.setRecieptDescription(rs.getString("reciept_description"));
				return reciept;
			}}
		);
	}
}
