 public class Admin extends User {

    public Admin(String username, String email, String password, int userID, boolean isguest) {
        super(username, email, password, userID, false);

    }


    


    

    public void updateMenu() {
        System.out.println("Menu updated.");
    }

    public void updateStock() {
        System.out.println("Stock updated.");
    }

    public void updatePreparationTime() {
        System.out.println("Preparation time updated.");
    }
}
 