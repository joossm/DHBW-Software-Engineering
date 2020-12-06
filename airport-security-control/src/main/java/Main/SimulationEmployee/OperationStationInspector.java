package Main.SimulationEmployee;

import Main.Employee.IDCard;
import Main.Employee.Inspector;
import Main.Counter.OperationStation;
import Main.Employee.Pin;

import java.time.LocalDate;

public class OperationStationInspector extends Inspector {

    private final OperationStation operationStation;

    public OperationStationInspector(int id, String name, LocalDate birthDate, IDCard idCard, boolean isSenior, OperationStation operationStation) {
        super(id, name, birthDate, idCard, isSenior);
        this.operationStation = operationStation;
    }

    public boolean activateBaggageScanner(Pin pin) throws Exception {
        return operationStation.activateBaggageScanner(this, pin);
    }

    public void clickButtonLeft() throws Exception {
        operationStation.backwardTrays(this);
    }
    public boolean clickButtonRect() throws Exception {
        return operationStation.scan(this);
    }
    public void clickButtonRight() throws Exception {
        operationStation.forwardTrays(this);
    }

    @Override
    public OperationStation getCounter() {
        return operationStation;
    }

    public void contact() {
    }

    public boolean activateAlarm() throws Exception {
        return operationStation.activateAlarm(this);
    }
}
