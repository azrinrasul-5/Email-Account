public class Main {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();

        try {
            accountManager.createAccount("john_doe", "passw^ord123", "john.doe@example.com", 20);
            accountManager.createAccount("jane_doe", "pass*6123", "jane.doe@example.com", 137);  //  UnderageException
            accountManager.createAccount("jack_doe", "pa&^%&%ss123", "shjgjadg@.dswat", 25);  //  InvalidEmailException
            accountManager.createAccount("john_doe", "str&^^%ongPassword123", "john.new@example.com", 30);  // DuplicateUsernameException
        } catch (UnderageException | InvalidEmailException | WeakPasswordException | DuplicateUsernameException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}