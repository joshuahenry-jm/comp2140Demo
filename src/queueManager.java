import java.util.ArrayList;
import java.util.Scanner;



public class queueManager {
private ArrayList<Integer> queue = new ArrayList<>();
private ArrayList<String> ActiveQueue = new ArrayList<>();

String burger = "burger";
String hotDog = "Hot Dog";
String fries = "fries";
String cheesyFries = "Cheesy Fries";
String NuggetsAndFries = "Nuggets and Fries";

int burgerTime = 10;
int hotdogTime = 5;
int friesTime = 5;
int cheesyFriesTime = 10;
int NuggetsAndFriesTime = 5;


public ArrayList<String> getActiveQueue() {
        return new ArrayList<>(ActiveQueue);
    }

public ArrayList<Integer> getQueue() {
        return new ArrayList<>(queue);
    }

public int foodList() {
    Scanner scan = new Scanner(System.in);
    System.out.printf("What would you like to order?: ");
    String input = scan.nextLine();
    input = input.trim().toLowerCase().replaceAll("\\s+", "");

    int itemTime = 0;
    String itemName ="";
    switch (input) {
        case "burger":
            itemTime = burgerTime; itemName = burger;
            break;
        case "hotdog":
            itemTime = hotdogTime; itemName = hotDog;
            break;
        case "fries":
            itemTime = friesTime; itemName = fries;
            break;
        case "cheesyfries":
            itemTime = cheesyFriesTime; itemName = cheesyFries;
            break;

        case "nuggetsandfries":
            itemTime = NuggetsAndFriesTime; itemName = NuggetsAndFries;
            break;
        default:
            System.out.println("not a valid food item, pick another");
            return 0;
    }

        int calcTime = itemTime;

        for (int item : queue) {

            calcTime += item;

        }
        queue.add(itemTime);
        ActiveQueue.add(itemName);
        //int position = ActiveQueue.size();
        System.out.println(itemName + " has been added to the queue.");

        return calcTime;


}
public boolean cancelOrder(String name){
    String index = name.trim().toLowerCase().replaceAll("\\s+","");
    for(int i= 0; i< ActiveQueue.size();i++){
        String normal = ActiveQueue.get(i).trim().toLowerCase().replaceAll("\\s+","");

        if (index.equals(normal)){
            String removeItem = ActiveQueue.get(i);
            ActiveQueue.remove(i);
            queue.remove(i);
            System.out.println("order " + removeItem + " has been cancelled.");
            return true;
        }
    }
    System.out.println("No matching order found.");
    return false;

}
public void ViewOrders(){
    if(ActiveQueue.isEmpty()){
        System.out.println("no active orders. ");

    }
    System.out.println("\n Current orders in queue: ");
    for(int i = 0; i < ActiveQueue.size(); i++){
        String name = ActiveQueue.get(i);
        int time = queue.get(i);
        System.out.println((i+1) + ". " + name + " (" + time + "min)");
    }

    }

}
