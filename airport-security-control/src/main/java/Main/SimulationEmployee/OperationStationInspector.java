package Main.SimulationEmployee;

import Main.Employee.IDCard;
import Main.Employee.Inspector;
import Main.Workspaces.OperationStation;
import Main.Employee.Pin;

import java.time.LocalDate;

public class OperationStationInspector extends Inspector {

    private OperationStation operationStation;

    public OperationStationInspector(int id, String name, LocalDate birthDate, IDCard idCard, boolean isSenior, OperationStation operationStation) {
        super(id, name, birthDate, idCard, isSenior);

        this.operationStation = operationStation;
    }

    public boolean activateBaggageScanner(Pin pin)
    {
        return operationStation.activateBaggageScanner(this, pin);
    }

    public void clickButtonLeft()
    {
        operationStation.backwardTrays(this);
    }
    public boolean clickButtonRect()
    {
        return operationStation.scan(this);
    }
    public void clickButtonRight()
    {
        operationStation.forwardTrays(this);
    }

    @Override
    public OperationStation getWorkspace() {
        return operationStation;
    }


    public void contact(ManualPostControlInspector inspectorI3) {
        //TODO sinnlose Funktion
    }

    public boolean activateAlarm() {
        return operationStation.activateAlarm(this);
    }
}
