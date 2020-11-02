import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;

public class Main {

    static HashMap<String, String> usersMap;
    static String currentLoggedIn="";
    static Account currentLoggedInAccount=null;
    static HashMap<String, Account> usersAccountMap;
    static HashMap<String, String> objectsMap;
    static ArrayList<Product> products;
    static HashMap<String,PremiumAccount> premiumAccounts;

    public static void main(String[] args) throws InvalidArgumentException {
        Scanner scanner = new Scanner(System.in);
        usersMap = new HashMap<>();
        usersAccountMap = new HashMap<>();
        objectsMap = new HashMap<>();
        products = new ArrayList<>();
        premiumAccounts = new HashMap<>();

        System.out.println("1. Add WebUser");
        System.out.println("2. Remove WebUser");
        System.out.println("3. Login WebUser");
        System.out.println("4. LogOut WebUser");
        System.out.println("6. Display Last Order");

        String userChoice = "";
        String Login_id;
        switch (Integer.parseInt(userChoice)) {

            case 1:
                try {
                    System.out.println("Enter new login id");
                    Login_id = scanner.nextLine();
                    AddWebUser(Login_id);
                }
                catch(InvalidArgumentException e){
                    e.printStackTrace();
                }
                break;

            case 2:
                System.out.println("Enter login id to remove");
                Login_id = scanner.nextLine();
                RemoveWebUser(Login_id);
                break;

            case 3:
                System.out.println("Enter login id to log in");
                Login_id = scanner.nextLine();
                LoginWebUser(Login_id);
                break;

            case 4:
                System.out.println("Enter login id to log out");
                Login_id = scanner.nextLine();
                LogoutWebUser(Login_id);
                break;

            case 6:
                Displayorder();
                break;


            case 10:
                ShowAllObjects();
                break;


        }
    }

    private static void LogoutWebUser(String Login_id) {
        if(currentLoggedIn.equals(Login_id)) {
            currentLoggedInAccount = null;
            currentLoggedIn = "";
        }

    }

    /**
     * The function will get a user name and validate the user credentials.
     * After that the user will be logged in
     * @param Login_id - String | the username
     */
    private static void LoginWebUser(String Login_id) {
        Scanner scanner = new Scanner(System.in);

        if (currentLoggedIn != null)
            LogoutWebUser(currentLoggedIn);

        // Validate user password
        System.out.println("Enter password");
        String password = scanner.nextLine();
        if (usersMap.get(Login_id).equals(password)) {
            currentLoggedIn = Login_id;
            currentLoggedInAccount = usersAccountMap.get(Login_id);
        } else
            System.out.println("Incorrect password");

    }


    public static void RemoveWebUser(String Login_id){
        if(usersMap.containsKey(Login_id)) {
            usersMap.remove(Login_id);
            usersAccountMap.remove(Login_id);
            if(currentLoggedIn.equals(Login_id)){
                currentLoggedInAccount=null;
                currentLoggedIn="";
            }
        }
        else
            System.out.println(Login_id + " not in the system");
    }


    public static void AddWebUser(String Login_id)throws InvalidArgumentException {
        Scanner scanner = new Scanner(System.in);
        //create the user
        System.out.println("Enter password");
        String password = scanner.nextLine();
        // If the user already exist in the system - don't add it
        if(usersMap.containsKey(Login_id))
            return;
        usersMap.put(Login_id, password);

        //create the user's customer profile
        System.out.println("Building customer profile...");
        System.out.println("Please enter your customer id");
        String customer_id = scanner.nextLine();
        System.out.println("Please enter your address");
        String customer_address = scanner.nextLine();
        System.out.println("Please enter your phone number");
        String customer_phone_number = scanner.nextLine();
        System.out.println("Please enter your email address");
        String customer_email = scanner.nextLine();

        //create the user's account
        System.out.println("Building customer account...");
        System.out.println("Please enter your account id");
        String account_id = scanner.nextLine();
        System.out.println("Please enter your billing address");
        String account_billing_address = scanner.nextLine();


        //set customer -> set webUser with customer, set account with customer -> set customer's account
        Customer new_customer = new Customer(customer_id,customer_address,customer_phone_number,customer_email);
        WebUser new_webUser = new WebUser(Login_id, password, UserState.New, new_customer);

        // Create the account according to the user type
        System.out.println("Do you have a premium account? (Y/N)");
        String answer = scanner.nextLine().toLowerCase();
        Account new_account;
        // The Date is the id of the shopping cart
        Date shoppingCartDate = new Date();
        if (answer.equals("y")) {
            new_account = new PremiumAccount(account_id, account_billing_address, false, new Date(),
                    null, 0, new_customer, new ShoppingCart(shoppingCartDate, new_webUser));
        }
        else{
            new_account = new Account(account_id,account_billing_address,false,new Date(),
                    null, 0,new_customer,new ShoppingCart(shoppingCartDate,new_webUser));
        }

        new_customer.setAccount(new_account);
        new_customer.setWebUser(new_webUser);

        // Add the objects to the Data structure
        objectsMap.put("Customer", customer_id);
        objectsMap.put("WebUser", Login_id);
        objectsMap.put("Account", account_id);
        objectsMap.put("ShoppingCart", shoppingCartDate.toString());

    }

    public static void Displayorder(){
        List<Order> ord=currentLoggedInAccount.getOrders();
        int size= currentLoggedInAccount.getOrderSize();
        if(ord.isEmpty())
            System.out.println("there is no orders");
        else {
            // Get the last order of the current logged in user
            Order LastOrder = ord.get(size-1);
            System.out.println(LastOrder);
        }

    }

    public static void LinkProduct(String product_name){
        Product pro = null;
        for(int i=0; i<products.size(); i++){
            if (product_name.equals(products.get(i).getName())){
                pro = products.get(i);
            }
        }
        if (pro==null){
            System.out.println("Product not in the system");
        }
        if (premiumAccounts.containsKey(currentLoggedIn)){
            PremiumAccount Pre = (PremiumAccount) currentLoggedInAccount;
            Pre.addProduct(pro);
        }
        else {
            System.out.println("User not Premium");
        }
    }

    public static void ShowAllObjects(){
        for (Map.Entry object : objectsMap.entrySet()) {
            System.out.println("the id i : "+ object.getKey() + " ,the Value is: " + object.getValue());
        }

    }





}
