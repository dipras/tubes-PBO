package util;

import model.User;

public class Var {
    private static User currentUser;
    private static final User[] users = {
        new User(1, "radit", "radit@radit.com", "radit")
      , new User(2, "gector", "gector@gector.com", "gector")
      , new User(3, "langga", "langga@langga.com", "langga")
      , new User(4, "yuli", "yuli@yuli.com", "yuli")
    };


    public static User[] getUsers() {
        return users;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}