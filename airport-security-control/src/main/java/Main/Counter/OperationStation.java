package Main.Counter;

import Main.Components.BaggageScanner.BS;
import Main.Employee.Employee;
import Main.Employee.Pin;
import Main.SimulationEmployee.OperationStationInspector;

public class OperationStation extends Counter {

    public OperationStation(BS BS) {
        super(BS);
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

    public void backwardTrays(OperationStationInspector e) {
        getBaggageScanner().backwardTrays(e);
    }

    public boolean activateAlarm(OperationStationInspector e) {
        return getBaggageScanner().alarm(e);
    }
}
