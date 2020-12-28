import sun.rmi.runtime.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Main {

    static HashMap<String, Child> childrenMap = new HashMap<String, Child>();
    static HashMap<String, Device> devicesMap = new HashMap<String, Device>();
    static HashMap<String,String> functions_call;
    static List<Object>  systemObjects = new ArrayList<Object>();

    public static void main(String[] args) throws ParseException {

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


        functions_call = new HashMap<>();
        functions_call.put("register", "");
        functions_call.put("manageticket", "*");
        functions_call.put("add", "*");
        functions_call.put("remove", "*");
        functions_call.put("exitpark", "");
        functions_call.put("exit", "");

        do {
            System.out.println("1. Register child");
            System.out.println("2. Manage Ticket *child_name*");
            System.out.println("3. Exit park");
            System.out.println("4. Exit");
            String userChoice = scanner.nextLine();
            String[] userChoice_split = userChoice.split(" ");
            String argument = "", function_call = "";

            for (String function : functions_call.keySet()) {
                if (userChoice.contains(function.toLowerCase())) {
                    //if (functions_call.get(function).equals("*"))
                    argument = userChoice_split[userChoice_split.length - 1];


                    function_call = function;
                    break;
                }
            }
            Guardian guardian1 = null;
            switch (function_call) {
                case "register":
                    guardian1 = new Guardian(parkSystem);
                    systemObjects.add(guardian1);
                    String ChildID;
                    String ChildAge;
                    do {
                        System.out.println("Please enter child ID");
                        ChildID = scanner.nextLine();
                        System.out.println("Please enter child age");
                        ChildAge = scanner.nextLine();
                    }
                    while (!parkSystem.checkIfDetailsValid(ChildID,ChildAge));
                    guardian1.CreateChild(ChildID,Integer.parseInt(ChildAge));
                    Child child1 = guardian1.childID_Child.get(ChildID);
                    systemObjects.add(child1);
                    systemObjects.add(parkSystem.CreateETicket(guardian1.childID_Child.get(ChildID)));
                    System.out.println("Please enter Credit Card Number");
                    String CardNumber = scanner.nextLine();
                    System.out.println("Please enter Credit Card Expiration Date in format DD/MM/YYYY");
                    String CardDate = scanner.nextLine();
                    System.out.println("Please enter Credit Card cvv");
                    String CardCVV = scanner.nextLine();
                    System.out.println("Please enter Maximum Amount");
                    String maxAmount = scanner.nextLine();
                    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(CardDate);
                    CreditCard card = new CreditCard(CardNumber, date1,CardCVV);
                    if (!parkSystem.companyApproval(card))
                        break;
                    systemObjects.add(parkSystem.CreateAccount(guardian1,Double.parseDouble(maxAmount)));
                    parkSystem.AddChildUser(child1);
                    systemObjects.add(parkSystem.CreateEBracelet(child1));
                    double childHeight = calculateHeight();
                    double childWeight = calculateWeight();
                    guardian1.UpdateHeightAndWeight(child1.ID,childHeight,childWeight);
                    break;

                case "manageticket":
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
                                    case "add":
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
                                    case "remove":
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

                case "exitpark":
                    System.out.println("please state your exiting child's id");
                    String childID = scanner.nextLine();
                    System.out.println("please state your exiting child's password");
                    String childPASS = scanner.nextLine();
                    if(parkSystem.validateGuardian(childID,childPASS)){
                        //parkSystem.get
                        if (guardian1 == null){
                            System.out.println("Need to register before");
                            break;
                        }
                        Child child = guardian1.getChildByID(childID);
                        parkSystem.ExitPark(guardian1, child);
                    }
                    else
                        System.out.println("wrong input, please try again");
                    break;


                case "exit":
                    break;

                default:
                    System.out.println("Command Not Found. Please Try Again");
                    break;
            }

        } while (true);
    }
    public static double calculateHeight(){
        Random rand = new Random();
        double Height= 0.5+rand.nextDouble()*(2.0-0.5);
        return Height;
    }
    public static double calculateWeight(){
        Random rand = new Random();
        double Weight= 10+rand.nextDouble()*(100-10);
        return Weight;
    }
}
