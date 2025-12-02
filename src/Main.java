//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        queueManager c = new queueManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n 1. Add order");
            System.out.println("2. cancel order");
            System.out.println("3. View orders");
            System.out.println("4. Exit");

            System.out.print("choose: ");
            if(!scanner.hasNextInt()){
                System.out.println("Not a valid choice, enter a number.");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1) {
                int wait = c.foodList();
                if (wait > 60) {
                    int hours = (int) Math.ceil(wait / 60.0);
                    System.out.println("Your estimated wait time is: " + hours + " hour(s)");
                } else {
                    System.out.println("the estimated wait time is: " + wait + " minutes");
                }
            } else if (choice == 2) {
                System.out.println("Enter the name of the order to be cancelled: ");
                String name = scanner.nextLine();
                c.cancelOrder(name);

            }
            else if(choice == 3){
                c.ViewOrders();
            }
            else if (choice == 4){
                break;
            }
            else{
                System.out.println("Not a valid choice.");
            }
        }


    }
}