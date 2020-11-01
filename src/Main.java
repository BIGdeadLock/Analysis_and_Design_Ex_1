import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<String, String> usersMap;
    static String currentLoggedIn="";
    static HashMap<String, Account> usersAccountMap;

    public static void main(String[] args) {
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
                addUser(scanner);
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

    public static void addUser(Scanner scanner){

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
        System.out.println("Please enter your account id");
        String account_id = scanner.nextLine();
        System.out.println("Please enter your address");
        String customer_address = scanner.nextLine();
        System.out.println("Please enter your phone number");
        String customer_phone_number = scanner.nextLine();
        System.out.println("Please enter your email address");
        String customer_email = scanner.nextLine();

        Customer new_customer = new Customer(customer_id,customer_address,customer_phone_number,customer_email);

        System.out.println("Do you have a premium account? (Y/N)");

        System.out.println("Do you have a shopping cart? (Y/N)");

    }


}
