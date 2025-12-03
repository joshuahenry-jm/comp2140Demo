public class TestLogin {

    public static void main(String[] args) {

        // Create sample users
        User normalUser = new User("Coyesha",  "Coyesha@gmail.com", "1234",1,true);
        Admin adminUser = new Admin( "admin", "admin@gmail.com", "admin123", 2, false );

        // UNIT TESTS
        System.out.println("=== UNIT TESTS ===");

        // Test login
        System.out.println("Normal user correct login: " +
                normalUser.login("Coyesha", "1234"));   // EXPECT TRUE

        System.out.println("Normal user wrong login: " +
                normalUser.login("Coyesha", "wrong"));  // EXPECT FALSE

        System.out.println("Admin correct login: " +
                adminUser.login("admin", "admin123")); // TRUE

        // Test role flag
        System.out.println("Normal user isGuest: " + normalUser.isguest()); // EXPECT TRUE
        System.out.println("Admin isGuest: " + adminUser.isguest()); // EXPECT FALSE


        // INTEGRATION TESTS
        System.out.println("\n=== INTEGRATION TESTS ===");

        // Fake login system
        User loggedIn1 = login(normalUser,"Coyesha", "1234");
        User loggedIn2 = login(adminUser,"admin", "admin123");

        runMenu(loggedIn1); // EXPECT Normal User Menu
        runMenu(loggedIn2); // EXPECT Admin Menu
    }


    // Simulate logging in
    public static User login(User user, String username, String password) {
        if (user.login(username, password)) {
            System.out.println("Login successful: " + user.getUsername());
            return user;
        } else {
            System.out.println("Login failed for: " + username);
            return null;
        }
    }

    // Show correct menu based on isGuest
    public static void runMenu(User user) {
        if (user == null) return;

        if (user.isguest()) {
            System.out.println("Normal User Menu: place order, create profile");
        } else {
            System.out.println("Admin Menu: update menu, update stock, update prep time");
        }
    }
}
