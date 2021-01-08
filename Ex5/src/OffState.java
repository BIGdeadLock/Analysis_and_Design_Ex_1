public class OffState implements State{
    private AirConditioner airConditioner;
    public OffState(AirConditioner airCondition) {
        airConditioner = airCondition;
        this.entry();
    }

    @Override
    public void entry() {
        System.out.println("OFF");
    }

    @Override
    public void exit() {
        this.on();
    }

    @Override
    public void on() {
        airConditioner.setC_temp(25);
        airConditioner.setR_temp(25);
        airConditioner.setSystemState(new WaitState(this.airConditioner));
    }

    @Override
    public void off() {
        System.out.println("Air conditioner is off");
    }

    @Override
    public void changeCTemp() {
        System.out.println("Air conditioner is off");
    }

    @Override
    public void changeRTemp() {
        System.out.println("Air conditioner is off");
    }

    @Override
    public void powerOutAge() {
        System.out.println("Air conditioner is off");
    }
}
