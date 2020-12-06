package Main.Counter;

import Main.Components.BaggageScanner.BS;
import Main.Employee.Employee;
import Main.Employee.Pin;
import Main.SimulationEmployee.OperationStationInspector;

public class OperationStation extends Counter {

    public OperationStation(BS BS) {
        super(BS);
    }

    public boolean activateBaggageScanner(Employee e, Pin pin) throws Exception {
        return getBaggageScanner().activate(e,pin);
    }

    public void forwardTrays(Employee e) throws Exception {
        getBaggageScanner().forwardTrays(e);
    }

    public boolean scan(Employee e) throws Exception {
        return getBaggageScanner().scan(e);
    }

    public void backwardTrays(OperationStationInspector e) throws Exception {
        getBaggageScanner().backwardTrays(e);
    }

    public boolean activateAlarm(OperationStationInspector e) throws Exception {
        return getBaggageScanner().alarm(e);
    }
}
