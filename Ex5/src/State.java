public interface State {

    public void entry();
    public void exit();
    public void on();
    public void off();
    public void changeCTemp();
    public void changeRTemp();
    public void powerOutAge();
}
