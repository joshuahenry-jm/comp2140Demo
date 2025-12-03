import java.util.List;
import java.util.Iterator;

class StockManager {

    // List to hold menu items
    private final List<MenuItem> menuItems;

    // Reference to QueueManager
    private final QueueManager queueManager;

    // Constructor
    StockManager(List<MenuItem> menuItems, QueueManager queueManager) {
        this.menuItems = menuItems;
        this.queueManager = queueManager;
    }

    public boolean checkStock(Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            MenuItem menuItem = orderItem.getMenuItem();   // use the actual link
            int requested = orderItem.getQuantity();
            int available = menuItem.getStock();

            if (available < requested) {
                return false;  // not enough stock for this item
            }
        }
        return true; // all good
    }

    public void autoCancelIfOutOfStock() {
        List<Order> active = queueManager.getActiveOrders();

        for (Order order : active) {
            if (!checkStock(order)) {
                System.out.println(
                    "Order #" + order.getOrderId()
                    + " has been cancelled due to insufficient stock."
                );
                order.cancelOrder(); // This will call QueueManager.removeOrder(this)
            }
        }
    }


}