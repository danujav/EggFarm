package lk.rangafarm.pos.dto;

public class UserDto {
    private String userName;
    private String name;
    private String email;
    private String password;
    private String roleType;

    public UserDto() {
    }

    public UserDto(String userName, String name, String email, String password, String roleType) {
        this.setUserName(userName);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setRoleType(roleType);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
