package Management.Service.Impl;

import Management.Dao.Impl.UserDaoImpl;
import Management.Dao.UserDao;
import Management.Pojo.User;
import Management.Service.UserService;

/**
 * @author lzq
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @param userName 用户名
     * @param password 密码
     */
    @Override
    public void register(String userName, String password) {
        User user = userDao.getUserByUserName(userName);
        if (user != null) {
            System.out.println("用户名已存在");
            return;
        }
        int i = userDao.insertUser(userName, password);
        if (i > 0) {
            System.out.println("注册成功");
        }
    }

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @Override
    public boolean login(String userName, String password) {
        User user = userDao.getUser(userName, password);
        if (user == null) {
            return false;
        }
        return true;
    }

    /**
     * 获取用户权限
     * @param userName 用户名
     * @return 用户权限1表示有高级权限，0表示没有
     */
    @Override
    public int getJurisdictionByUserName(String userName) {

        User user = userDao.getUserByUserName(userName);

        return user.getJurisdiction();
    }

    /**
     * 修改密码
     * @param userName 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @Override
    public int updatePassword(String userName, String oldPassword, String newPassword) {

        User user = userDao.getUserByUserName(userName);
        if(!user.getPassword().equals(oldPassword)){
            return 0;
        }else {
            userDao.updatePassword(userName, newPassword);
        }
        return 1;
    }
}
