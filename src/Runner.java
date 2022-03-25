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
        System.out.println("3. Режим дешифровки СИЛОЙ (bruteforce)");

        System.out.print("Выберите режим: ");
        numberMode = KeyboardIn.keyboardInt();


        if (numberMode == 1) {                                   // 1. Режим шифрования
//            mode1();
            mode1_2();

        } else if (numberMode == 2) {                           // 2. Режим дешифровки с известным ключом
            mode1_2();

        } else if (numberMode == 3) {                           // 3. Режим дешифровки СИЛОЙ - результат выводив в консоль
            modeBruteforce();
        }

    }

/*    public static void buffCode(String outputPath) {
        int realCodeKey = 0;
        try (FileWriter writerNewFile = new FileWriter(Constants.PATH_FOLDER_ENCRYPTED_TXT + fileNameEncrypted + ".txt");
             BufferedWriter writerBuffNewFile = new BufferedWriter(writerNewFile)) {
            Alphabet alphabet = new Alphabet();

//            for (char chars : alphabet.getSymbol()) System.out.print(chars);

            BufferedReader bufferedReaderFile = new BufferedReader(new FileReader(outputPath));

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
    }*/ // рабочий buffer, но для первой версии мода. Она сейчас отключена.

/*   public static void mode1() {
        System.out.println("->> Вы выбрали режим \"Шифрования ключом\"");

        System.out.println("1. file1.txt");
        System.out.println("2. file2.txt");
        System.out.println("3. fileTEST.txt");
        System.out.print("выберите Интересующий файл: ");
        number = KeyboardIn.keyboardInt();

        System.out.print("\nВведите ключ \"Цезаря\" для шифрования файла (от -50 до 50): ");
        keyCezar = KeyboardIn.keyboardInt();

        System.out.print("Введите название зашифрованного файла: ");
        fileNameEncrypted = KeyboardIn.keyboardString();


        if (number == 1) {
            buffCode(Constants.PATH_FILE_TXT_1);
        } else if (number == 2) {
            buffCode(Constants.PATH_FILE_TXT_2);
        } else if (number == 3) {
            buffCode(Constants.PATH_FILE_TXT_3);

        }
    }*/     //первая версия кодировщика - рабочая


    public static void encodingSelection(String outputPath) {

        Path path = Path.of(System.getProperty("user.dir") + "\\.idea\\encrypted_text");

        int realCodeKey = 0;
        try (FileWriter writerNewFile = new FileWriter(path + "\\" + fileNameEncrypted);
             BufferedWriter writerBuffNewFile = new BufferedWriter(writerNewFile)) {
            Alphabet alphabet = new Alphabet();

//            for (char chars : alphabet.getSymbol()) System.out.print(chars);

            BufferedReader bufferedReaderFile = new BufferedReader(new FileReader(outputPath));

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

    public static void deCodeBruteforce(String outputPath, Path selectionPathFile) {
//        Path path = Path.of(System.getProperty("user.dir") + "\\.idea\\encrypted_text");
        Alphabet alphabet = new Alphabet();
        ExceptionWords exceptionWords = new ExceptionWords();
        String pathFile = null;
        try {
            pathFile = Files.readString(selectionPathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert pathFile != null;
        char[] charsCodeArray = new char[pathFile.length()];

        int keyCezar = -50;
        boolean codeSearch = true;

        while (codeSearch) {
            for (int i = 0; i < charsCodeArray.length; i++) {
                if (alphabet.getSymbol().contains(pathFile.charAt(i))) {
                    for (int j = 0; j < alphabet.getSize(); j++) {
                        int realCodKey = j + keyCezar;

                        if (pathFile.charAt(i) == alphabet.getSymbol().get(j)) {
                            if (realCodKey < 0) {
                                charsCodeArray[i] = alphabet.getSymbol().get(alphabet.getSize() + (realCodKey % alphabet.getSize()));
                            } else if (!(realCodKey > alphabet.getSize() - 1)) {
                                charsCodeArray[i] = alphabet.getSymbol().get(realCodKey);
                            } else {
                                charsCodeArray[i] = alphabet.getSymbol().get(realCodKey % alphabet.getSize());
                            }
                        }

                    }
                }else {
                    charsCodeArray[i]=pathFile.charAt(i);
                }
            }
            pathFile = String.valueOf(charsCodeArray);

            for (String temp: exceptionWords.getWords())
                if (pathFile.contains(temp)){
                    codeSearch = false;
                    break;
                }


        }

        System.out.println(pathFile);

        System.out.println();
        System.out.printf(" Поздравляю - Вы успешно раскодировали фал: %s", selectionPathFile.getFileName());


/*        int realCodeKey;
        try (FileWriter writerNewFile = new FileWriter(path + "\\" + fileNameEncrypted);
             BufferedWriter writerBuffNewFile = new BufferedWriter(writerNewFile)) {
            Alphabet alphabet = new Alphabet();
            ExceptionWords exceptionWords = new ExceptionWords();

            BufferedReader bufferedReaderFile = new BufferedReader(new FileReader(outputPath));

            int c;


            while ((c = bufferedReaderFile.read()) != -1) {             //бежим по массиву буффера с данными файла

                if (alphabet.getSymbol().contains((char) c)) {           //содержит ли символ с файла - алфавита

                    for (int j = 0; j < alphabet.getSize(); j++) {       //бежим по массиву алфавита

                        realCodeKey = j + keyCezar;


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
        }*/
    }


    public static void mode1_2() {
        Path path = null;
        if (numberMode == 1) {                                   // 1. Режим шифрования
            System.out.println("->> Вы выбрали режим \"Шифрования ключом\"");
            path = Path.of(System.getProperty("user.dir") + "\\.idea\\normal_text");
        } else if (numberMode == 2) {

            System.out.println("->> Вы выбрали режим \"Дешифровки с известным ключом\"");
            path = Path.of(System.getProperty("user.dir") + "\\.idea\\encrypted_text");
        }

        var fileList = new ArrayList<String>();

//        if (numberMode == 1) {
//
//            Path path = Path.of(System.getProperty("user.dir") + "\\.idea\\encrypted_text");
//        } else if (numberMode == 2) {
//
//            Path path = Path.of(System.getProperty("user.dir") + "\\.idea\\normal_text");
//        }


        try {
            assert path != null;
            try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {

                for (Path tempPath : files) {
                    fileList.add(String.valueOf(tempPath));
                }

                int numberPathFile = 1;
                for (String str : fileList) {
                    System.out.printf("%d. \"%s\"\n", numberPathFile, str);
                    numberPathFile++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (numberMode == 1) {                                   // 1. Режим шифрования
            System.out.print("Теперь Вам нужно выбрать из ↑~списка выше~↑ № файла для шифровки: ");
        } else if (numberMode == 2) {

            System.out.print("Теперь Вам нужно выбрать из ↑~списка выше~↑ № файла для дешифровки: ");
        }

        number = KeyboardIn.keyboardNumberFile(fileList.size());


        Path selectionPathFile = Path.of(fileList.get(number - 1));
        String selectionFileName = String.valueOf(selectionPathFile.getFileName());
        System.out.printf("->> Отличный выбор файла под именем: \"%s\"\n", selectionFileName);

        if (numberMode == 1) {                                   // 1. Режим шифрования
            System.out.printf("\nВведите ключ (от -50 до 50) \"Цезаря\" для Шифрования файла \"%s\": ", selectionFileName);
        } else if (numberMode == 2) {

            System.out.printf("\nВведите ключ (от -50 до 50) \"Цезаря\" для ДЕшифрования файла \"%s\": ", selectionFileName);
        }

        keyCezar = KeyboardIn.keyboardCezarKey();

        if (numberMode == 1) {
            System.out.print("Введите название зашифрованного файла: ");
            fileNameEncrypted = KeyboardIn.keyboardString() + ".txt";
        } else if (numberMode == 2) {

            fileNameEncrypted = "DECODE" + selectionFileName;
        }


        encodingSelection(path + "\\" + selectionFileName);

    }

    public static void modeBruteforce() {
        System.out.println("->> Вы выбрали режим \"Дешифровки СИЛОЙ (bruteforce)\"");

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
        } //выводим список файлов

        System.out.print("Теперь Вам нужно выбрать из ↑~списка выше~↑ № файла для дешифровки: ");
        number = KeyboardIn.keyboardNumberFile(fileList.size());


        Path selectionPathFile = Path.of(fileList.get(number - 1));
        String selectionFileName = String.valueOf(selectionPathFile.getFileName());
        System.out.printf("->> Отличный выбор файла под именем: \"%s\"\n", selectionFileName);


        keyCezar = 0;


        fileNameEncrypted = "DECODE_bruteforce_" + selectionFileName;

        deCodeBruteforce(path + "\\" + selectionFileName, selectionPathFile);

    }


}


