import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<String, String> usersMap;
    static String currentLoggedIn="";
    static Account currentLoggedInAccount=null;
    static HashMap<String, Account> usersAccountMap;

    public static void main(String[] args) throws InvalidArgumentException {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, ShoppingCart> shoppingCartHashMap;
        usersMap = new HashMap<>();
        usersAccountMap = new HashMap<>();

        System.out.println("1. Add WebUser");
        System.out.println("2. Remove WebUser");
        System.out.println("3. Login WebUser");
        System.out.println("4. LogOut WebUser");

        String userChoice = "";
        switch (Integer.parseInt(userChoice)) {

            case 1:
                try {
                    addUser(scanner);
                }
                catch(InvalidArgumentException e){
                    e.printStackTrace();
                }
                break;

            case 2:
                removeUser(scanner);
                break;

            case 3:
                loginUser(scanner);
                break;

            case 4:
                logOutUser(scanner);
                break;

        }
    }

    private static void logOutUser(Scanner scanner) {
    }

    private static void loginUser(Scanner scanner) {
    }

    private static void removeUser(Scanner scanner) {
    }

    public static void addUser(Scanner scanner)throws InvalidArgumentException {

        //create the user
        System.out.println("Enter login id");
        String userName = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        usersMap.put(userName, password);

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
        System.out.println("Please enter your billing address");
        String account_billing_address = scanner.nextLine();


        //set customer -> set webUser with customer, set account with customer -> set customer's account
        Customer new_customer = new Customer(customer_id,customer_address,customer_phone_number,customer_email);
        WebUser new_webUser = new WebUser(userName, password, UserState.New, new_customer);
        Account new_account = new Account(customer_id,account_billing_address,false,new Date(),
                null, 0,new_customer,new ShoppingCart(new Date(),new_webUser));

        new_customer.setAccount(new_account);
        new_customer.setWebUser(new_webUser);

        System.out.println("Do you have a premium account? (Y/N)");

        System.out.println("Do you have a shopping cart? (Y/N)");

    }


}
