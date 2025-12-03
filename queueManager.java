import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.print.attribute.standard.Media;

class QueueManager {
    private static final QueueManager INSTANCE = new QueueManager();
    // lightweight order lists for APIs that other classes expect
    private final ArrayList<Order> activeOrders = new ArrayList<>();
    private final ArrayList<Order> pendingOrders = new ArrayList<>();

    // Menu instance to pull items from
    private final Menu menu;
    private final StockManager stockManager;

    private QueueManager() {
        this.menu = new Menu();
        this.stockManager = new StockManager(menu.getMenu(), this);
    }

    public static QueueManager getInstance() { return INSTANCE; }

    public Menu getMenu() { return menu; }
    public StockManager getStockManager() { return stockManager; }


    // Added to satisfy StockManager/Order usage. These return simple lists.
    public List<Order> getActiveOrders() { return new ArrayList<>(activeOrders); }
    public List<Order> getPendingOrders() { return new ArrayList<>(pendingOrders); }

    public void displayMenu() { menu.displayMenu(); }

    private MenuItem findMenuItemByInput(String input) {
        input = input.trim();
        String inputLower = input.toLowerCase();

        // Try to match by name (case-insensitive, partial match)
        for (MenuItem item : menu.getMenu()) {
            String itemNameLower = item.getName().toLowerCase();
            if (itemNameLower.contains(inputLower) ||
                inputLower.contains(itemNameLower.split(" ")[0])) {
                return item;
            }
        }

        // If not found, try by ID
        try {
            int itemId = Integer.parseInt(input);
            for (MenuItem item : menu.getMenu()) {
                if (item.getItemID() == itemId) {
                    return item;
                }
            }
        } catch (NumberFormatException e) {
            // not a number, ignore
        }

        return null; // no match
    }


    public int calculateQueueWaitTime() {
        int total = 0;
        for (Order order : activeOrders) {
            for (OrderItem oi : order.getOrderItems()) {
                MenuItem item = oi.getMenuItem();
                total += item.getPrepTime();
            }
        }
        return total;
    }

    public void removeOrder(Order o) {
        activeOrders.remove(o);
        pendingOrders.remove(o);
    }

    public int foodList() {
        Scanner scan = new Scanner(System.in);

        Order order = new Order();  // one order per call

        while (true) {
            // You can remove this if Main already called displayMenu()
            System.out.println();
         

            System.out.print("Enter menu item name or ID (or type 'done' to finish): ");
            String input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            MenuItem selectedItem = findMenuItemByInput(input);

            if (selectedItem == null) {
                System.out.println("Not a valid food item, try again.");
                continue;
            }

            // Ask for quantity
            System.out.print("Enter quantity for \"" + selectedItem.getName() + "\": ");
            String qtyInput = scan.nextLine().trim();
            int quantity;

            try {
                quantity = Integer.parseInt(qtyInput);
                if (quantity <= 0) {
                    System.out.println("Quantity must be at least 1.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity, please enter a number.");
                continue;
            }

            // Try to add item to order (stock is checked inside addItem)
            try {
                order.addItem(selectedItem, quantity);
                System.out.println(quantity + " x " + selectedItem.getName() + " added to this order.");
                System.out.println("Remaining stock: " + selectedItem.getStockQuantity());
            } catch (RuntimeException e) {
                System.out.println("Sorry, " + e.getMessage());
            }
        }

        // If no items were added, cancel the whole order
        if (order.getOrderItems().isEmpty()) {
            System.out.println("No items added. Order cancelled.");
            return 0;
        }

        // Add the completed order to active orders
        activeOrders.add(order);

        // Update the order's own estimated wait time using current queue
        order.calculateWaitTime();

        // Compute total queue wait time for Main to display
        int waitTime = calculateQueueWaitTime();

        // Print ticket for this multi-item order
        order.generateTicket();

        return waitTime;
    }




    public boolean cancelOrder(String name) {
        if (activeOrders.isEmpty()) {
            System.out.println("No active orders to cancel.");
            return false;
        }

        String target = name.trim().toLowerCase().replaceAll("\\s+", "");

        for (int i = 0; i < activeOrders.size(); i++) {
            Order order = activeOrders.get(i);

            String orderLabel = "(no items)";
            if (!order.getOrderItems().isEmpty()) {
                MenuItem firstItem = order.getOrderItems().get(0).getMenuItem();
                orderLabel = firstItem.getName();
            }

            String normalized = orderLabel.trim().toLowerCase().replaceAll("\\s+", "");

            if (normalized.equals(target)) {
                order.cancelOrder();   // restores stock + removes from QueueManager’s lists
                System.out.println("Order for \"" + orderLabel + "\" has been cancelled.");
                return true;
            }
        }

        System.out.println("No matching order found.");
        return false;
    }



    public void ViewOrders() {
        if (activeOrders.isEmpty()) {
            System.out.println("No active orders.");
            return;
        }

        System.out.println("\n========== ACTIVE ORDERS ==========");

        for (int i = 0; i < activeOrders.size(); i++) {
            Order order = activeOrders.get(i);

            System.out.println(
                    (i + 1) + ". Order #" + order.getOrderId()
                    + " (Est wait: " + order.getEstimatedWaitTime() + " min)"
            );

            // Print ALL items in the order
            for (OrderItem oi : order.getOrderItems()) {
                MenuItem item = oi.getMenuItem();
                int qty = oi.getQuantity();

                System.out.println("   • " + qty + "x " + item.getName());
            }

            System.out.println(); // blank line between orders
        }
    }

}
