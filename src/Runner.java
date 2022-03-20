import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Runner {
    private static String fileNameEncrypted;
    private static int keyCezar;
    private static int numberMode;
    private static int number;

    public static void main(String[] args) {

        System.out.println("1. Режим шифрования ");
        System.out.println("2. Режим дешифровки с известным ключом");
        System.out.println("3. Режим дешифровки СИЛОЙ");

        System.out.print("Выберите режим: ");
        numberMode = KeyboardIn.getNumber();


        if (numberMode == 1) {                                   // 1. Режим шифрования
            mode1();

        } else if (numberMode == 2) {                           // 2. Режим дешифровки с известным ключом
            mode2();

        } else if (numberMode == 3) {                           // 3. Режим дешифровки СИЛОЙ
            System.out.println("режим 3 в разработке");
        }

    }

    public static void buffCode(String constants) {
        int realCodeKey = 0;
        try (FileWriter writerNewFile = new FileWriter(Constants.PATH_FOLDER_ENCRYPTED_TXT + fileNameEncrypted + ".txt");
             BufferedWriter writerBuffNewFile = new BufferedWriter(writerNewFile)) {
            Alphabet alphabet = new Alphabet();

//            for (char chars : alphabet.getSymbol()) System.out.print(chars);

            BufferedReader bufferedReaderFile = new BufferedReader(new FileReader(constants));

            int c;

            while ((c = bufferedReaderFile.read()) != -1) {             //бежим по массиву буффера с данными файла

                if (alphabet.getSymbol().contains((char) c)) {           //содержит ли символ с файла - алфавита

                    for (int j = 0; j < alphabet.getSize(); j++) {       //бежим по массиву алфавита
                        if (numberMode == 1) {
                            realCodeKey = j + keyCezar;
                        } else if (numberMode == 2) {
                            realCodeKey = j - keyCezar;
                        }


                        if ((char) c == alphabet.getSymbol().get(j)) {
                            if (realCodeKey < 0) {
                                writerBuffNewFile.write(alphabet.getSymbol().get((alphabet.getSize()) + ((realCodeKey % alphabet.getSize()))));
                            } else if (!(realCodeKey > (alphabet.getSize()) - 1)) {
                                writerBuffNewFile.write(alphabet.getSymbol().get(realCodeKey));
                            } else {
                                writerBuffNewFile.write(alphabet.getSymbol().get(realCodeKey % (alphabet.getSize())));
                            }
                        }
//                        }
                    }

                } else {
                    writerBuffNewFile.write(c);

                }


            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buffDeCode(String constants) {
        Path path = Path.of(System.getProperty("user.dir") + "\\.idea\\encrypted_text");
        int realCodeKey = 0;
        try (FileWriter writerNewFile = new FileWriter(path + "\\" + fileNameEncrypted);
             BufferedWriter writerBuffNewFile = new BufferedWriter(writerNewFile)) {
            Alphabet alphabet = new Alphabet();

//            for (char chars : alphabet.getSymbol()) System.out.print(chars);

            BufferedReader bufferedReaderFile = new BufferedReader(new FileReader(constants));

            int c;

            while ((c = bufferedReaderFile.read()) != -1) {             //бежим по массиву буффера с данными файла

                if (alphabet.getSymbol().contains((char) c)) {           //содержит ли символ с файла - алфавита

                    for (int j = 0; j < alphabet.getSize(); j++) {       //бежим по массиву алфавита
                        if (numberMode == 1) {
                            realCodeKey = j + keyCezar;
                        } else if (numberMode == 2) {
                            realCodeKey = j - keyCezar;
                        }


                        if ((char) c == alphabet.getSymbol().get(j)) {
                            if (realCodeKey < 0) {
                                writerBuffNewFile.write(alphabet.getSymbol().get((alphabet.getSize()) + ((realCodeKey % alphabet.getSize()))));
                            } else if (!(realCodeKey > (alphabet.getSize()) - 1)) {
                                writerBuffNewFile.write(alphabet.getSymbol().get(realCodeKey));
                            } else {
                                writerBuffNewFile.write(alphabet.getSymbol().get(realCodeKey % (alphabet.getSize())));
                            }
                        }
//                        }
                    }

                } else {
                    writerBuffNewFile.write(c);

                }


            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mode1() {
        System.out.println("->> Вы выбрали режим \"Шифрования ключом\"");

        System.out.println("1. file1.txt");
        System.out.println("2. file2.txt");
        System.out.println("3. fileTEST.txt");
        System.out.print("выберите Интересующий файл: ");
        number = KeyboardIn.getNumber();

        System.out.print("\nВведите ключ \"Цезаря\" для шифрования файла (от -50 до 50): ");
        keyCezar = KeyboardIn.getNumber();

        System.out.print("Введите название зашифрованного файла: ");
        fileNameEncrypted = KeyboardIn.keyboardString();


        if (number == 1) {
            buffCode(Constants.PATH_FILE_TXT_1);
        } else if (number == 2) {
            buffCode(Constants.PATH_FILE_TXT_2);
        } else if (number == 3) {
            buffCode(Constants.PATH_FILE_TXT_3);

        }
    }

    public static void mode2() {
        System.out.println("->> Вы выбрали режим \"Дешифровки с известным ключом\"");

        var fileList = new ArrayList<String>();
        Path path = Path.of(System.getProperty("user.dir") + "\\.idea\\encrypted_text");

        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {

            for (Path tempPath : files) {
                fileList.add(String.valueOf(tempPath));
            }

            int numberPathFile = 1;
            for (String str : fileList) {
                System.out.printf("%d. \"%s\"\n", numberPathFile, str);
                numberPathFile++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("Теперь Вам нужно выбрать из ↑~списка выше~↑ № файла для дешифровки: ");
        number = KeyboardIn.getNumber();


        Path selectionPathFile = Path.of(fileList.get(number - 1));
        String selectionFileName = String.valueOf(selectionPathFile.getFileName());
        System.out.printf("->> Отличный выбор файла под именем: \"%s\"\n", selectionFileName);

        System.out.printf("\nВведите ключ \"Цезаря\" для ДЕшифрования файла \"%s\" (от -50 до 50): ", selectionFileName);
        keyCezar = KeyboardIn.getNumber();


        fileNameEncrypted = "DECODE" + selectionFileName;

        buffDeCode(path + "\\" + selectionFileName);

    }


}


