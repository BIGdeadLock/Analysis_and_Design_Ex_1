public class CoolingState extends Operation{

    public CoolingState(AirConditioner airCondition) {
        super(airCondition);
        this.entry();
    }


    @Override
    public void entry() {
        System.out.println("OPERATION-COOLING");
    }

    @Override
    public void changeCTemp() {
        if (this.airConditioner.getR_Temperature() <= this.airConditioner.getC_Temperature() - 2) {
            this.airConditioner.setOperationState(new FanningState(this.airConditioner));
            this.airConditioner.getOperationState().changeCTemp();
        }

    }

    @Override
    public void changeRTemp() {
        if (this.airConditioner.getR_Temperature() <= this.airConditioner.getC_Temperature() - 2) {
            this.airConditioner.setOperationState(new FanningState(this.airConditioner));
            this.airConditioner.getOperationState().changeRTemp();

        }
    }
}
