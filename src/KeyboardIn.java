import java.util.Scanner;

public class KeyboardIn {

    KeyboardIn() {
    }


//    public static String getTxt() {
//        return keyboardString();
//    }

//    public static int getNumber() {
//        return keyboardInt();
//    }

    public static int keyboardInt() {
        Scanner keyBoard = new Scanner(System.in);
        return keyBoard.nextInt();
    }

    public static String keyboardString() {
        Scanner keyBoard = new Scanner(System.in);
        return keyBoard.nextLine();
    }

}
