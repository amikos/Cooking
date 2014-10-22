package kz.amikos.cooking.core.transaction.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kz.amikos.cooking.web.models.Role;
import kz.amikos.cooking.web.models.User;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by zhansar
 */
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {
	private static final String INSERT_USER_DAO = "insert into users values (?, ?, ?)";
	private static final String SELECT_USER_DAO = "select username, password, enabled from users";
	private static final String SELECT_ROLE_DAO = "select role from user_roles";

	public void addUser(User user) {
		getJdbcTemplate().update(INSERT_USER_DAO, user.getUsername(), user.getPassword(), user.isEnabled());
	}

	public List<User> getAllUsers() {
		return getJdbcTemplate().query(SELECT_USER_DAO, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEnabled(Boolean.parseBoolean(String.valueOf(rs.getInt("enabled"))));
				return user;
			}}
		);
	}

	@Override
	public User loadUserByUsername(String userName) {
		
		User user =  getJdbcTemplate().query(SELECT_USER_DAO + " where username='" + userName + "'", new ResultSetExtractor<User>() {
	        @Override
	        public User extractData(ResultSet rs) throws SQLException, DataAccessException  {
	        	if (rs.next()) {
	        		User user = new User();
	        		user.setUsername(rs.getString("username"));
	        		user.setPassword(rs.getString("password"));
	        		
	        		user.setAuthorities(loadRolesByUsername(user.getUsername()));
	        		
	        		return user;
	        	}
	        	return null;
	        }
	    });
		
//		User user = (User) getJdbcTemplate().queryForObject(SELECT_USER_DAO + " where username='" + userName + "'", new BeanPropertyRowMapper<User>(User.class));
		return user;
	}
	
	private List<Role> loadRolesByUsername(String userName){
		return getJdbcTemplate().query(SELECT_ROLE_DAO + " where username='" + userName + "'", new RowMapper<Role>() {
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				Role role = new Role();
				role.setName(rs.getString("role"));
				return role;
			}}
		);
	}
	
}
