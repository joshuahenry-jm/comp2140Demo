import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    private static Menu menu;
    private static ArrayList<User> users = new ArrayList<>();
    private static HashMap<Integer, String> userMap = new HashMap<>();
    private static Admin admin;
    

    public static void main(String[] args) {
        QueueManager c = QueueManager.getInstance();
        Scanner scanner = new Scanner(System.in);

        // simple hardcoded admin for now
        Admin u1 = new Admin("Noellee", "work@example.com", "admin123", 1, false);
        User u2 = new User("Coyesha",  "Coyesha@gmail.com", "1234",2,true);

        admin = u1;

        users.add(u1);
        users.add(u2);
        userMap.put(u1.getUserID(), u1.getUsername()); // 1 -> Noellee
        userMap.put(u2.getUserID(), u2.getUsername()); // 2 -> Coyesha

        boolean loggedIn = false;
        User currentUser = null;

        while (!loggedIn) {
            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine().trim();

            // Check if username exists in HashMap
            Integer userId = null;
            for (Integer id : userMap.keySet()) {
                if (userMap.get(id).equalsIgnoreCase(enteredUsername)) {
                    userId = id;
                    break;
                }
            }

            if (userId == null) {
                System.out.println("User not found. Try again.\n");
                continue;
            }

            //Prompt for password
            System.out.print("Enter password: ");
            
            String enteredPassword = scanner.nextLine();
            //int password = parseInt(enteredPassword);
             
            // Search ArrayList by userID to verify password
            for (User u : users) {
                if (u.getUserID() == userId) {
                    if (u.getPassword().equals(enteredPassword)) {
                        loggedIn = true;
                        currentUser = u;
                        System.out.println("Login successful! Welcome, " + u.getUsername() + "!\n");
                    } else {
                        System.out.println("Incorrect password. Try again.\n");
                    }
                    break;
                }
            }
        }
        
        boolean vis = true;
        while (true) {
            System.out.println("\n1. Add order");
            System.out.println("2. Cancel order");
            System.out.println("3. View orders");
            System.out.println("4. Exit");
            System.out.println("5. Admin panel");   

            System.out.print("Choose: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Not a valid choice, enter a number.");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                c.displayMenu();
                int wait = c.foodList();
                if (wait > 60) {
                    int hours = (int) Math.ceil(wait / 60.0);
                    System.out.println("Your estimate1d wait time is: " + hours + " hour(s)");
                } else if (wait > 0) {
                    System.out.println("The estimated wait time is: " + wait + " minutes");
                }
            } else if (choice == 2) {
                System.out.println("Enter the name of the order to be cancelled: ");
                String name = scanner.nextLine();
                c.cancelOrder(name);

            } else if (choice == 3) {
                c.ViewOrders();

            } else if (choice == 4) {
                break;

            } else if (choice == 5) {

                //make sure someone is logged in
                if (currentUser == null) {
                    System.out.println("No user is currently logged in.");
                    continue;
                }

                //only admins are allowed into the admin panel
                if (!(currentUser instanceof Admin)) {
                    System.out.println("Access denied. Only admins can open the admin panel.");
                    continue;
                }

                //OPTIONAL: still ask for admin ID as an extra check
                System.out.println("Enter admin user ID:");
                int checkadminID = scanner.nextInt();
                scanner.nextLine();  // consume leftover newline

                Admin loggedInAdmin = (Admin) currentUser;  // safe cast

                if (checkadminID == loggedInAdmin.adminID) {
                    adminMenu(scanner, loggedInAdmin);
                } else {
                    System.out.println("Invalid admin ID.");
                }

            } else {
                System.out.println("Not a valid choice.");
            }

        }
    }

    //Admin submenu
    private static void adminMenu(Scanner scanner, Admin admin) {
        while (true) {
            System.out.println("\n--- ADMIN PANEL ---");
            System.out.println("1. View menu");
            System.out.println("2. Update stock");
            System.out.println("3. Update preparation time");
            System.out.println("4. Back");

            System.out.print("Choose: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Not a valid choice, enter a number.");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                menu.displayMenu();

            } else if (choice == 2) {
                System.out.print("Enter item ID: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid ID.");
                    scanner.nextLine();
                    continue;
                }
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter new stock quantity: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid stock value.");
                    scanner.nextLine();
                    continue;
                }
                int newStock = scanner.nextInt();
                scanner.nextLine();

                

            } else if (choice == 3) {
                System.out.print("Enter item ID: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid ID.");
                    scanner.nextLine();
                    continue;
                }
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter new preparation time (minutes): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid time.");
                    scanner.nextLine();
                    continue;
                }
                int newPrep = scanner.nextInt();
                scanner.nextLine();

            } else if (choice == 4) {
                break;

            } else {
                System.out.println("Not a valid choice.");
            }
        }
    }
}
