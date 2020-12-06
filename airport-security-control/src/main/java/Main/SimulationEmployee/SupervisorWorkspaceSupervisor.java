package Main.SimulationEmployee;

import Main.Employee.IDCard;
import Main.Employee.Supervisor;
import Main.Employee.Pin;
import Main.Counter.SupervisorCounter;

import java.time.LocalDate;

public class SupervisorWorkspaceSupervisor extends Supervisor {

    private final SupervisorCounter counter;

    public SupervisorWorkspaceSupervisor(int id, String name, LocalDate birthDate, IDCard idCard, boolean isSenior, boolean isExecutive, SupervisorCounter counter) {
        super(id, name, birthDate, idCard, isSenior, isExecutive);

        this.counter = counter;
    }

    public boolean unlockBaggageScanner(Pin enteredPin) throws Exception {
        return getCounter().unlockBaggageScanner(this, getIDCard(), enteredPin);
    }
    public boolean pressButtonStartBaggageScanner() throws Exception {
        return getCounter().onButtonStartBaggageScannerClicked(this);
    }


    public SupervisorCounter getCounter() {
        return counter;
    }
}
