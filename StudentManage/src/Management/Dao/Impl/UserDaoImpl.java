package Management.Dao.Impl;

import Management.Dao.UserDao;
import Management.Pojo.User;
import Management.Utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author lzq
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public int insertUser(String userName, String password) {
        String sql = "insert into User values(?,?,?)";
        int update = jdbcTemplate.update(sql, userName, password, 0);
        return update;
    }

    @Override
    public User getUser(String userName, String password) {
        User user = null;
        try {
            String sql = "select * from User where userId = ? and password = ?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName, password);
        } catch (Exception e) {

        }

        return user;
    }

    @Override
    public User getUserByUserName(String userName) {

        User user = null;

        try {
            String sql = "select * from User where userId = ?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName);
        } catch (Exception e) {

        }

        return user;
    }

    @Override
    public int updatePassword(String userName, String password) {

        String sql = "update User set password = ? where userName = ?";
        int update = jdbcTemplate.update(sql, userName, password);
        return update;
    }
}
