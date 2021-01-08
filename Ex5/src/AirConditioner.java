public class AirConditioner {
    State operationState;
    State modeState;
    State systemState;
    int C_Temperature;
    int R_Temperature;

    public AirConditioner(){
        this.off();
    }

    public void setSystemState(State systemState){
        this.systemState = systemState;
    }

    public void setOperationState(State operationState) {
        this.operationState = operationState;
    }

    public void setModeState(State modeState) {
        this.modeState = modeState;
    }

    public void setC_temp(int temp){
        System.out.println("set c_temp to " + temp);
        C_Temperature = temp;
        modeState.changeCTemp();
        operationState.changeCTemp();
    }


    public void setR_temp(int temp){
        System.out.println("set r_temp to " + temp);
        R_Temperature = temp;
        modeState.changeCTemp();
        operationState.changeRTemp();
    }

    public void on(){
        C_Temperature = 25;
        R_Temperature = 25;
        this.systemState = new WaitState(this);
        this.operationState = new FanningState(this);
    }
    public void off(){
        systemState = new OffState(this);
    }


    public int getC_Temperature() {
        return C_Temperature;
    }

    public int getR_Temperature() {
        return R_Temperature;
    }

    public State getOperationState() {
        return operationState;
    }
}
