import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bruteforce {

    public void bruteforce() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к зашифрованному файлу");
        Path encryptedFile = Path.of(scanner.nextLine());
        System.out.println("Введите путь для записи расшифрованного файла");
        Path notEncryptedFile = Path.of(scanner.nextLine());

        CaesarCipher caesarCipher = new CaesarCipher();

        try (BufferedReader bufferedReader = Files.newBufferedReader(encryptedFile);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(notEncryptedFile)) {

            StringBuilder text = new StringBuilder();
            List<String> list = new ArrayList<>();
            while (bufferedReader.ready()){
                String string = bufferedReader.readLine();
                text.append(string);
                list.add(string);

            }

            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String encryptedString = caesarCipher.deEncrypt(text.toString(), i);
                if(isValidateText(encryptedString)){
                    for (String string : list) {
                        String deEncrypt = caesarCipher.deEncrypt(string, i);
                        bufferedWriter.write(deEncrypt + System.lineSeparator());
                        System.out.println("Текст расшифрован и записан. Ключ расшифровки - " + i);
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private boolean isValidateText(String text){

        boolean isValidate = false;

        int indexStart = new Random().nextInt(text.length() / 2);
        int indexEnd = indexStart + (int) Math.sqrt(text.length());
        String substring = text.substring(indexStart, indexEnd);

        String[] strings = substring.split(" ");
        for (String string : strings) {
            if(string.length() > 24){
                return false;
            }
        }

//        if(text.matches("[.,!? ]"))
        if(substring.contains(". ") || substring.contains(", ") || substring.contains("? ") || substring.contains("! ")){
            isValidate = true;
        }

        if(isValidate){
            System.out.println(substring);
            System.out.println("Понятен ли текст? y/n");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if(answer.equalsIgnoreCase("y")){
                return true;
            }else if(answer.equalsIgnoreCase("n")){
                isValidate = false;
            }
        }

        return isValidate;
    }


}


//найти ошибку через дебаг. получить ключ. проверить правильность записи.
//предложить варианты исправить ошибку
