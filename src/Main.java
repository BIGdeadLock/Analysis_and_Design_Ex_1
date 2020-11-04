import com.sun.javaws.exceptions.InvalidArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    static int id =0;

    static ObjectsFactory factory;

    public static void main(String[] args) throws InvalidArgumentException, ParseException {
        Scanner scanner = new Scanner(System.in);
        usersMap = new HashMap<>();
        usersAccountMap = new HashMap<>();
        objectsMap = new HashMap<>();
        products = new HashMap<>();
        premiumAccounts = new HashMap<>();
        suppliers = new HashMap<>();
        factory = new ObjectsFactory();

        //Saved Data
        Supplier S=new Supplier("123","Moshe");
        Product P=new Product("Bamba","Bamba",S);
        Product P2=new Product("Ramen","Ramen",S);

        Customer C=new Customer("DaniCustomer","Tel Mond","054123456","Dani@gmail.com", "DaniAccount","Tel Mond");
        WebUser W=new WebUser("Dani","Dani123",C);
        C.setWebUser(W);

        Customer C1=new Customer("DanaCustomer","Tel Mond","054654321","Dana@gmail.com","DanAccount", "Tel Mond");
        WebUser W1=new WebUser("Dana","Dana123",C1);
        C1.setWebUser(W1);
        ShoppingCart SC1=new ShoppingCart(new Date(),W1);
        PremiumAccount A1=new PremiumAccount("DanaAccount","Tel Mond",C1,SC1);
        C1.setAccount(A1);
        A1.addProduct(P);

        //there is no shopping cart in the factory
        factory.addObject("123",S);
        factory.addObject("Bamba",P);
        factory.addObject("Ramen",P2);
        factory.addObject("Dani",W);
        factory.addObject("DaniCustomer",C);
        factory.addObject("DaniAccount",C.getAccount());
        factory.addObject("DanaCustomer",C1);
        factory.addObject("Dana",W1);
        factory.addObject("DanaAccount",A1);
        objectsMap.put("123","Supplier");
        objectsMap.put("Bamba","Product");
        objectsMap.put("Ramen","Product");
        objectsMap.put("Dani","WebUser");
        objectsMap.put("DaniCustomer","Customer");
        objectsMap.put("DaniAccount","Account");
        objectsMap.put("DanaCustomer","Customer");
        objectsMap.put("Dana","WebUser");
        objectsMap.put("DanaAccount","Account");
        usersMap.put("Dani","Dani123");
        usersMap.put("Dana","Dana123");


        System.out.println("1. Add WebUser");
        System.out.println("2. Remove WebUser");
        System.out.println("3. Login WebUser");
        System.out.println("4. Logout WebUser");
        System.out.println("5: Make order");
        System.out.println("6. Display order");
        System.out.println("7. Link Product");
        System.out.println("8. Add Product");
        System.out.println("9. Delete Product");
        System.out.println("10. ShowAllObjects");
        System.out.println("11. ShowObjectId");

        String userChoice = scanner.nextLine();
        String Login_id;
        String Product_name;
        switch (userChoice){
            case "Add WebUser":
                try {
                    System.out.println("Enter new login id");
                    Login_id = scanner.nextLine();
                    AddWebUser(Login_id);
                }
                catch(InvalidArgumentException e){
                    e.printStackTrace();
                }
                break;

            case "Remove WebUser":
                System.out.println("Enter login id to remove");
                Login_id = scanner.nextLine();
                RemoveWebUser(Login_id);
                break;

            case "Login WebUser":
                System.out.println("Enter login id to log in");
                Login_id = scanner.nextLine();
                LoginWebUser(Login_id);
                break;

            case "Logout WebUser":
                System.out.println("Enter login id to log out");
                Login_id = scanner.nextLine();
                LogoutWebUser(Login_id);
                break;

            case "Make order":
                Makeorder();
                break;

            case "Display order":
                Displayorder();
                break;

            case "Link Product":
                System.out.println("Please enter Product name");
                Product_name = scanner.nextLine();
                LinkProduct(Product_name);
                break;

            case "Add Product":
                AddProduct();
                break;

            case "Delete Product":
                System.out.println("Please enter Product name");
                Product_name = scanner.nextLine();
                DeleteProduct(Product_name);
                break;

            case "ShowAllObjects":
                ShowAllObjects();
                break;

            case "ShowObjectId":
                System.out.println("Please enter object id");
                String object_id = scanner.nextLine();
                ShowObjectId(object_id);
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

        while (usersMap.containsKey(Login_id)) {
            System.out.println("Login id already exists. Please try again.");
            System.out.println("Enter Login id again");
            Login_id = scanner.nextLine();
        }
        usersMap.put(Login_id, password);

        //create the user's customer profile
        System.out.println("Building customer profile...");
        System.out.println("Please enter your customer id");
        String customer_id = scanner.nextLine();
        // Check if the id already exist. If Yes - ask the user's input again
        while (factory.getObjectType(customer_id) == null){
            System.out.println("Customer id already exists. Please try again.");
            System.out.println("Enter Customer id again");
            customer_id = scanner.nextLine();
        }
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
        // Check if the id already exist. If Yes - ask the user's input again
        while (factory.getObjectType(account_id) == null){
            System.out.println("Account id already exists. Please try again.");
            System.out.println("Enter Account id again");
            account_id = scanner.nextLine();
        }

        System.out.println("Please enter your billing address");
        String account_billing_address = scanner.nextLine();


        //set customer -> set webUser with customer, set account with customer -> set customer's account
        Customer new_customer = new Customer(customer_id,customer_address,customer_phone_number,customer_email,
                account_id, account_billing_address
                );
        WebUser new_webUser = new WebUser(Login_id, password, new_customer);

        // Create the account according to the user type
        System.out.println("Do you have a premium account? (Y/N)");
        String answer = scanner.nextLine().toLowerCase();
        Account new_account;
        // The Date is the id of the shopping cart
        Date shoppingCartDate = new Date();
        ShoppingCart new_shoppingcart = new ShoppingCart(shoppingCartDate, new_webUser);
        if (answer.equals("y")) {
            new_account = new PremiumAccount(account_id, account_billing_address,
                    new_customer, new_shoppingcart);
        }
        else{
            new_account = new Account(account_id,account_billing_address, new_customer,
                    new_shoppingcart);
        }

        new_customer.setAccount(new_account);
        new_customer.setWebUser(new_webUser);

        // Add the objects to the Data structures
        objectsMap.put(customer_id, "Customer");
        objectsMap.put(Login_id, "WebUser");
        objectsMap.put(account_id, "Account");
        objectsMap.put(shoppingCartDate.toString(), "ShoppingCart");
        factory.addObject(customer_id, new_customer);
        factory.addObject(Login_id, new_webUser);
        factory.addObject(account_id, new_account);
        factory.addObject(shoppingCartDate.toString(), new_shoppingcart);

    }

    public static void Makeorder() throws InvalidArgumentException, ParseException {
        if(currentLoggedInAccount == null){
            System.out.println("you cant make an order without be loged in");
        }
        /*else if(!(currentLoggedInAccount instanceof  PremiumAccount)){
            System.out.println("you are not a premium, you cant buy");
        }*/
        else {
            System.out.println("please enter the id of the user you want to but products from:\n");
            Scanner scanner = new Scanner(System.in);
            String idTobuyFrom = scanner.nextLine();
            //check if that user exists
            while (usersMap.get(idTobuyFrom) == null) {
                System.out.println("user doesn't exists , please write another id\n");
                idTobuyFrom = scanner.nextLine();
            }
            List userProducts = new ArrayList();
            Account user = usersAccountMap.get(idTobuyFrom);
            if (user instanceof PremiumAccount) {
                userProducts = ((PremiumAccount) user).getProducts();
            }
            System.out.println("these are the products you can choose from\n");
            for (int i = 0; i < userProducts.size(); i++) {
                System.out.println(userProducts.get(i));
            }
            String input;
            boolean didOrder = false;
            int sum=0;
            do {
            System.out.println("Do you want to buy anything?(Y/N)\n");
            input = scanner.next();
                if (input.equals("Y")) {
                    didOrder = true;
                    String orderName = scanner.next();
                    for (int i = 0; i < userProducts.size(); i++) {
                        if(((Product) userProducts.get(i)).getId().equals(orderName)){
                            System.out.println("How many?\n");
                            String amount = scanner.next();
                            sum +=Integer.parseInt(amount);
                        }
                        else{
                            System.out.println("Product not in the options");
                        }
                    }
                }
            }
            while (!input.equals("N"));

            if (didOrder) {
                while(objectsMap.containsKey(Integer.toString(id))){
                    id++;
                }
                Date ordered = new Date();
                Date shipped = ordered;
                System.out.println("What is your shipping address?");
                String address = scanner.nextLine();
                Order ord = new Order(Integer.toString(id),ordered, shipped, address, sum, currentLoggedInAccount);
                objectsMap.put(ord.getNumber(),"Order");
                factory.addObject(ord.getNumber(),ord);
                currentLoggedInAccount.addOrder(ord);
                while(objectsMap.containsKey(Integer.toString(id))){
                    id++;
                }
                System.out.println("Do you have something to add?");
                String details = scanner.nextLine();
                System.out.println("Do you want to pay immediate or delayed");
                String pay = scanner.nextLine();
                if(pay.equals("immediate")){
                    System.out.println("Do you have a phone confirmation (Y/N)?");
                    String con = scanner.nextLine();
                    boolean ans = false;
                    if(con.equals("Y")){
                        ans = true;
                    }
                    ImmediatePayment impay = new ImmediatePayment(Integer.toString(id),ordered,(float)sum,details,ord,currentLoggedInAccount,ans);
                    objectsMap.put(impay.getId(),"Immediate Payment");
                    factory.addObject(impay.getId(),impay);
                }
                else{
                    System.out.println("When do you want to pay?(DD/MM/YYYY)");
                    String Dat = scanner.nextLine();
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(Dat);
                    DelayedPayment depay = new DelayedPayment(Integer.toString(id),ordered,(float)sum,details,ord,currentLoggedInAccount,date);
                    objectsMap.put(depay.getId(),"Delayed Payment");
                    factory.addObject(depay.getId(),depay);
                }
                System.out.println("Order added successfully \n");
            }
        }
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
        if (factory.getObjectType(product_name) instanceof Product){
                pro = (Product) factory.objectMap.get(product_name);
        }
        if (pro==null){
            System.out.println("Product not in the system");
        }
        //check if the account logged in is premium, if it is premium link the product to this account
        if (factory.getObjectType(currentLoggedIn) instanceof PremiumAccount){
            PremiumAccount Pre = (PremiumAccount) factory.objectMap.get(currentLoggedIn);
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
        if(factory.objectMap.get(supplier_id)==null){
            new_supplier = suppliers.get(supplier_id);
        }
        else {
            new_supplier = new Supplier(supplier_id,supplier_name);
            factory.addObject(supplier_id,new_supplier);
            objectsMap.put(supplier_id,"Supplier");
        }
        Product new_product = new Product(product_id,product_name,new_supplier);
        factory.addObject(product_id,new_product);
        objectsMap.put(product_id,"Product");
    }

    public static void DeleteProduct(String Product_name){
        boolean Exist = false;
        //Check if the product is in the system, if it is delete the product
        if(factory.getObjectType(Product_name) instanceof Product ){
                factory.objectMap.remove(Product_name);
                objectsMap.remove(Product_name);
                Exist = true;
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

    /**
     * The function will get an object id and will print all of its details.
     *
     * @param id - String | Representing and id of an existing object in the system
     */
    public static void ShowObjectId(String id){
        System.out.println(factory.getObjectType(id));
    }




}
