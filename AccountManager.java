import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
public class AccountManager {
    private Map<String, User> users = new HashMap<>();
    private static final int MINIMUM_AGE = 18;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public void createAccount(String username, String password, String email, int age)
            throws UnderageException, InvalidEmailException, WeakPasswordException, DuplicateUsernameException {

        validateAge(age);
        validateEmail(email);
        validatePassword(password);
        checkDuplicateUsername(username);

        User user = new User(username, password, email, age);
        users.put(username, user);
        System.out.println("Account successfully created for user: " + username);
    }

    // Validates the user's age
    private void validateAge(int age) throws UnderageException {
        if (age < MINIMUM_AGE) {
            throw new UnderageException("User must be at least " + MINIMUM_AGE + " years old to create an account.");
        }
    }

    private void validateEmail(String email) throws InvalidEmailException {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Invalid email format.");
        }
    }

    private void validatePassword(String password) throws WeakPasswordException {
        if (password.length() < MIN_PASSWORD_LENGTH || !password.matches(".*[0-9].*") || !password.matches(".*[A-Za-z].*") || !password.matches(".*[!@#$%^&*].*")) {
            throw new WeakPasswordException("Password must be at least " + MIN_PASSWORD_LENGTH +
                    " characters long and contain symbols, letters and numbers.");
        }
    }

    private void checkDuplicateUsername(String username) throws DuplicateUsernameException {
        if (users.containsKey(username)) {
            throw new DuplicateUsernameException("The username " + username + " is already in use. Please choose another one.");
        }
    }
}


