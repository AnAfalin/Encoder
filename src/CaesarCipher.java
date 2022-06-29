public class CaesarCipher {
    private static final String ALPHABET_PART_ONE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!? +-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789";
    private static final String ALPHABET_PART_TWO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!? +-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789";
    private static final String ALPHABET = ALPHABET_PART_ONE + ALPHABET_PART_TWO;

    public String encrypt(String message, int key) {
        System.out.println(ALPHABET.length());
        StringBuilder result = new StringBuilder();
        for (char aChar : message.toCharArray()) {
            int indexPosition = ALPHABET.indexOf(aChar);
            int newIndexPosition;
            char newCharAt = 0;

            if (indexPosition > 0) {
                if (key > 0) {
                    newIndexPosition = (indexPosition + key) % (ALPHABET.length() / 2);
                } else {
                    key = key % (ALPHABET.length() / 2);
                    newIndexPosition = (indexPosition + (ALPHABET.length() / 2) + key) % ALPHABET.length();
                }
                newCharAt = ALPHABET.charAt(newIndexPosition);
            }
            result.append(newCharAt);
        }
        return result.toString();
    }

    public String deEncrypt(String message, int key) {
        return encrypt(message, -1 * key);
    }

}
