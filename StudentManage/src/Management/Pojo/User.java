package Management.Pojo;

/**
 * @author lzq
 */
public class User {

    /**
     * 用户名
     */
    private String userId;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户权限
     */
    private Integer jurisdiction;

    public User(){}

    public User(String userId, String password, Integer jurisdiction) {
        this.userId = userId;
        this.password = password;
        this.jurisdiction = jurisdiction;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Integer jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
}
