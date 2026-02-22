package bean;

public class User {
    private String userName;// 用户名
    private String password;
    private String loginName;// 登录名

    public User(){}

    public User(String userName, String password, String loginName) {
        this.userName = userName;
        this.password = password;
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
