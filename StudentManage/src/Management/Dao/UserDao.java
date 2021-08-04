package Management.Dao;

import Management.Pojo.User;

/**
 * @author lzq
 */
public interface UserDao {

    /**
     * 注册
     * @param userName 用户名
     * @param password 密码
     * @return 返回插入成功否
     */
    public int insertUser(String userName,String password);

    /**
     *
     * 登录
     * @param userName 用户名
     * @param password 密码
     */
    public User getUser(String userName,String password);

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 返回一个用户对象
     */
    public User getUserByUserName(String userName);

    /**
     * 修改密码
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    public int updatePassword(String userName,String password);

}
