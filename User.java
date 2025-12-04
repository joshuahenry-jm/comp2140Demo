import java.util.HashMap;

public class User {
    protected String username;
    protected String password;
    protected String email;
    protected int userID;
    private boolean isguest;
    private HashMap<Integer, String> map = new HashMap<>();

    public User(String username, String email,String password,  int userID, boolean isguest) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userID = userID;
        this.isguest = isguest;

        map.put( userID, username);
    }
    // Getters and Setters

   public String getUsername(){
    return this.username;}

    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return this.password;}
    
    public void setPasswords(String password){
        this.password = password;}
    
    public String getEmail(){
        return this.email;}

    public void setemail(String email){
        this.email = email;
    
    }
    public int getUserID(){
        return this.userID;}
    
    public void setUserID(int userID){
        this.userID = userID;}


    public boolean isguest()
    {
        return this.isguest;


    }

    public void setisGuest(boolean isguest){
        this.isguest = isguest;

    }
    public void displayInfo(){
        System.out.println("Username: " + this.username);
        System.out.println("Email: " + this.email);
        System.out.println("User ID: " + this.userID);
        System.out.println("Guest: " + this.isguest);

    }
    // login()
    public boolean login(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) &&
               this.password.equals(enteredPassword);
    }

    // createProfile()
    public void createProfile(String newUsername, String newEmail) {
        this.username = newUsername;
        this.email = newEmail;

        System.out.println("Profile created/updated successfully!");
    }

    //  placeOrder()
    public void placeOrder() {
        // For your demo this is enough
        System.out.println(username + " has placed an order.");
    }

    
    

}