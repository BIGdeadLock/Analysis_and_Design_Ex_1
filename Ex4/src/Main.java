import sun.rmi.runtime.Log;

import java.lang.management.GarbageCollectorMXBean;
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

        Guardian guardian1 = null;
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
            System.out.println("2. Manage Ticket");
            System.out.println("3. Exit park");
            System.out.println("4. Exit");
            String userChoice = scanner.nextLine();
            String[] userChoice_split = userChoice.split(" ");
            String argument = "", function_call = "";

            for (String function : functions_call.keySet()) {
                if (userChoice_split[0].toLowerCase().equals(function.toLowerCase())) {
                    //if (functions_call.get(function).equals("*"))
                    argument = userChoice_split[userChoice_split.length - 1];
                    //System.out.println(argument);

                    function_call = function;
                    //System.out.println(function_call);
                    break;
                }
            }

            switch (function_call) {
                case "register":
                    guardian1 = new Guardian(parkSystem);
                    systemObjects.add(guardian1);
                    parkSystem.addGuardians(guardian1);
                    String ChildID;
                    String ChildAge;
                    String ChildName;
                    do {
                        System.out.println("Please enter child ID");
                        ChildID = scanner.nextLine();
                        System.out.println("Please enter child age");
                        ChildAge = scanner.nextLine();
                        System.out.println("Please enter child name");
                        ChildName = scanner.nextLine();
                    }
                    while (!parkSystem.checkIfDetailsValid(ChildID,ChildAge));
                    guardian1.CreateChild(ChildID,Integer.parseInt(ChildAge),ChildName);
                    Child child1 = guardian1.childID_Child.get(ChildID);
                    systemObjects.add(child1);
                    childrenMap.put(ChildName,child1);
                    ETicket eTicket1=parkSystem.CreateETicket(guardian1.childID_Child.get(ChildID));
                    systemObjects.add(eTicket1);
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
                    if (!parkSystem.companyApproval(card)) {
                        System.out.println("CreditCard not approved");
                        String ID=child1.getID();
                        child1.Delete();
                        parkSystem.clearChlid(ID);
                        eTicket1.Delete();
                        guardian1.Delete();
                        break;
                    }
                    try{
                        Double.parseDouble(maxAmount);
                    }
                    catch (Exception e){
                        System.out.println("MaxAmount must be Number");
                        String ID=child1.getID();
                        child1.Delete();
                        parkSystem.clearChlid(ID);
                        eTicket1.Delete();
                        guardian1.Delete();
                        break;
                    }
                    systemObjects.add(parkSystem.CreateAccount(guardian1,Double.parseDouble(maxAmount)));
                    parkSystem.AddChildUser(child1);
                    systemObjects.add(parkSystem.CreateEBracelet(child1));
                    double childHeight = calculateHeight();
                    double childWeight = calculateWeight();
                    guardian1.UpdateHeightAndWeight(child1.ID,childHeight,childWeight);

                    int childSystemID = guardian1.getChildByID(ChildID).getSystemId();
                    int password = parkSystem.getChildPassword(childSystemID);

                    System.out.println("The following account was created for child: " + ChildName);
                    System.out.println("The child system id created: " + childSystemID);
                    System.out.println("The child password created: " + password);
                    System.out.println("\n");
                    break;

                case "manageticket":
                    //System.out.println(argument);
                    ArrayList<Device> suitableForChild = new ArrayList<>();
                    int flag = 0;
                    ETicket et = null;
                    for (Map.Entry name : childrenMap.entrySet()) {
                        if (argument.equals(name.getKey())){
                            Child chosenChild = (Child)(name.getValue());
                            //et = chosenChild.geteBracelet().geteTicket();
                            et = chosenChild.geteTicket();
                            flag = 1;
                            System.out.println(et);

                            for (Map.Entry device : devicesMap.entrySet()) {
                                Device temp = (Device)(device.getValue());
                                Boolean suitable = temp.suitableDevices(chosenChild);
                                if (suitable){
                                    suitableForChild.add(temp);
                                }

                            }
                        }
                    }
                    if (flag == 0){
                        System.out.println("the child " + argument + " does not exists");
                    }

                    else{
                        flag = 0;
                        String input;
                        do{
                            System.out.println("Do you want to add or remove device?(Y/N)");
                            input = scanner.nextLine();
                            if(input.equals("Y")){
                                if (suitableForChild.size() > 0){
                                    System.out.println("These are the devices you child can get on:");
                                    for(Device d: suitableForChild){
                                        System.out.println(d.getName());
                                    }
                                }
                                else{
                                    System.out.println("There are not suitable devices for your child");
                                }

                                System.out.println("\nplease choose an option:");
                                System.out.println("1. Add ride *rideName*");
                                System.out.println("2. Remove ride *rideName*");
                                String gaurdChoice = scanner.nextLine();
                                String[] gaurdChoice_split = gaurdChoice.split(" ");
                                String newArg = "", new_function_call = "";

                                for (String function : functions_call.keySet()) {
                                    if (gaurdChoice_split[0].toLowerCase().equals(function)) {
                                        int i = 1;
                                        while (i < gaurdChoice_split.length){
                                            newArg += gaurdChoice_split[i];
                                            if(i + 1 != gaurdChoice_split.length){
                                                newArg+= " " ;
                                            }
                                            i+= 1;
                                            //System.out.println(newArg);
                                        }
                                        //newArg = gaurdChoice_split[gaurdChoice_split.length - 1];
                                        new_function_call = function;
                                        break;
                                    }
                                }
                                switch(new_function_call){
                                    case "add":
                                        flag = 0;
                                        for (Map.Entry name : devicesMap.entrySet()) {
                                            if (newArg.toLowerCase().equals(((String)name.getKey()).toLowerCase())){
                                                Device devToadd = (Device)(name.getValue());

                                                flag = 1;
                                                if (devToadd.checkExtreme()){
                                                    System.out.println("This is an Extreme device , do you allow it?(Y/N)");
                                                    String allow = scanner.nextLine();
                                                    if(allow.equals("N")){
                                                        System.out.println("the " + name.getKey() + " device will not be added\n");
                                                        break;
                                                    }
                                                }
                                                parkSystem.addDeviceToTicket(guardian1,et,devToadd);
                                               // et.addRide(devToadd);

                                            }
                                        }
                                        if (flag == 0){
                                            System.out.println("the " + newArg + " device does not exists\n");
                                        }
                                        break;
                                    case "remove":
                                        flag = 0;
                                        for (Map.Entry name : devicesMap.entrySet()) {
                                            if (newArg.toLowerCase().equals(((String)name.getKey()).toLowerCase())){
                                                parkSystem.removeDeviceFromTicket(guardian1,et,(Device)name.getValue());
                                                //et.removeRide((String)name.getKey());
                                                flag = 1;
                                                //System.out.println("the " + name.getKey() + " device was removed successfully\n");
                                            }
                                        }
                                        if (flag == 0){
                                            System.out.println("the " + newArg + " device does not exists\n");
                                        }
                                        break;
                                    default:
                                        System.out.println("Command Not Found. Please Try Again\n");
                                        break;
                                }
                            }
                        }while(!input.equals("N"));

                    }
                    break;

                case "exitpark":
                    System.out.println("please state your exiting child's id");
                    String childID = scanner.nextLine();
                    System.out.println("please state your exiting child's given system password");
                    String childPASS = scanner.nextLine();
                    if(parkSystem.validateGuardian(childID,childPASS)){
                        if (guardian1 == null){
                            System.out.println("Need to register before");
                            break;
                        }
                        Child child = guardian1.getChildByID(childID);
                        parkSystem.ExitPark(guardian1, child);
                    }
                    else{
                        System.out.println("wrong input, please try again");
                        break;
                    }

                    System.out.println("Successfully exited the park");
                    break;


                case "exit":
                    systemObjects.clear();
                    System.out.println("Successfully exited the system. Goodbye!");
                    return;

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
