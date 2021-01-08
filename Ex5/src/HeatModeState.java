public class HeatModeState extends Mode{
    private AirConditioner airConditioner;

    public HeatModeState(AirConditioner airCondition) {
        super(airCondition);
        this.entry();
    }

    @Override
    public void entry() {
        System.out.println("MODE-HEAT");
    }

    @Override
    public void changeCTemp() {
        if (this.airConditioner.getC_Temperature() + 5 <= this.airConditioner.getR_Temperature())
            this.airConditioner.setSystemState(new CoolModeState(this.airConditioner));
    }

    @Override
    public void changeRTemp() {
        if (this.airConditioner.getC_Temperature() + 5 <= this.airConditioner.getR_Temperature())
            this.airConditioner.setModeState(new CoolModeState(this.airConditioner));
    }

}
