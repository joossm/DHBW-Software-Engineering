package Main.Workspaces;

import Main.Components.BaggageScanner.BaggageScanner;
import Main.Employee.Employee;
import Main.Employee.IDCard;
import Main.Employee.Pin;
import Main.Components.Reader;
import Main.SimulationEmployee.OperationStationInspector;

public class OperationStation extends Workspace {

    public OperationStation(BaggageScanner baggageScanner) {
        super(baggageScanner);
    }

    public boolean activateBaggageScanner(Employee e, Pin pin) {
        return getBaggageScanner().activate(e,pin);
    }

    public void forwardTrays(Employee e) {
        getBaggageScanner().forwardTrays(e);
    }

    public boolean scan(Employee e) {
        return getBaggageScanner().scan(e);
    }

    public boolean backwardTrays(OperationStationInspector e) {
        return getBaggageScanner().backwardTrays(e);
    }

    public boolean activateAlarm(OperationStationInspector e) {
        return getBaggageScanner().alarm(e);
    }
}
