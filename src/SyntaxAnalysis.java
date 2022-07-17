import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SyntaxAnalysis {
    private final Map<Character, Integer> mapEncrypted = new HashMap<>();
    private final Map<Character, Integer> mapAnalysis = new HashMap<>();
    private final Map<Character, Character> map = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public void analysisStatistic(){
        System.out.println("Введите путь к зашифрованному файлу");
        String encryptFile = scanner.nextLine();

        System.out.println("Введите путь к файлу для набора статистики");
        String fileAnalysis = scanner.nextLine();

        System.out.println("Введите путь к файлу для записи расшифрованного файла");
        String fileDeEncrypted = scanner.nextLine();

        List<Map.Entry<Character, Integer>> listEncrypted = fillMapValues(mapEncrypted, encryptFile);
        List<Map.Entry<Character, Integer>> listAnalysis = fillMapValues(mapAnalysis, fileAnalysis);

        if(listAnalysis.size() > listEncrypted.size()){
            for (int i = 0; i < listEncrypted.size(); i++) {
                map.put(listEncrypted.get(i).getKey(), listAnalysis.get(i).getKey());
            }

            try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(encryptFile));
                 BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(fileDeEncrypted))){

                while (bufferedReader.ready()){
                    String string = bufferedReader.readLine();
                    StringBuilder resString = new StringBuilder();
                    for (Character character : string.toCharArray()) {
                        resString.append(map.get(character));
                    }
                    bufferedWriter.write(resString + System.lineSeparator());
                }

                System.out.println("Содержимое файла расшифровано");

            }catch (IOException exception){
                exception.printStackTrace();
            }

        }else {
            System.out.println("Размер файла для статистики должен быть больше зашифрованного файла");
        }

    }

    private List<Map.Entry<Character, Integer>> fillMapValues(Map<Character, Integer> map, String path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(path))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine());
            }

            for (Character character : stringBuilder.toString().toCharArray()) {
                if (!map.containsKey(character)) {
                    map.put(character, 1);
                } else {
                    map.put(character, map.get(character) + 1);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

        Comparator<Map.Entry<Character, Integer>> comparator = Map.Entry.comparingByValue();

        list.sort(comparator.reversed());

        return list;
    }










}
