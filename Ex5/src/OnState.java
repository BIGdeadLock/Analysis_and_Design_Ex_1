public class OnState implements State{
    private AirConditioner airConditioner;

    public OnState(AirConditioner airCondition) {
        airConditioner = airCondition;
        this.entry();
    }

    @Override
    public void entry() {
        System.out.println("ON");
    }

    @Override
    public void exit() {

    }

    @Override
    public void on() {

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
