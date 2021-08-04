package Management.Service;

/**
 * @author lzq
 */
public interface UserService {

    /**
     * 注册用户
     * @param userName 用户名
     * @param password 密码
     */
    public void register(String userName,String password);

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return 返回1表示登陆成功，返回0表示登录失败
     */
    public boolean login(String userName,String password);

    /**
     * 通过用户名查询用户权限
     * @param userName 用户名
     * @return 权限值
     */
    public int getJurisdictionByUserName(String userName);

    /**
     * 修改密码
     * @param userName 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 判断修改是否成功
     */
    public int updatePassword(String userName,String oldPassword,String newPassword);

}
