package entity;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String fullName;

    public User(){

    }

    public User(int id, String name, String password, String role, String fullName) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
    }
    public User(String name, String password, String role,String fullName) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public Object[] toObjectArray(int columnCount) {
        Object[] userArray = new Object[columnCount];
        userArray[0] = this.id;
        userArray[1] = this.username;
        userArray[2] = this.password;
        userArray[3] = this.role;
        userArray[4] = this.fullName;
        return userArray;
    }
}
