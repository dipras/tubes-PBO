package page;

import main.Main; 
import pkg.User;   
import util.Util;  

public class ProfilePage {

    public static void viewProfile() {
        System.out.println("======================================");
        System.out.println("\nProfil Pengguna");
        System.out.println("======================================");

        User currentUser = Main.currentUser; 

        if (currentUser == null) {
            System.out.println("Belum ada pengguna yang login.");
        } else {
            System.out.println("ID Pengguna   : " + currentUser.getId());
            System.out.println("Username      : " + currentUser.getName());
            System.out.println("Email          : " + currentUser.getEmail());
            
        }

        System.out.println("======================================");
        System.out.print("Tekan Enter untuk kembali...");
        Util.getInputStr(); 
    }
}
