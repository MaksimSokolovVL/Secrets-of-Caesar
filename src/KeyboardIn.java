import java.util.Scanner;

public class KeyboardIn {

    KeyboardIn() {
    }


    public static int keyboardInt() {

        Scanner keyBoard = new Scanner(System.in);
        int temp;
        if (keyBoard.hasNextInt()) {
            temp = keyBoard.nextInt();
            if (temp<=3  && temp>0) {
                return temp;

            }else{System.out.println("Вы ввели не верные данные. Будьте внимательны и введите пожалуйста от 1 до 3");
                System.out.print("Выберите режим: ");
                return keyboardInt();}
        }else {
            System.out.println("Вы водите символ не относящийся к числу, давайте еще раз");
            System.out.print("Выберите режим: ");
            return keyboardInt();
        }


    }

    public static int keyboardCezarKey() {

        Scanner keyBoard = new Scanner(System.in);
        int temp;
        if (keyBoard.hasNextInt()) {
            temp = keyBoard.nextInt();
            if (temp<=50  && temp>=-50) {
                return temp;

            }else{System.out.println("Вы ввели не верные данные. Будьте внимательны и введите пожалуйста от 1 до 3");
                System.out.print("Выберите режим: ");
                return keyboardInt();}
        }else {
            System.out.println("Вы водите символ не относящийся к числу, давайте еще раз");
            System.out.print("Выберите режим: ");
            return keyboardInt();
        }


    }

    public static int keyboardNumberFile(int numb) {

        Scanner keyBoard = new Scanner(System.in);
        int temp;
        if (keyBoard.hasNextInt()) {
            temp = keyBoard.nextInt();
            if (temp<=numb  && temp>0) {
                return temp;

            }else{System.out.println("Вы ввели не верные данные. Будьте внимательны и введите пожалуйста от 1 до 3");
                System.out.print("Выберите режим: ");
                return keyboardInt();}
        }else {
            System.out.println("Вы водите символ не относящийся к числу, давайте еще раз");
            System.out.print("Выберите режим: ");
            return keyboardInt();
        }


    }


    public static String keyboardString() {
        Scanner keyBoard = new Scanner(System.in);
        if(keyBoard.hasNextLine()){
           return keyBoard.nextLine();
        }else {

        System.out.println("Вы водите некорректное имя, давайте еще раз");
        return keyboardString();
    }}

}

