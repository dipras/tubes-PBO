package page;

import model.User;
import util.Util;  
import util.Var;

public class ProfilePage {
    public static void viewProfile() {
        User currentUser = Var.getCurrentUser();
        System.out.println("======================================");
        System.out.println("\nProfil User");
        System.out.println("======================================");

        if (currentUser == null) {
            System.out.println("Belum ada User yang login.");
        } else {
            System.out.println("ID User       : " + currentUser.getId());
            System.out.println("Username      : " + currentUser.getName());
            System.out.println("Email         : " + currentUser.getEmail());
            
        }

        System.out.println("======================================");
        System.out.print("Tekan Enter untuk kembali...");
        Util.getInputStr();
    }
}
