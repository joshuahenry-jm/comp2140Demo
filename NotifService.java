public class NotifService {
    private String orderStatus; // "READY", "CANCELED", or "PROCESSING"
    
    public NotifService(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public void displayOrderStatus() {
        if ("READY".equals(orderStatus)) {
            System.out.println("Order is READY!");
        } 
        else if ("CANCELED".equals(orderStatus)) {
            System.out.println("Order has been CANCELED.");
        } 
        else {
            System.out.println("Order is being processed.");
        }
    }
    
    public String getOrderStatus() {
        return orderStatus;
    }
    
    public void setOrderStatus(String status) {
        this.orderStatus = status;
    }
}