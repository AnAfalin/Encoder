import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Coder {
    public void coder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу, который необходимо зашифровать");
        Path notEncryptedFile = Path.of(scanner.nextLine());

        System.out.println("Введите ключ шифрования");
        int key = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите путь, куда необходимо записать зашифрованный файл");
        Path encryptedFile = Path.of(scanner.nextLine());

        try (BufferedReader bufferedReader = Files.newBufferedReader(notEncryptedFile);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(encryptedFile)) {
            CaesarCipher caesarCipher = new CaesarCipher();

            while (bufferedReader.ready()) {

                String string = bufferedReader.readLine();
                String encryptString = caesarCipher.encrypt(string, key);
                bufferedWriter.write(encryptString + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Файл зашифрован!");

    }
}
