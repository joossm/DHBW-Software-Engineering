package Main.Counter;

import Main.Components.BaggageScanner.BS;
import Main.Employee.Employee;
import Main.Employee.IDCard;
import Main.Employee.Pin;

public class SupervisorCounter extends Counter {


    public SupervisorCounter(BS BS) {
        super(BS);
    }

    public boolean unlockBaggageScanner(Employee employee, IDCard idCard, Pin pin) throws Exception {
        return getBaggageScanner().unlock(employee, pin);
    }

    public boolean onButtonStartBaggageScannerClicked(Employee e) throws Exception {
        return getBaggageScanner().start(e);
    }

}
