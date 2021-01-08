public class WaitState implements State{
    private AirConditioner airConditioner;


    public WaitState(AirConditioner airCondition) {
        airConditioner = airCondition;
        this.entry();
    }

    @Override
    public void entry() {
        System.out.println("WAITING 30 SECONDS");
        this.exit();

    }

    @Override
    public void exit() {
        this.airConditioner.setSystemState(new OnState(this.airConditioner));

        if (airConditioner.getC_Temperature() > airConditioner.getR_Temperature())
            this.airConditioner.setModeState(new HeatModeState(this.airConditioner));

        else
            this.airConditioner.setModeState(new CoolModeState(this.airConditioner));

    }

    @Override
    public void on() {
        System.out.println("Air conditioner is already on");
    }

    @Override
    public void off() {
        System.out.println("Air conditioner is already on");
    }

    @Override
    public void changeCTemp() {

    }

    @Override
    public void changeRTemp() {

    }

    @Override
    public void powerOutAge() {

    }
}
