import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

class Order {
    private static int idCounter = 1;

    private int orderId;
    private Date orderTime;
    private String status;
    private int estimatedWaitTime;
    private User user;
    private List<OrderItem> items;
    NotifService notifService = new NotifService(null);

    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Order() {
        this.orderId = idCounter++;
        this.orderTime = new Date();
        this.status = "pending";
        this.items = new ArrayList<>();
        this.estimatedWaitTime = QueueManager.getInstance().calculateQueueWaitTime();
    }

    public void calculateWaitTime() {
        this.estimatedWaitTime = QueueManager.getInstance().calculateQueueWaitTime();
    }

    public void cancelOrder() {
        this.status = "cancelled";

        for (OrderItem oi : items) {
            MenuItem item = oi.getMenuItem();
            int qty = oi.getQuantity();
            int currentStock = item.getStockQuantity();
            item.updateStock(currentStock + qty);
        }
        QueueManager.getInstance().removeOrder(this);
        
        notifService.setOrderStatus("CANCELED");
    }

    public void addItem(MenuItem item, int quantity) {
        if (!item.isInStock() || item.getStockQuantity() < quantity) {
            throw new RuntimeException("Item out of stock: " + item.getName());
        }

        items.add(new OrderItem(item, quantity));
        item.updateStock(item.getStockQuantity() - quantity);
    }

    public void generateTicket() {
        double total = 0.0;

        String border = "═".repeat(42);
        String thinBorder = "─".repeat(42);

        System.out.println("\n" + border);
        System.out.println("        NARDO'S RESTAURANT");
        System.out.println("     Delicious Food • Fast Service");
        System.out.println(thinBorder);
        System.out.println("Order ID: #" + String.format("%04d", orderId));
        System.out.println("Date    : " + DATE_FORMAT.format(orderTime));
        System.out.println("Status  : " + status.toUpperCase());
        System.out.println(thinBorder);

        System.out.printf(" %-4s %-20s %6s %8s\n", "Qty", "Item", "Price", "Subtotal");
        System.out.println(thinBorder);

        for (OrderItem oi : items) {
            MenuItem item = oi.getMenuItem();
            double subtotal = oi.getSubtotal();
            total += subtotal;

            System.out.printf(" %2dx %-20s %6.2f %8.2f\n",
                    oi.getQuantity(),
                    truncate(item.getName(), 20),
                    item.getPrice(),
                    subtotal);
        }

        System.out.println(thinBorder);
        System.out.printf(" %-30s %10.2f\n", "TOTAL:", total);
        System.out.printf(" %-30s %10d min\n", "Est. Wait Time:", estimatedWaitTime);
        System.out.println(thinBorder);
        System.out.println("     Thank you for choosing Nardo's!");
        System.out.println("        Your order is being prepared");
        System.out.println(border + "\n");
    }

    private String truncate(String name, int maxLen) {
        return name.length() <= maxLen ? name : name.substring(0, maxLen - 3) + "...";
    }


    public int getOrderId() { return orderId; }
    public String getStatus() { return status; }
    public int getEstimatedWaitTime() { return estimatedWaitTime; }
    public List<OrderItem> getOrderItems() { return items; }

    public void setUser(User user) { this.user = user; }
}

