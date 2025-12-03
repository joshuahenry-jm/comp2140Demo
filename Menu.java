import java.util.ArrayList;

class Menu{
    private ArrayList<MenuItem> menuList;

    public Menu() {
        this.menuList = new ArrayList<>();

        menuList.add(new MenuItem("Regular Hot Dog", 200.00,5 ,1));
        menuList.add(new MenuItem("Full House Hot Dog","Lettuce, Cheese, Ketchup,Mayo, Pepper", 250.00, 5, 2));
        menuList.add(new MenuItem("Corn Bread Corn Dog", "Lettuce, Cheese,Ketchup,Mayo, Pepper", 450.00, 5, 3));
        menuList.add(new MenuItem("Pitt Bull Made With Grotto Bread", "Lettuce, Cheese, Ketchup, Mustard,Mayo,Peppper", 700.00, 5, 4));
        menuList.add(new MenuItem("Sugar Dog Made With Sugar Bun", "Lettuce, Cheese, Ketchup,Mayo, Pepper", 450.00, 5, 5));
        menuList.add(new MenuItem("Raisin Dog", "Raisin Bread, Lettuce, Cheese, Ketchup, Mustard, Mayo", 450.00, 5, 6));
        menuList.add(new MenuItem("Chicken Burger","Cheese, Ketchup, Lettuce, Mayo, Mustard", 650.00, 10, 7));
        menuList.add(new MenuItem("Beef Burger","Cheese, Ketchup, Lettuce, Mayo, Mustard", 650.00, 10, 8));
        menuList.add(new MenuItem("Chicken Nugget Sandwich","Coco or Cornbread, Cheese, Ketchup, Mayo, Pepper", 650.00, 5, 9));
        menuList.add(new MenuItem("Mega Sausage", "Lettuce, Cheese, Ketchup, Mayo", 600.00, 5, 10));
        menuList.add(new MenuItem("Tastee Beef Patty", 350.00, 5, 11));
        menuList.add(new MenuItem("Tastee Chicken Patty", 400.00, 5, 12));
        menuList.add(new MenuItem("Tastee Cheese Patty", 420.00, 5, 13));
        menuList.add(new MenuItem("Coco Bread", 200.00, 5, 14));
        menuList.add(new MenuItem("Nuggets and Fries", "Ketchup, Mayo, Cheese, Pepper", 750.00, 5, 15));
        menuList.add(new MenuItem("Turkey Sandwich", "Coco Bread,Lettuce, Ketchup, Mayo, Cheese", 600.00, 5, 16));
        menuList.add(new MenuItem("Cheesy Fries", "Ketchup, Mayo, Cheese, Pepper", 350.00, 10, 17));
        menuList.add(new MenuItem("Regular Fries", "Ketchup, Mayo, Pepper", 250.00, 5, 18));
        menuList.add(new MenuItem("Deli Chicken Sandwich", "Coco Bread,Lettuce, Ketchup, Mayo, Pepper", 600.00, 5, 19));
        menuList.add(new MenuItem("Add On: Cheese", 50.00, 0, 20));
        menuList.add(new MenuItem("Add On: Plantain", 50.00, 5, 21));
        menuList.add(new MenuItem("Add On: Bacon", 200.00, 5, 22));

    }

    public void addMenuItem(MenuItem item) {
        menuList.add(item);
    }

    public ArrayList<MenuItem> getMenu() {
        return menuList;
    }


    public void displayMenu() {

    System.out.println("--------------------------------------MENU----------------------------------------------");
    
        // Table header
    System.out.printf("%-5s %-30s %-10s %-10s %-10s%n",
            "ID", "Name", "Price", "Prep(min)", "Ingredients");

    System.out.println("----------------------------------------------------------------------------------------");

    for (MenuItem item : menuList) {

        System.out.printf("%-5s %-30s %-10.2f %-10d %-10s%n",
                item.getItemID(),
                item.getName(),
                item.getPrice(),
                item.getPrepTime(),
                item.getIngredients());
    }
}


}