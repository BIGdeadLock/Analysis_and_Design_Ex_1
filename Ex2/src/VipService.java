public class VipService extends Service{

    public VipService(String serviceName) {
        super(serviceName);
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for (VipService v: model.vipServiceServiceAllInstances()){
            if (v == null)
                return true;
            if(!v.checkConstraints())
                return false;
        }
        return true;
    }

}
