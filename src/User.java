public class User {

    private int ID, userType; // 1 is admin, 2 is cook, 3 is client
    private String userName, userPassword;

    public User(int ID, int userType, String userName, String userPassword) {
        this.ID = ID;
        this.userType = userType;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
