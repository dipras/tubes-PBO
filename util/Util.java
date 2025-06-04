
package util;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Util {

    static Scanner inp = new Scanner(System.in);
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static int getInput() {
        int result = inp.nextInt();
        inp.nextLine();

        return result;
    }

    public static String getInputStr() {
        String result = inp.nextLine();

        return result;
    }
}
