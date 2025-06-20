import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "^$?!@#%&";
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        // Enforce minimum length
        if (length < 8) length = 8;

        // Generate required characters
        Stream<Character> lowerChars = getRandomChars(LOWERCASE, 1);
        Stream<Character> upperChars = getRandomChars(UPPERCASE, 1);
        Stream<Character> digitChars = getRandomChars(DIGITS, 1);
        Stream<Character> symbolChars = getRandomChars(SYMBOLS, 1);
        Stream<Character> allChars = getRandomChars(LOWERCASE + UPPERCASE + DIGITS + SYMBOLS, length - 4);

        // Combine and shuffle
        List<Character> combined = Stream.concat(Stream.concat(lowerChars, upperChars),
                        Stream.concat(Stream.concat(digitChars, symbolChars), allChars))
                .collect(Collectors.toList());
        Collections.shuffle(combined, random);

        return combined.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static Stream<Character> getRandomChars(String source, int count) {
        return random.ints(count, 0, source.length())
                .mapToObj(source::charAt);
    }

    public static void main(String[] args) {
        System.out.println("Generated password: " + PasswordGenerator.generatePassword(12));
    }

}
