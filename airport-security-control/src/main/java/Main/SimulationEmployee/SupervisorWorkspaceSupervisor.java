package Main.SimulationEmployee;

import Main.Employee.IDCard;
import Main.Employee.Supervisor;
import Main.Employee.Pin;
import Main.Counter.SupervisorCounter;

import java.time.LocalDate;

public class SupervisorWorkspaceSupervisor extends Supervisor {

    private SupervisorCounter workspace;

    public SupervisorWorkspaceSupervisor(int id, String name, LocalDate birthDate, IDCard idCard, boolean isSenior, boolean isExecutive, SupervisorCounter workspace) {
        super(id, name, birthDate, idCard, isSenior, isExecutive);

        this.workspace = workspace;
    }

    public boolean unlockBaggageScanner(Pin enteredPin) throws Exception {
        return getWorkspace().unlockBaggageScanner(this, getIDCard(), enteredPin);
    }
    public boolean pressButtonStartBaggageScanner() throws Exception {
        return getWorkspace().onButtonStartBaggageScannerClicked(this);
    }
    public boolean pressButtonShutdownBaggageScanner() throws Exception {
        return getWorkspace().onButtonShutdownBaggageScannerClicked(this);
    }


    public SupervisorCounter getWorkspace() {
        return workspace;
    }
}
