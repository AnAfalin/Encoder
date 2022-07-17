import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Здравствуйте! " +
                    "Выберите:\n" +
                    "1. Зашифровать файл\n" +
                    "2. Расшифровать файл\n" +
                    "3. Перебор\n" +
                    "4. Синтаксический анализ\n" +
                    "5. Выход");
            String answer = scanner.nextLine();
            switch (answer){
                case ("1") -> new Coder().coder();
                case ("2") -> new Decoder().decoder();
                case ("3") -> new Bruteforce().bruteforce();
                case ("4") -> new SyntaxAnalysis().analysisStatistic();
            }

            if(answer.equals("5")){
                break;
            }
        }



    }

}
