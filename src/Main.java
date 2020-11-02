import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;

public class Main {

    static HashMap<String, String> usersMap;
    static String currentLoggedIn="";
    static Account currentLoggedInAccount=null;
    static HashMap<String, Account> usersAccountMap;
    static HashMap<String, String> objectsMap;
    static HashMap<String,Product> products;
    static HashMap<String,PremiumAccount> premiumAccounts;
    static HashMap<String,Supplier> suppliers;

    static ObjectsFactory factory;

    public static void main(String[] args) throws InvalidArgumentException {
        Scanner scanner = new Scanner(System.in);
        usersMap = new HashMap<>();
        usersAccountMap = new HashMap<>();
        objectsMap = new HashMap<>();
        products = new HashMap<>();
        premiumAccounts = new HashMap<>();
        suppliers = new HashMap<>();
        factory = new ObjectsFactory();

        System.out.println("1. Add WebUser");
        System.out.println("2. Remove WebUser");
        System.out.println("3. Login WebUser");
        System.out.println("4. LogOut WebUser");
        System.out.println("6. Display Last Order");

        String userChoice = "";
        String Login_id;
        String Product_name;
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

            case 7:
                System.out.println("Please enter Product name");
                Product_name = scanner.nextLine();
                LinkProduct(Product_name);
                break;

            case 8:
                AddProduct();
                break;

            case 9:
                System.out.println("Please enter Product name");
                Product_name = scanner.nextLine();
                DeleteProduct(Product_name);
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

        if (currentLoggedIn != null) {
            // TODO: change the user state to blocked
            LogoutWebUser(currentLoggedIn);
            System.out.println("Successfully logged in to the system");
        }

        // Validate user password
        System.out.println("Enter password");
        String password = scanner.nextLine();
        if (usersMap.get(Login_id).equals(password)) {
            currentLoggedIn = Login_id;
            currentLoggedInAccount = usersAccountMap.get(Login_id);
            // Need to change the user state to active - meaning he is logged in
            // TODO: Change the webuser state

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
        if(usersMap.containsKey(Login_id)) {
            //TODO : Add while loop
            System.out.println("Login id already exists. Please try again.");
            return;
        }
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
        WebUser new_webUser = new WebUser(Login_id, password, new_customer);

        // Create the account according to the user type
        System.out.println("Do you have a premium account? (Y/N)");
        String answer = scanner.nextLine().toLowerCase();
        Account new_account;
        // The Date is the id of the shopping cart
        Date shoppingCartDate = new Date();
        if (answer.equals("y")) {
            new_account = new PremiumAccount(account_id, account_billing_address,
                    new_customer, new ShoppingCart(shoppingCartDate, new_webUser));
        }
        else{
            new_account = new Account(account_id,account_billing_address, new_customer,
                    new ShoppingCart(shoppingCartDate,new_webUser));
        }

        new_customer.setAccount(new_account);
        new_customer.setWebUser(new_webUser);

        // Add the objects to the Data structure
        objectsMap.put(customer_id, "Customer");
        objectsMap.put(Login_id, "WebUser");
        objectsMap.put(account_id, "Account");
        objectsMap.put(shoppingCartDate.toString(), "ShoppingCart");

    }

    public static void Displayorder(){
        if(currentLoggedIn!=null) {
            List<Order> ord = currentLoggedInAccount.getOrders();
            int size = currentLoggedInAccount.getOrderSize();
            if (ord.isEmpty())
                System.out.println("There is no orders");
            else {
                // Get the last order of the current logged in user
                Order LastOrder = ord.get(size - 1);
                System.out.println(LastOrder);
            }
        }
        else {
            System.out.println("No account is logged in");
        }

    }

    public static void LinkProduct(String product_name){
        Product pro = null;
        //check if the product exist in the system
        if (products.containsKey(product_name)){
                pro = products.get(product_name);
        }
        if (pro==null){
            System.out.println("Product not in the system");
        }
        //check if the account logged in is premium, if it is premium link the product to this account
        if (premiumAccounts.containsKey(currentLoggedIn)){
            PremiumAccount Pre = premiumAccounts.get(currentLoggedIn);
            Pre.addProduct(pro);
        }
        else {
            System.out.println("User not Premium");
        }
    }

    public static void AddProduct() throws InvalidArgumentException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Product id");
        String product_id = scanner.nextLine();
        while (objectsMap.get(product_id) != null){
            System.out.println("id already exist , please write another id\n");
            product_id = scanner.nextLine();
        }
        System.out.println("Please enter Product name");
        String product_name = scanner.nextLine();
        System.out.println("Please enter Supplier id");
        String supplier_id = scanner.nextLine();
        while (objectsMap.get(supplier_id) != null && objectsMap.get(supplier_id)!="Supplier"){
            System.out.println("id already exist , please write another id\n");
            supplier_id = scanner.nextLine();
        }
        System.out.println("Please enter Supplier name");
        String supplier_name = scanner.nextLine();
        Supplier new_supplier = null;
        if(suppliers.containsKey(supplier_id)){
            new_supplier = suppliers.get(supplier_id);
        }
        else {
            new_supplier = new Supplier(supplier_id,supplier_name);
            suppliers.put(supplier_id,new_supplier);
            objectsMap.put(supplier_id,"Supplier");
        }
        Product new_product = new Product(product_id,product_name,new_supplier);
        products.put(product_id,new_product);
        objectsMap.put(product_id,"Product");
    }

    public static void DeleteProduct(String Product_name){
        boolean Exist = false;
        //Check if the product is in the system, if it is delete the product
        for(int i=0; i<products.size(); i++){
            if (Product_name.equals(products.get(i).getName())){
                products.remove(i);
                objectsMap.remove(products.get(i).getId());
                Exist = true;
            }
        }
        if(Exist==false){
            System.out.println("Product not in the system");
        }
    }

    public static void ShowAllObjects(){
        for (Map.Entry object : objectsMap.entrySet()) {
            System.out.println("the id i : "+ object.getKey() + " ,the Value is: " + object.getValue());
        }

    }





}
