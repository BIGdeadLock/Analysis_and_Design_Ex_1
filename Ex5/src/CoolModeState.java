public class CoolModeState extends Mode {


    public CoolModeState(AirConditioner airCondition) {
        super(airCondition);
        this.entry();
    }

    @Override
    public void entry() {
        System.out.println("MODE-COOL");
    }

    @Override
    public void exit() {

    }

    @Override
    public void changeCTemp() {
        if (this.airConditioner.getC_Temperature() >= this.airConditioner.getR_Temperature() + 5)
            this.airConditioner.setModeState(new HeatModeState(this.airConditioner));
    }

    @Override
    public void changeRTemp() {
        if (this.airConditioner.getC_Temperature() >= this.airConditioner.getR_Temperature() + 5)
            this.airConditioner.setModeState(new HeatModeState(this.airConditioner));
    }

}
