public class Mode implements State{
    protected AirConditioner airConditioner;

    public Mode(AirConditioner airCondition) {
        airConditioner = airCondition;
    }

    @Override
    public void entry() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void on() {
        System.out.println("Air conditioner is already on");
    }

    @Override
    public void off() {
        this.airConditioner.setSystemState(new OffState(this.airConditioner));
    }

    @Override
    public void changeCTemp() {

    }

    @Override
    public void changeRTemp() {

    }

    @Override
    public void powerOutAge() {
        this.airConditioner.setSystemState(new OffState(this.airConditioner));
    }
}
