package dataStruct;

public class User {
    public int userID;
    public String userName;
    public String password;
    public boolean status;
    public boolean isAdmin;

    @Override
    public String toString() {
        return "\n" + this.userID + " " + this.userName;
    }


}
