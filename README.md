# AgriProductSalesSystem
农产品销售系统的代码仓库
public class User {

    private String username;
    private String password;
    private String email;

    // Constructor
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to register a new user
    public boolean registerUser() {
        // Here you would typically insert the user into a database
        // For this example, we'll just print out the user details
        System.out.println("Registering new user...");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        // In a real scenario, you would hash the password before storing it
        System.out.println("Password: " + password);
        // Assume the registration is successful
        return true;
    }

    // Method to login an existing user
    public boolean loginUser(String inputUsername, String inputPassword) {
        // Here you would typically check the username and password against a database
        // For this example, we'll just compare them to the instance variables
        if (this.username.equals(inputUsername) && this.password.equals(inputPassword)) {
            System.out.println("Login successful for user: " + username);
            return true;
        } else {
            System.out.println("Login failed for user: " + inputUsername);
            return false;
        }
    }

    // Main method for demonstration purposes
    public static void main(String[] args) {
        // Create a new User object
        User newUser = new User("johnDoe", "password123", "john@example.com");

        // Register the new user
        boolean isRegistered = newUser.registerUser();
        if (isRegistered) {
            System.out.println("User registered successfully!");
        }

        // Attempt to login with correct credentials
        boolean isLoggedIn = newUser.loginUser("johnDoe", "password123");
        if (isLoggedIn) {
            System.out.println("User logged in successfully!");
        }

        // Attempt to login with incorrect credentials
        isLoggedIn = newUser.loginUser("johnDoe", "wrongpassword");
        if (!isLoggedIn) {
            System.out.println("User login failed!");
        }
    }
}
