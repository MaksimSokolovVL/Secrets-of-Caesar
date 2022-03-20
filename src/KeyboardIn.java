import java.util.Scanner;

public class KeyboardIn {
    private static int number;
    private  String txt;


    KeyboardIn() {
    }


    public String getTxt() {
        return txt= keyboardString();
    }

    public static int getNumber() {
        return number = keyboardInt();
    }

    public static int keyboardInt() {
        Scanner keyBoard = new Scanner(System.in);
        return keyBoard.nextInt();
    }

    public static String keyboardString() {
        Scanner keyBoard = new Scanner(System.in);
        return keyBoard.nextLine();
    }

}
