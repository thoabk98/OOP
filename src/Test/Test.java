package Test;

import dataStruct.User;
import model.UserModel;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        UserModel userModel = new UserModel();
        ArrayList<User> userArrayList = userModel.getUserList();
        for (User user: userArrayList)
            System.out.println(user.toString());
    }
}
