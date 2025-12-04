class MenuItem {
    private String name;
    private String ingredients;
    private double price;
    private int stockQuantity;
    private int prepTime;
    private int itemID;

    // Constructor used when there are no ingredients (e.g. basic items)
    public MenuItem(String name, double price, int prepTime, int itemID) {
        this(name, "", price, prepTime, itemID);
    }

    // Constructor used for items with ingredients
    public MenuItem(String name, String ingredients, double price, int prepTime, int itemID) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.prepTime = prepTime;       //  now actually set
        this.itemID = itemID;

        // Temporary default stock so items are "in stock" for now
        this.stockQuantity = 10;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getItemID() { return itemID; }
    public int getPrepTime() { return prepTime; }
    public String getIngredients() { return ingredients; }

    public boolean isInStock() { return stockQuantity > 0; }
    public int getStockQuantity() { return stockQuantity; }
    public int getStock() { return stockQuantity; }

    public void setStock(int newStock) { this.stockQuantity = newStock; }
    public void updateStock(int newStock) { this.stockQuantity = newStock; }
    public void setPrepTime(int prepTime) { this.prepTime = prepTime; }
}
