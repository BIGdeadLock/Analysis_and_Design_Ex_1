import com.sun.javaws.exceptions.InvalidArgumentException;
import sun.rmi.runtime.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static String currentLoggedIn="";
    static Account currentLoggedInAccount=null;
    static HashMap<String, String> usersMap;
    static HashMap<String, String> objectsMap;
    static HashMap<String,String> functions_call;
    static ObjectsFactory factory;

    public static void main(String[] args) throws InvalidArgumentException, ParseException {
        Scanner scanner = new Scanner(System.in);
        usersMap = new HashMap<>();
        objectsMap = new HashMap<>();
        factory = new ObjectsFactory();
        //Saved Data
        Supplier S = new Supplier("123", "Moshe");
        Product P = new Product("Bamba", "Bamba", S);
        Product P2 = new Product("Ramen", "Ramen", S);
        factory.addObject("123", S);
        factory.addObject("Bamba", P);
        factory.addObject("Ramen", P2);

        //Customer C=new Customer("DaniCustomer","Tel Mond","054123456","Dani@gmail.com", "DaniAccount","Tel Mond", false);
        WebUser W = new WebUser("Dani", "Dani123",
                "DaniCustomer", "Tel Mond", "054123456", "Dani@gmail.com", "DaniAccount", "Tel Mond",5, false,Integer.parseInt(factory.getNextFreeId()));
        //C.setWebUser(W);
        factory.addObject(Integer.toString(W.getShoppingCart().getId()),W.getShoppingCart());
        factory.addObject("Dani", W);
        factory.addObject("DaniCustomer", W.getCustomer());
        factory.addObject("DaniAccount", W.getCustomer().getAccount());

//
//        Customer C1=new Customer("DanaCustomer","Tel Mond","054654321","Dana@gmail.com",
//                "DanAccount", "Tel Mond", true, W);
        WebUser W1 = new WebUser("Dana", "Dana123",
                "DanaCustomer", "Tel Mond", "054654321", "Dana@gmail.com",
                "DanaAccount", "Tel Mond",500, true,Integer.parseInt(factory.getNextFreeId()));
        PremiumAccount PA=(PremiumAccount)(W1.getCustomer().getAccount());
        PA.addProduct(P);
        P.setQuantity(2);
        P.setPrice(5);
        //C1.setWebUser(W1);
        //ShoppingCart SC1=new ShoppingCart(new Date(),W1);
        //PremiumAccount A1=new PremiumAccount("DanaAccount","Tel Mond",C1,SC1);
        // C1.setAccount(A1);
        //A1.addProduct(P);

        //there is no shopping cart in the factory
        factory.addObject(Integer.toString(W1.getShoppingCart().getId()),W1.getShoppingCart());
        factory.addObject("DanaCustomer", W1.getCustomer());
        factory.addObject("Dana", W1);
        factory.addObject("DanaAccount", W1.getCustomer().getAccount());

        objectsMap.put("123", "Supplier");
        objectsMap.put("Bamba", "Product");
        objectsMap.put("Ramen", "Product");
        objectsMap.put("Dani", "WebUser");
        objectsMap.put("DaniCustomer", "Customer");
        objectsMap.put("DaniAccount", "Account");
        objectsMap.put("DanaCustomer", "Customer");
        objectsMap.put("Dana", "WebUser");
        objectsMap.put("DanaAccount", "Premium Account");
        objectsMap.put(Integer.toString(W.getShoppingCart().getId()),"ShoppingCart");
        objectsMap.put(Integer.toString(W1.getShoppingCart().getId()),"ShoppingCart");
        usersMap.put("Dani", "Dani123");
        usersMap.put("Dana", "Dana123");




        functions_call = new HashMap();
        functions_call.put("Add WebUser", "*");
        functions_call.put("Remove WebUser", "*");
        functions_call.put("Login WebUser", "*");
        functions_call.put("Logout WebUser", "*");
        functions_call.put("Make order", "");
        functions_call.put("Display order", "");
        functions_call.put("Link Product", "*");
        functions_call.put("Add Product", "");
        functions_call.put("Delete Product", "*");
        functions_call.put("ShowAllObjects", "");
        functions_call.put("ShowObjectId", "*");

        do {
            System.out.println("1. Add WebUser");
            System.out.println("2. Remove WebUser");
            System.out.println("3. Login WebUser");
            System.out.println("4. Logout WebUser");
            System.out.println("5. Make order");
            System.out.println("6. Display order");
            System.out.println("7. Link Product");
            System.out.println("8. Add Product");
            System.out.println("9. Delete Product");
            System.out.println("10. ShowAllObjects");
            System.out.println("11. ShowObjectId");
            String userChoice = scanner.nextLine();
            String[] userChoice_split = userChoice.split(" ");
            String argument = "", function_call = "";

            // Check the functions call map too see if the function the user has given
            // needs to have arguments or not
            for (String function : functions_call.keySet()) {
                if (userChoice.contains(function)) {
                    //if (functions_call.get(function).equals("*"))
                    argument = userChoice_split[userChoice_split.length - 1];


                    function_call = function;
                    break;
                }
            }

            switch (function_call) {
                case "Add WebUser":
                    try {
                        if (argument.equals("WebUser")){
                            System.out.println("No login id detected. Please try again with a login id");
                            break;
                        }
                        AddWebUser(argument);
                    } catch (InvalidArgumentException e) {
                        e.printStackTrace();
                    }
                    break;

                case "Remove WebUser":
                    if (argument.equals("WebUser")){
                        System.out.println("No login id detected. Please try again with a login id");
                        break;
                    }
                    RemoveWebUser(argument);
                    break;

                case "Login WebUser":
                    if (argument.equals("WebUser")){
                        System.out.println("No login id detected. Please try again with a login id");
                        break;
                    }
                    LoginWebUser(argument);
                    break;

                case "Logout WebUser":
                    if (argument.equals("WebUser")){
                        System.out.println("No login id detected. Please try again with a login id");
                        break;
                    }
                    LogoutWebUser(argument);
                    break;

                case "Make order":
                    if (function_call.contains(argument) )
                        Makeorder();

                    else
                        System.out.println("No arguments needed for this function");

                    break;


                case "Display order":
                    if (function_call.contains(argument) )
                        Displayorder();

                    else
                        System.out.println("No arguments needed for this function");


                    break;


                case "Link Product":
                    if (argument.equals("Product")){
                        System.out.println("No product id detected. Please try again with a product id");
                        break;
                    }
                    LinkProduct(argument);
                    break;

                case "Add Product":
                    if (function_call.contains(argument) )
                        AddProduct();

                    else
                        System.out.println("No arguments needed for this function");

                    break;



                case "Delete Product":
                    if (argument.equals("Product")){
                        System.out.println("No product id detected. Please try again with a product id");
                        break;
                    }
                    DeleteProduct(argument);
                    break;

                case "ShowAllObjects":
                    if (function_call.contains(argument) )
                        ShowAllObjects();

                    else
                        System.out.println("No arguments needed for this function");

                    break;

                case "ShowObjectId":
                    if (argument.equals("ShowObjectId")){
                        System.out.println("No object id detected. Please try again with a object id");
                        break;
                    }
                    ShowObjectId(argument);
                    break;

                default:
                    System.out.println("Command Not Found. Please Try Again");
                    break;
            }

        } while (true);
    }

    private static void LogoutWebUser(String Login_id) {
        if(currentLoggedIn.equals(Login_id)) {
            currentLoggedInAccount = null;
            currentLoggedIn = "";
            System.out.println("Successfully logged out");
        }
    }

    /**
     * The function will get a user name and validate the user credentials.
     * After that the user will be logged in
     * @param Login_id - String | the username
     */
    private static void LoginWebUser(String Login_id) {
        Scanner scanner = new Scanner(System.in);
        if (objectsMap.get(Login_id) == null){
            System.out.println("No user with that login id exists in the system. Please try again");
            return;
        }
        if (currentLoggedIn != "") {
            ((WebUser)factory.getObjectType(Login_id)).setState(UserState.Blocked);
            System.out.println("Another user is currently logged in, please try again later");
            return;
        }
        // Validate user password
        System.out.println("Enter password");
        String password = scanner.nextLine();
        //usersMap.get(Login_id)!=null &&  -- check if needed
        if (usersMap.get(Login_id).equals(password)) {
            currentLoggedIn = Login_id;
            WebUser LoggedIn= (WebUser)factory.getObjectType(Login_id);
            currentLoggedInAccount = LoggedIn.getCustomer().getAccount();
            // Need to change the user state to active - meaning he is logged in
            ((WebUser)factory.getObjectType(Login_id)).setState(UserState.Active);
            System.out.println("Successfully logged in to the system");
        }
        else
            System.out.println("Incorrect password");
    }

    public static void RemoveWebUser(String Login_id){
        if(usersMap.containsKey(Login_id)) {
            usersMap.remove(Login_id);
            factory.removeObject(Login_id);
            if(currentLoggedIn.equals(Login_id)){
                currentLoggedInAccount=null;
                currentLoggedIn="";
            }
            System.out.printf(Login_id+ " Deleted Successfully\n");
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
        // Check that the web user login is different from the customer id
        while (customer_id.equals(Login_id)){
            System.out.println("The customer id can't be like the login id. Please try again.");
            System.out.println("Enter Customer id again");
            customer_id = scanner.nextLine();
        }
        // Check if the id already exist. If Yes - ask the user's input again
        while (factory.getObjectType(customer_id) != null){
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
        // Check if the account id is different from the web user login id or customer id
        while (account_id.equals(Login_id) || account_id.equals(customer_id)){
            System.out.println("The Account id can't be like the login id or customer id. Please try again.");
            System.out.println("Enter Customer id again");
            account_id = scanner.nextLine();
        }        // Check if the id already exist. If Yes - ask the user's input again
        while (factory.getObjectType(account_id) != null){
            System.out.println("Account id already exists. Please try again.");
            System.out.println("Enter Account id again");
            account_id = scanner.nextLine();
        }

        System.out.println("Please enter your billing address");
        String account_billing_address = scanner.nextLine();

        System.out.println("what is your balance?");
        String balance = scanner.nextLine();

        // Create the account according to the user type
        System.out.println("Do you have a premium account? (Y/N)");
        String answer = scanner.nextLine().toLowerCase();
        WebUser new_webUser;
        Customer new_customer;
        Date shoppingCartDate = new Date();
        if (answer.equals("y")) {
            new_webUser = new WebUser(Login_id, password,
                    customer_id,customer_address,customer_phone_number,customer_email,
                           account_id, account_billing_address,Integer.parseInt(balance), true,Integer.parseInt(factory.getNextFreeId()));
        }
        else{
            new_webUser = new WebUser(Login_id, password,
                    customer_id,customer_address,customer_phone_number,customer_email,
                    account_id, account_billing_address,Integer.parseInt(balance), false,Integer.parseInt(factory.getNextFreeId()));
        }

        // Add the objects to the Data structures (id given automatically)
        objectsMap.put(customer_id, "Customer");
        objectsMap.put(Login_id, "WebUser");
        if(new_webUser.getCustomer().getAccount().getClass()==PremiumAccount.class)
            objectsMap.put(account_id, "PremiumAccount");
        else
            objectsMap.put(account_id, "Account");
        objectsMap.put(shoppingCartDate.toString(), "ShoppingCart");
        factory.addObject(shoppingCartDate.toString(),new_webUser.getShoppingCart());
        factory.addObject(new_webUser.getCustomer().getId(),new_webUser.getCustomer());
        factory.addObject(new_webUser.getLogin_id(),new_webUser);
        factory.addObject(new_webUser.getCustomer().getAccount().getId(),new_webUser.getCustomer().getAccount());


        System.out.println(Login_id + " You were successfully added to the system");

    }

    public static void Makeorder() throws InvalidArgumentException, ParseException {
        if(currentLoggedInAccount == null)
            System.out.println("You can't make an order without be logged in");
        else {
            System.out.println("Please enter the id of the user you want to buy from:");
            Scanner scanner = new Scanner(System.in);
            String idTobuyFrom = scanner.nextLine();
            //check if that user exists
            while (usersMap.get(idTobuyFrom) == null) {
                System.out.println("User doesn't exists , please write another id");
                idTobuyFrom = scanner.nextLine();
            }
            List userProducts;
            WebUser user = (WebUser) factory.getObjectType(idTobuyFrom);
            if (user.getCustomer().getAccount() instanceof PremiumAccount) {
                userProducts = ((PremiumAccount) user.getCustomer().getAccount()).getProducts();
                System.out.println("these are the products you can choose from:");
                for (int i = 0; i < userProducts.size(); i++) {
                    Product temp = (Product)userProducts.get(i);
                    System.out.println(temp.getName()+" Price: "+temp.getPrice()+" Quantity: "+temp.getQuantity() + "\n");
                }
                String input;
                boolean didOrder = false;
                boolean found = false;
                List <LineItem> lineItemList = new ArrayList<>();
                int sum = 0;
                do {
                    System.out.println("Do you want to buy anything?(Y/N)");
                    input = scanner.nextLine();
                    if (input.equals("Y")) {
                        didOrder = true;
                        System.out.println("Please write the name of the item you want to buy");
                        String orderName = scanner.nextLine();
                        found = false;
                        for (int i = 0; i < userProducts.size(); i++) {
                            if (((Product) userProducts.get(i)).getId().equals(orderName)) {
                                found = true;
                                System.out.println("How many?");
                                String amount = scanner.nextLine();
                                while(Integer.parseInt(amount) > ((Product) userProducts.get(i)).getQuantity()){
                                    System.out.println("please enter an amount the user has");
                                    amount = scanner.nextLine();
                                }
                                sum += Integer.parseInt(amount)*((Product) userProducts.get(i)).getPrice();
                                if(sum > currentLoggedInAccount.getBalance()){
                                    sum -= Integer.parseInt(amount)*((Product) userProducts.get(i)).getPrice();
                                    System.out.println("Sorry you don't have enough money");
                                    break;
                                }
                                LineItem lineItem= new LineItem(factory.getNextFreeId(),Integer.parseInt(amount),((Product) userProducts.get(i)).getPrice(),(Product) userProducts.get(i));
                                lineItemList.add(lineItem);
                                ((Product) userProducts.get(i)).setQuantity(((Product) userProducts.get(i)).getQuantity() - Integer.parseInt(amount));
                                factory.addObject(lineItem.getId(),lineItem);

                            }
                        }
                        if(!found){
                            System.out.println("Product not in the options");
                        }
                    }
                }
                while (!input.equals("N"));

                if (didOrder && sum != 0) {
                    currentLoggedInAccount.setBalance(currentLoggedInAccount.getBalance()-sum);
                    user.getCustomer().getAccount().setBalance(user.getCustomer().getAccount().getBalance() + sum);

                    Date ordered = new Date();
                    Date shipped = ordered;
                    System.out.println("What is your shipping address?");
                    String address = scanner.nextLine();
                    Order ord = new Order(factory.getNextFreeId(), ordered, shipped, address, sum, currentLoggedInAccount);
                    objectsMap.put(ord.getNumber(), "Order");
                    factory.addObject(ord.getNumber(),ord);
                    currentLoggedInAccount.addOrder(ord);
                    System.out.println("Do you have something to add?");
                    String details = scanner.nextLine();
                    System.out.println("Do you want to pay immediate or delayed");
                    String pay = scanner.nextLine();
                    System.out.println("How many payments do you want?");
                    String howManyPay = scanner.nextLine();

                    if (pay.equals("immediate")) {
                        System.out.println("Do you have a phone confirmation (Y/N)?");
                        String con = scanner.nextLine();
                        boolean ans = false;
                        if (con.equals("Y")) {
                            ans = true;
                        }
                        for(int i = 0; i< Integer.parseInt(howManyPay);i++) {
                            ImmediatePayment impay = new ImmediatePayment(factory.getNextFreeId(), ordered, (float) sum/Integer.parseInt(howManyPay), details, ord, currentLoggedInAccount, ans);
                            objectsMap.put(impay.getId(), "Immediate Payment");
                            factory.addObject(impay.getId(),impay);
                        }
                    } else {
                        System.out.println("When do you want to pay?(DD/MM/YYYY)");
                        String Dat = scanner.nextLine();
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(Dat);
                        for(int i = 0; i< Integer.parseInt(howManyPay);i++) {
                            DelayedPayment depay = new DelayedPayment(factory.getNextFreeId(), ordered, (float) sum /Integer.parseInt(howManyPay), details, ord, currentLoggedInAccount, date);
                            objectsMap.put(depay.getId(), "Delayed Payment");
                            factory.addObject(depay.getId(),depay);
                        }
                    }
                    ord.setLineItems(lineItemList);
                    for (LineItem lineItem : lineItemList) {
                        lineItem.setOrder(ord);
                        lineItem.setShoppingCart(currentLoggedInAccount.getShoppingCart());
                        //lineItem.getProduct().addLineItem(lineItem);
                        objectsMap.put(lineItem.getId(),"Line Item");
                    }
                    System.out.println("Order added successfully");
                }
            }
            else {
                System.out.println("You can't purchase from non premium account");
            }
        }
    }

    public static void Displayorder(){
        if(!currentLoggedIn.equals("")) {
            List<Order> ord = currentLoggedInAccount.getOrders();
            /* If the user does not have any orders -> inform him */
            int size = currentLoggedInAccount.getOrderSize();
            if (ord.isEmpty())
                System.out.println("There are no orders");
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

    public static void LinkProduct(String product_name) throws InvalidArgumentException {
        Product pro = null;
        Scanner scanner = new Scanner(System.in);
        //check if the product exist in the system
        pro = SearchForName(product_name);
        if (pro!=null) {
            //check if the product is link to another user
            if(pro.getPremiumAccount()!=null){
                System.out.println("The product already belongs to another user. Please try again");
                return;
            }
            //check if the account logged in is premium, if it is premium link the product to this account
            WebUser user = (WebUser)factory.getObjectType(currentLoggedIn);
            if ( currentLoggedInAccount != null && user.getCustomer().getAccount() instanceof PremiumAccount) {
                PremiumAccount Pre = (PremiumAccount)user.getCustomer().getAccount();
                System.out.println("How much from this product you want to add?");
                String quantity = scanner.nextLine();
                pro.setQuantity(Integer.parseInt(quantity));
                System.out.println("How much do you want to sell this product?");
                String price = scanner.nextLine();
                pro.setPrice(Integer.parseInt(price));
                Pre.addProduct(pro);
                System.out.println("Product added successfully");
            } else {
                System.out.println("You can't link product without be logged in and have Premium account");
            }
        }
        else{
            System.out.println("Product not in the system");
        }
    }

    public static void AddProduct() throws InvalidArgumentException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Product id");
        String product_id = scanner.nextLine();
        while (factory.getObjectType(product_id) != null){
            System.out.println("Product id already exists. Please try again.");
            product_id = scanner.nextLine();
        }
        System.out.println("Please enter Product name");
        String product_name = scanner.nextLine();
        System.out.println("Please enter Supplier id");
        String supplier_id = scanner.nextLine();
        while (factory.getObjectType(supplier_id) != null && !(factory.getObjectType(supplier_id) instanceof Supplier) || (supplier_id.equals(product_id))){
            System.out.println("Supplier id already exists. Please try again.");
            supplier_id = scanner.nextLine();
        }
        System.out.println("Please enter Supplier name");
        String supplier_name = scanner.nextLine();
        Supplier new_supplier = null;
        String supplier_Sysid = factory.IdMap.get(supplier_id);
        if(factory.objectMap.get(supplier_Sysid)!=null){
            new_supplier = (Supplier)factory.objectMap.get(supplier_Sysid);
        }
        else {
            new_supplier = new Supplier(supplier_id,supplier_name);
            factory.addObject(supplier_id,new_supplier);
            objectsMap.put(supplier_id,"Supplier");
        }
        Product new_product = new Product(product_id,product_name,new_supplier);
        factory.addObject(product_id,new_product);
        objectsMap.put(product_id,"Product");
        System.out.println("Product added successfully");
    }

    public static void DeleteProduct(String Product_name){
        //Check if the product is in the system, if it is delete the product
        Product to_Delete = SearchForName(Product_name);
        if (to_Delete == null){
            System.out.println("Product with that name does not exists. Please try again.");
            return;
        }
        //to_Delete.Delete();
        factory.removeObject(to_Delete.getId());
        objectsMap.remove(to_Delete.getId());
        System.out.println("Product was deleted successfully");

    }

    public static void ShowAllObjects(){
        for (Map.Entry object : objectsMap.entrySet()) {
            System.out.println("The id is: "+ factory.getObjecSystemtId(object.getKey()) + " ,the Value is: " + object.getValue());
        }

    }

    /**
     * The function will get an object id and will print all of its details.
     *
     * @param id - String | Representing and id of an existing object in the system
     */
    public static void ShowObjectId(String id){
        if (!factory.isSystemIdExists(id)){
            System.out.println("The system id you have given does not belong to any object in the system");
            System.out.println("Please try again");
            return;
        }
        Object obj = factory.getObjectType(id);
        System.out.println("System ID: " + id);
        if(factory.getObjectType(id).getClass()==Order.class){
            System.out.println(factory.getObjectType(id));
            ((Order)factory.getObjectType(id)).PrintTheConnection();
        }
        else
            System.out.println(factory.getObjectType(id));


    }

    public static Product SearchForName (String name) {
        for (Map.Entry object : factory.objectMap.entrySet()) {
            if (factory.getObjectType((String) object.getKey()) instanceof Product) {
                if (((Product) object.getValue()).getName().equals(name)) {
                    return ((Product) object.getValue());
                }
            }
        }
        return null;
    }




}
