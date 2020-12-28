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
    static List<Object>  systemObjects;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ParkMap parkMap = new ParkMap();
        systemObjects.add(parkMap);
        ParkSystem parkSystem = new ParkSystem(parkMap);
        systemObjects.add(parkSystem);

        Device MambaRide = new Device("Mamba Ride", 12.0, 1.4, null, 20.0, true);
        Device GiantWheel = new Device("Giant Wheel", null, null, null, 15.0, true);
        Device Carrousel = new Device("Carrousel", 8.0, null, null, 10.0, true);
        systemObjects.add(MambaRide);
        systemObjects.add(GiantWheel);
        systemObjects.add(Carrousel);
        devicesMap.put("Mamba Ride",MambaRide);
        devicesMap.put("Giant Wheel",GiantWheel);
        devicesMap.put("Carrousel",Carrousel);


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
                    Guardian guardian = new Guardian(parkSystem);
                    systemObjects.add(guardian);
                    String ChildID;
                    String ChildAge;
                    do {
                        System.out.println("Please enter child ID");
                        ChildID = scanner.nextLine();
                        System.out.println("Please enter child age");
                        ChildAge = scanner.nextLine();
                    }
                    while (!parkSystem.checkIfDetailsValid(ChildID,ChildAge));
                    guardian.CreateChild(ChildID,Integer.parseInt(ChildAge));
                    systemObjects.add(guardian.childID_Child.get(ChildID));
                    systemObjects.add(parkSystem.CreateETicket(guardian.childID_Child.get(ChildID)));
                    System.out.println("Please enter Credit Card Number");
                    String CardNumber = scanner.nextLine();
                    System.out.println("Please enter Credit Card Expiration Date");
                    String CardDate
                    break;

                case "Manage Ticket":
                    int flag = 0;
                    ETicket et = null;
                    for (Map.Entry name : childrenMap.entrySet()) {
                        if (argument.equals(name.getKey())){
                            Child chosenChild = (Child)(name.getValue());
                            et = chosenChild.geteBracelet().geteTicket();
                            flag = 1;
                            System.out.println(et);
                            ArrayList<Device> suitableForChild = new ArrayList<>();
                            for (Map.Entry device : devicesMap.entrySet()) {
                                Device temp = (Device)(device.getValue());
                                if (temp.getMinAge() <= chosenChild.getAge() && temp.getMinHeight() <= chosenChild.getHeight() && temp.getMinWeight() <= chosenChild.getWeight()){
                                    suitableForChild.add(temp);
                                }
                            }
                            if (suitableForChild.size() > 0){
                                System.out.println("These are the devices you child can get on:\n");
                                for(Device d: suitableForChild){
                                    System.out.println(d);
                                }
                            }
                            else{
                                System.out.println("There are not suitable devices for your child\n");
                            }
                            //System.out.println("the " + name.toLowerCase() + " device was removed successfully");
                        }
                    }
                    if (flag == 0){
                        System.out.println("the child" + argument + " does not exists");
                    }
                    else{
                        String input;
                        do{
                            System.out.println("Do you want to add or remove device?(Y/N)");
                            input = scanner.nextLine();
                            if(input.equals("Y")){
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
                                                Device devToadd = (Device)(name.getValue());

                                                flag = 1;
                                                if (devToadd instanceof ExtremeDevice){
                                                    System.out.println("This is an Extreme device , do you allow it?(Y/N)");
                                                    String allow = scanner.nextLine();
                                                    if(allow.equals("N")){
                                                        System.out.println("the " + name + " device will not be added\n");
                                                        break;
                                                    }
                                                }

                                                et.addRide(devToadd);
                                                System.out.println("the " + name + " device was added successfully\n");
                                            }
                                        }
                                        if (flag == 0){
                                            System.out.println("the" + argument + "device does not exists\n");
                                        }
                                        break;
                                    case "Remove ride":
                                        for (Map.Entry name : devicesMap.entrySet()) {
                                            if (argument.equals(name.getKey())){
                                                et.addRide((Device)(name.getValue()));
                                                flag = 1;
                                                System.out.println("the " + name + " device was removed successfully\n");
                                            }
                                        }
                                        if (flag == 0){
                                            System.out.println("the" + argument + "device does not exists\n");
                                        }
                                        break;
                                    default:
                                        System.out.println("Command Not Found. Please Try Again\n");
                                        break;
                                }
                            }
                        }while(!input.equals("N"));

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
