import java.util.ArrayList;
//import java.util.HashMap;


public class Admin extends User {

    
    private Menu menu;
    private static ArrayList<MenuItem> menuList;
    //HashMap<Integer, String> map = new HashMap<>();
    public final int  adminID = 006;
   
    public Admin(String username, String email, String password, int userID, boolean isguest) {
        super(username, email, password, userID, false);
       // map.put(userID, username);
    }
 /* 
    public void addMenuItem(MenuItem item) {
        menuList.add(item);
    }

    public ArrayList<MenuItem> getMenu() {
        return menuList;
    }
    public void updatePrepTime(int id, int newPrepTime) {
    for (MenuItem item : menuList) {
        if (item.getItemID() == id) {
            item.setPrepTime(newPrepTime);
            return;
        }
    }
}

public void updatePrice(int id, double newPrice) {
    for (MenuItem item : menuList) {
        if (item.getItemID() == id) {
            item.setPrice(newPrice);
            return;
        }
    }
}

public void updateName(int id, String newName) {
    for (MenuItem item : menuList) {
        if (item.getItemID() == id) {
            item.setName(newName);
            return;
        }
    }
}
}
*/    
}
