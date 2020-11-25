public class RegularService extends Service {


    public RegularService(String serviceName) {
        super(serviceName);
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for (RegularService v: model.regularServiceServiceAllInstances()){
            if (v == null)
                return true;
            if(!v.checkConstraints())
                return false;
        }

        return true;
    }
}
