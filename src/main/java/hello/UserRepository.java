/**
 * 
 */
package hello;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Siva
 *
 */
@Repository
public class UserRepository
{
	private JdbcTemplate jdbcTemplate;
	
	public UserRepository(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return jdbcTemplate.query("select * from user_details", new UserRowMapper());		
	}

	@Transactional(readOnly=true)
	public User findUserById(int id) {
		return jdbcTemplate.queryForObject("select * from user_details where user_id=?", new Object[]{id}, new UserRowMapper());
	}
}

class UserRowMapper implements RowMapper<User>
{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setName(rs.getString("user_name"));
		user.setEmail(rs.getString("user_email"));
		
		return user;
	}
	
}