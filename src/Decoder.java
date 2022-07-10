import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Decoder {
    public void decoder(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу, который необходимо расшифровать");
        Path encryptedFile = Path.of(scanner.nextLine());
        //String notEncryptedFile = scanner.nextLine();

        System.out.println("Введите ключ шифрования");
        int key = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите путь, куда необходимо записать расшифрованный файл");
        Path notEncryptedFile = Path.of(scanner.nextLine());

        try (BufferedReader bufferedReader = Files.newBufferedReader(encryptedFile);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(notEncryptedFile)) {
            CaesarCipher caesarCipher = new CaesarCipher();

            while (bufferedReader.ready()) {

                String string = bufferedReader.readLine();
                String deEncryptString = caesarCipher.deEncrypt(string, key);
                bufferedWriter.write(deEncryptString + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Файл расшифрован!");

    }
}
