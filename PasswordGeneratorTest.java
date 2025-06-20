import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {

    @Test
    void testPasswordLength() {
        int length = 12;
        String password = PasswordGenerator.generatePassword(length);
        assertEquals(length, password.length(), "Password should have the requested length");
    }

    @Test
    void testMinimumPasswordLength() {
        int requestedLength = 4; // less than minimum
        String password = PasswordGenerator.generatePassword(requestedLength);
        assertTrue(password.length() >= 8, "Password should be at least 8 characters long");
    }

    @Test
    void testContainsLowercase() {
        String password = PasswordGenerator.generatePassword(12);
        assertTrue(password.chars().anyMatch(Character::isLowerCase), "Password should contain a lowercase letter");
    }

    @Test
    void testContainsUppercase() {
        String password = PasswordGenerator.generatePassword(12);
        assertTrue(password.chars().anyMatch(Character::isUpperCase), "Password should contain an uppercase letter");
    }

    @Test
    void testContainsDigit() {
        String password = PasswordGenerator.generatePassword(12);
        assertTrue(password.chars().anyMatch(Character::isDigit), "Password should contain a digit");
    }

    @Test
    void testContainsSymbol() {
        String password = PasswordGenerator.generatePassword(12);
        String symbols = "^$?!@#%&";
        assertTrue(password.chars().anyMatch(c -> symbols.indexOf(c) >= 0), "Password should contain a symbol");
    }

    @Test
    void testRandomness() {
        String password1 = PasswordGenerator.generatePassword(12);
        String password2 = PasswordGenerator.generatePassword(12);
        assertNotEquals(password1, password2, "Two generated passwords should not be the same");
    }
}
