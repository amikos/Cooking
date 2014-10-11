package kz.amikos.cooking.core.transaction.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kz.amikos.cooking.web.models.User;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

/**
 * Created by zhansar
 */
@Component
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {
	private static final String INSERT_USER_DAO = "insert into users values (?, ?, ?)";
	private static final String SELECT_USER_DAO = "select username, password, enabled from users";

	public void addUser(User user) {
		getJdbcTemplate().update(INSERT_USER_DAO, user.getUsername(), user.getPassword(), user.getEnabled());
	}

	public List<User> getAllUsers() {
		return getJdbcTemplate().query(SELECT_USER_DAO, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEnabled(rs.getInt("enabled"));
				return user;
			}}
		);
	}
	
}
