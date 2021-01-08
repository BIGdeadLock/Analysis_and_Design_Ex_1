public class HeatingState extends Operation{

    public HeatingState(AirConditioner airCondition) {
        super(airCondition);
        this.entry();
    }

    @Override
    public void entry() {
        System.out.println("OPERATION-HEATING");
    }

    @Override
    public void changeCTemp() {
        if (this.airConditioner.getR_Temperature() >= this.airConditioner.getC_Temperature() + 2) {
            this.airConditioner.setOperationState(new FanningState(this.airConditioner));
            this.airConditioner.getOperationState().changeCTemp();

        }

    }

    @Override
    public void changeRTemp() {
        if (this.airConditioner.getR_Temperature() >= this.airConditioner.getC_Temperature() + 2){
            this.airConditioner.setOperationState(new FanningState(this.airConditioner));
            this.airConditioner.getOperationState().changeRTemp();

        }
    }

}
