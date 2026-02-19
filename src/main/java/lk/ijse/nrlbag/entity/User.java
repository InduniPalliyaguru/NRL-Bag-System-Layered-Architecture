package lk.ijse.nrlbag.entity;

public class User {

    private int id;
    private String userName;
    private String user_password;
    private String email;
    private String name;
    private String role;


    public User() {
    }

    public User(String userName, String user_password) {
        this.userName = userName;
        this.user_password = user_password;
    }

    public User(String userName, String email, String name, String role) {
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public User(int id, String userName, String user_password, String email, String name, String role) {
        this.id = id;
        this.userName = userName;
        this.user_password = user_password;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public User(String userName, String user_password, String email, String name, String role) {
        this.userName = userName;
        this.user_password = user_password;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
