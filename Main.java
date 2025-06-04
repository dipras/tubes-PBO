
import java.util.Scanner;
import pkg.User;

public class Main {

    static User[] users = {new User(1, "Raddit", "radit@gmail.com", "wkowko")};
    static User currentUser;
    static Scanner inp = new Scanner(System.in);

    public static void main(String[] kontoru) {
        loginPage();
        System.out.println("SELEMAT DATANG " + currentUser.getName());
        mainMenu();
    }

    public static void loginPage() {
        System.out.println("======================================");
        System.out.println("LOGIN PAGE");
        System.out.println("======================================");

        System.out.print("email: ");
        String email = inp.nextLine();
        System.out.print("password: ");
        String pw = inp.nextLine();

        for (User user : users) {
            if (user.getEmail().equals(email)) {
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
        System.out.println("1. Stock\n2. Checkout\n3. Profile\n9. Logout");
        System.out.println("==============================================");

        int choice = 0;
        System.out.print("## Masukan pilihan: ");
        choice = inp.nextInt();
        inp.nextLine();
        switch (choice) {
            case 1 ->
                System.out.println("Hello world");
            case 9 ->
                System.out.println("Selamat tinggal!");
            default ->
                System.out.println("Masukan menu yang benar!");
        }

        if (choice != 9) {
            mainMenu();
        }
    }
}
