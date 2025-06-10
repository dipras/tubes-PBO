import java.util.Scanner;
import page.CheckoutPage;
import page.ProfilePage;
import page.ReportPage;
import page.StockPage;
import pkg.User;
import util.Var;
 
public class Main {
    static Scanner inp = new Scanner(System.in);

    public static void main(String[] kontoru) {
        while (true) {
            loginPage();
            User currentUser = Var.getCurrentUser();
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

        User[] users = Var.getUsers();
        for (User user : users) {
            if (user.getName().equals(name)) {
                if (user.isPasswordValid(pw)) {
                    Var.setCurrentUser(user);
                    System.out.println("BERHASIL LOGIN\n\n");
                }
                break;
            }
        }

        if (Var.getCurrentUser() == null) {
            System.out.println("Email atau password salah!\n\n");

            loginPage();
        }
    }

   

    
    public static void mainMenu() {
        System.out.println("==============================================");
        System.out.println("1. Stock\n2. Category\n3. Checkout\n4. Profile\n5. Report Transaction\n9. Logout");
        System.out.println("==============================================");

        int choice = 0;
        System.out.print("## Masukan pilihan: ");
        choice = inp.nextInt();
        inp.nextLine();
        switch (choice) {
            case 1 -> StockPage.viewStock();
            case 3 -> CheckoutPage.viewCheckout();
            case 4 -> ProfilePage.viewProfile();
            case 5 -> ReportPage.viewReport();
            case 9 -> {
                System.out.println("Anda telah logout.");
                loginPage();
            }
                
            default -> {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        mainMenu();
        
    }
}
