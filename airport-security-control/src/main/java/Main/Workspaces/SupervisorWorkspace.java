package Main.Workspaces;

import Main.Components.BaggageScanner.BaggageScanner;
import Main.Employee.Employee;
import Main.Employee.IDCard;
import Main.Employee.Pin;

public class SupervisorWorkspace extends Workspace {


    public SupervisorWorkspace(BaggageScanner baggageScanner) {
        super(baggageScanner);
    }

    public boolean unlockBaggageScanner(Employee employee, IDCard idCard, Pin pin)
    {
        return getBaggageScanner().unlock(employee, pin);
    }

    public boolean onButtonStartBaggageScannerClicked(Employee e) {
        return getBaggageScanner().start(e);
    }
    public boolean onButtonShutdownBaggageScannerClicked(Employee e) {
        return getBaggageScanner().shutdown(e);
    }
}
