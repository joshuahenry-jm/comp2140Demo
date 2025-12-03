import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class QueueManager {
    private static QueueManager instance;
    private Queue<order> ActiveQueue;


    private QueueManager() {
        this.ActiveQueue = new LinkedList<>();

    }

    public static QueueManager getInstance() {
        if(instance == null){
            instance = new QueueManager();
        }
        return instance;
    }

    public void addOrder(order order) {
        int wait = EstimatedWaitTime();
        order.setEstimatedWaitTime(wait);
        if (ActiveQueue.size() == 30) {
            System.out.println("sorry order cannot be added now, try again later.");
        } else {

            ActiveQueue.offer(order);
            System.out.println("Order" + order.getOrderId() + "added to the queue.");
        }
    }
    public int EstimatedWaitTime(){
        int total = 0;
        for(order o : ActiveQueue){
            total += o.getEstimatedWaitTime();
        }
        return total;
    }


    public order processNextOrder() {
        if (!ActiveQueue.isEmpty()) {
            order nextOrder = ActiveQueue.poll();
            nextOrder.setStatus("completed");
            System.out.println("Processing Order: " + nextOrder.getOrderId());
            return nextOrder;
        } else {
            System.out.println("No orders in the queue to process.");
            return null;
        }
    }





    public boolean isQueueEmpty() {
        return ActiveQueue.isEmpty();
    }
    public Queue<order> getActiveOrders(){
        return ActiveQueue;
    }
}



    /*String burger = "burger";
    String hotDog = "Hot Dog";
    String fries = "fries";
    String cheesyFries = "Cheesy Fries";
    String NuggetsAndFries = "Nuggets and Fries";

    int burgerTime = 10;
    int hotdogTime = 5;
    int friesTime = 5;
    int cheesyFriesTime = 10;
    int NuggetsAndFriesTime = 5;

    public static QueueManager getInstance() {

    }


    public int foodList() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("What would you like to order?: ");
        String input = scan.nextLine();
        input = input.trim().toLowerCase().replaceAll("\\s+", "");

        Menu orderedItem = null;
        for (Menu item: menu.getMenu()) {
            if (item.getName().toLowerCase().replaceAll("\\s+", "")
                    .equals(input.replaceAll("\\s+", ""))) {
                orderedItem = item;
                break;

            }
        } if(orderedItem == null) {
            System.out.println("Not a valid food item, pick another.");
            return 0;
        }

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

        for (Menu item : ActiveQueue) {

            calcTime += item.getPrepTime();

        }
        calcTime += orderedItem.getPrepTime();
        ActiveQueue.add(orderedItem);
        System.out.println(itemName + " has been added to the queue.");

        return calcTime;


    }
    public boolean cancelOrder(String name){
        String index = name.trim().toLowerCase().replaceAll("\\s+","");
        for(int i= 0; i< ActiveQueue.size();i++){
            String normal = ActiveQueue.get(i).getName().trim().toLowerCase().replaceAll("\\s+","");

            if (index.equals(normal)){
                String removeItem = ActiveQueue.get(i).getName();
                ActiveQueue.remove(i);
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
    public int EstimateQueueWaitTime() {
        int waitTime = 0;
        for (Menu i : ActiveQueue) {
            waitTime += i;
        }
        return waitTime;
    }



}
*/