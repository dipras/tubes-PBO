package main;
import java.util.Scanner;

import page.CheckoutPage;
import page.ProfilePage;
import page.ReportPage;
import page.StockPage;
import pkg.User;
 
public class Main {

    static User[] users = {
        new User(1, "radit", "radit@radit.com", "radit")
      , new User(2, "gector", "gector@gector.com", "gector")
      , new User(3, "langga", "langga@langga.com", "langga")
      , new User(4, "yuli", "yuli@yuli.com", "yuli")
    };
    public static User currentUser;
    static Scanner inp = new Scanner(System.in);

    public static void main(String[] kontoru) {
        while (true) {
            loginPage();
            System.out.println("SELEMAT DATANG " + currentUser.getName());
            mainMenu();
        }
    }

    public static void loginPage() {
        System.out.println("======================================");
        System.out.println("LOGIN PAGE");
        System.out.println("======================================");

        System.out.print("nama: ");
        String name = inp.nextLine();
        System.out.print("password: ");
        String pw = inp.nextLine();

        for (User user : users) {
            if (user.getName().equals(name)) {
                if (user.isPasswordValid(pw)) {
                    currentUser = user;
                    System.out.println("BERHASIL LOGIN\n\n");
                }
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("Email atau password salah!\n\n");

            loginPage();
        }
    }

   

    
    public static void mainMenu() {
        System.out.println("==============================================");
        System.out.println("1. Stock\n2. Checkout\n3. Profile\n4. Report Transaction\n9. Logout");
        System.out.println("==============================================");

        int choice = 0;
        System.out.print("## Masukan pilihan: ");
        choice = inp.nextInt();
        inp.nextLine();
        switch (choice) {
            case 1 -> StockPage.viewStock();
            case 2 -> CheckoutPage.viewCheckout();
            case 3 -> ProfilePage.viewProfile();
            case 4 -> ReportPage.viewReport();
            case 9 -> {
                System.out.println("Anda telah logout.");
                loginPage();
            }
                
            default -> {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                mainMenu();
            }
        }

        
        
    }
}
