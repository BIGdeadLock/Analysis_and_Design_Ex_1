public class CommunityService extends Service{

    public CommunityService(String serviceName) {
        super(serviceName);
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for (CommunityService cs: model.communityServiceServiceAllInstances()){
            if (cs == null)
                return true;
            if(!cs.checkConstraints())
                return false;
        }

        return true;
    }
}
