package Main.SimulationEmployee;

import Main.Employee.IDCard;
import Main.Employee.Supervisor;
import Main.Employee.Pin;
import Main.Workspaces.SupervisorWorkspace;

import java.time.LocalDate;

public class SupervisorWorkspaceSupervisor extends Supervisor {

    private SupervisorWorkspace workspace;

    public SupervisorWorkspaceSupervisor(int id, String name, LocalDate birthDate, IDCard idCard, boolean isSenior, boolean isExecutive, SupervisorWorkspace workspace) {
        super(id, name, birthDate, idCard, isSenior, isExecutive);

        this.workspace = workspace;
    }

    public boolean unlockBaggageScanner(Pin enteredPin) {
        return getWorkspace().unlockBaggageScanner(this, getIDCard(), enteredPin);
    }
    public boolean pressButtonStartBaggageScanner() {
        return getWorkspace().onButtonStartBaggageScannerClicked(this);
    }
    public boolean pressButtonShutdownBaggageScanner() {
        return getWorkspace().onButtonShutdownBaggageScannerClicked(this);
    }


    public SupervisorWorkspace getWorkspace() {
        return workspace;
    }
}
