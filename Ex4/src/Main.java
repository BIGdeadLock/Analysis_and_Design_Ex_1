import sun.rmi.runtime.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Main {

    static HashMap<String, Child> childrenMap;
    static HashMap<String, Device> devicesMap;
    static HashMap<String,String> functions_call;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ParkMap parkMap = new ParkMap();
        ParkSystem parkSystem = new ParkSystem(parkMap);

        functions_call = new HashMap();
        functions_call.put("Register child", "");
        functions_call.put("Manage Ticket", "*");
        functions_call.put("Add ride", "*");
        functions_call.put("Remove ride", "*");
        functions_call.put("Exit park", "");
        functions_call.put("Exit", "");

        do {
            System.out.println("1. Register child");
            System.out.println("2. Manage Ticket *child_name*");
            System.out.println("3. Exit park");
            System.out.println("4. Exit");
            String userChoice = scanner.nextLine();
            String[] userChoice_split = userChoice.split(" ");
            String argument = "", function_call = "";

            for (String function : functions_call.keySet()) {
                if (userChoice.contains(function)) {
                    //if (functions_call.get(function).equals("*"))
                    argument = userChoice_split[userChoice_split.length - 1];


                    function_call = function;
                    break;
                }
            }

            switch (function_call) {
                case "Register child":
                    /*try {
                        if (argument.equals("WebUser")){
                            System.out.println("No login id detected. Please try again with a login id");
                            break;
                        }
                        AddWebUser(argument);
                    } catch (UnknownError e) {
                        e.printStackTrace();
                    } catch (NullPointerException n){
                        n.printStackTrace();
                    }*/
                    break;

                case "Manage Ticket":
                    int flag = 0;
                    ETicket et = null;
                    for (Map.Entry name : childrenMap.entrySet()) {
                        if (argument.equals(name.getKey())){
                            et = ((Child)(name.getValue())).geteBracelet().geteTicket();
                            flag = 1;
                            //System.out.println("the " + name.toLowerCase() + " device was removed successfully");
                        }
                    }
                    if (flag == 0){
                        System.out.println("the child" + argument + " does not exists");
                    }
                    else{
                        System.out.println("please choose an option:");
                        System.out.println("1. Add ride *rideName*");
                        System.out.println("2. Remove ride *rideName*");
                        String gaurdChoice = scanner.nextLine();
                        String[] gaurdChoice_split = gaurdChoice.split(" ");
                        String newArg = "", new_function_call = "";

                        for (String function : functions_call.keySet()) {
                            if (gaurdChoice.contains(function)) {
                                newArg = userChoice_split[gaurdChoice_split.length - 1];
                                new_function_call = function;
                                break;
                            }
                        }
                        switch(new_function_call){
                            case "Add ride":
                                for (Map.Entry name : devicesMap.entrySet()) {
                                    if (argument.equals(name.getKey())){
                                        et.addRide((Device)(name.getValue()));
                                        flag = 1;
                                        System.out.println("the " + name + " device was added successfully");
                                    }
                                }
                                if (flag == 0){
                                    System.out.println("the" + argument + "device does not exists");
                                }
                                break;
                            case "Remove ride":
                                for (Map.Entry name : devicesMap.entrySet()) {
                                    if (argument.equals(name.getKey())){
                                        et.addRide((Device)(name.getValue()));
                                        flag = 1;
                                        System.out.println("the " + name + " device was removed successfully");
                                    }
                                }
                                if (flag == 0){
                                    System.out.println("the" + argument + "device does not exists");
                                }
                                break;
                            default:
                                System.out.println("Command Not Found. Please Try Again");
                                break;
                        }

                    }
                    /*if (argument.equals("WebUser")){
                        System.out.println("No login id detected. Please try again with a login id");
                        break;
                    }
                    RemoveWebUser(argument);*/
                    break;

                case "Exit park":
                    System.out.println("please state your exiting child's id");
                    String childID = scanner.nextLine();
                    System.out.println("please state your exiting child's password");
                    String childPASS = scanner.nextLine();
                    if(parkSystem.validateGuardian(childID,childPASS)){
                        //parkSystem.get
                        Child child = guardian.getChildByID(childID);
                        parkSystem.ExitPark(guardian, child);
                    }
                    else
                        System.out.println("wrong input, please try again");
                    break;

                case "Exit":
                    /*
                    if (argument.equals("WebUser")){
                        System.out.println("No login id detected. Please try again with a login id");
                        break;
                    }
                    LogoutWebUser(argument);*/
                    break;

                case "exit":
                    /*
                    if (argument.equals("WebUser")){
                        System.out.println("No login id detected. Please try again with a login id");
                        break;
                    }
                    LogoutWebUser(argument);*/
                    break;


                case "EXIT":
                    /*
                    if (argument.equals("WebUser")){
                        System.out.println("No login id detected. Please try again with a login id");
                        break;
                    }
                    LogoutWebUser(argument);*/
                    break;

                default:
                    System.out.println("Command Not Found. Please Try Again");
                    break;
            }

        } while (true);
    }
}
