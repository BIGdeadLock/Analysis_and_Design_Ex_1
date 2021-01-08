public class FanningState extends Operation {

    public FanningState(AirConditioner airCondition) {
        super(airCondition);
        this.entry();
    }

    @Override
    public void entry() {
        System.out.println("OPERATION-FANNING (120 SECONDS AT LEAST)");

    }

    @Override
    public void changeCTemp() {
        if (this.airConditioner.getR_Temperature() >= this.airConditioner.getC_Temperature() + 2)
            this.airConditioner.setOperationState(new CoolingState(this.airConditioner));
        else if (this.airConditioner.getR_Temperature() <= this.airConditioner.getC_Temperature() - 2)
            this.airConditioner.setOperationState(new HeatingState(this.airConditioner));
    }

    @Override
    public void changeRTemp() {
        if (this.airConditioner.getR_Temperature() >= this.airConditioner.getC_Temperature() + 2)
            this.airConditioner.setOperationState(new CoolingState(this.airConditioner));
        else if (this.airConditioner.getR_Temperature() <= this.airConditioner.getC_Temperature() - 2)
            this.airConditioner.setOperationState(new HeatingState(this.airConditioner));
    }

}
