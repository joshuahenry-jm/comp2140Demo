import java.util.List;
import java.util.Iterator;

public class stockManager{

    // List to hold menu items
    private List<MenuItem> menuItems;

    // Reference to QueueManager
    private QueueManager queueManager;

    // Constructor
    public StockManager(List<MenuItem> menuItems, QueueManager queueManager) {
        this.menuItems = menuItems;
        this.queueManager = queueManager;
    }

}

public boolean isItemAvailable(Order order) {
    for(OrderItem orderItem : order.getOrderItems()) {
        boolean itemFound = false;
        for(MenuItem menuItem : menuItems) {
            if(menuItem.getName().equals(orderItem.getName())) {
                itemFound = true;
                if(menuItem.getStock() < orderItem.getQuantity()) {
                    return false; // Not enough stock
                }
                break;
            }
        }
        if(!itemFound) {
            return false; // Item not found in menu
        }
    }
    return true; // All items are available
}

public void updateStock(Order order) {
    for(OrderItem orderItem : order.getOrderItems()){
        for(MenuItem menuItem : menuItems) {
            if(menuItem.getName().equals(orderItem.getName())) {
                int newStock = menuItem.getStock() - orderItem.getQuantity();
                menuItem.setStock(newStock);
                break;
            }
        }
    }
}

public void restockItem(String itemName, int quantity) {
    for(MenuItem menuItem : menuItems) {
        if(menuItem.getName().equals(itemName)) {
            int newStock = menuItem.getStock() + quantity;
            menuItem.setStock(newStock);
            break;
        }
    }
}

public void autocancelIfOutOfStock(){
    List<Order> active = queueManager.getActiveOrders(); //Change if name different
    cancelOutOfStockOrders(active);

    List<Order> pending = queueManager.getPendingOrders(); //Change if name different
    cancelOutOfStockOrders(pending);
}

private void cancelOutOfStockOrders(List<Order> orders){
    Iterator<Order> it = orders.iterator();
    while(it.hasNext()){
        Order order = it.next();
        if(!isItemAvailable(order)){
            it.remove();
            notificationService.notifyCancellation(order); //Assuming notificationService is defined
        }
    }
}