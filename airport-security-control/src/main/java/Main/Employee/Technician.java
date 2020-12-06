package Main.Employee;

import Main.Components.BaggageScanner.BaggageScanner;
import Main.Components.Reader;
import Main.Workspaces.Workspace;

import java.time.LocalDate;

public class Technician extends Employee{

    public Technician(int id, String name, LocalDate birthDate, IDCard idCard) {

        super(id, name, birthDate, idCard);
    }

    //Leider wurde der Wartungsauftrag nicht spezifiziert
    public void maintenance(BaggageScanner scanner) {
        scanner.maintenance(this);
    }

    @Override
    public Workspace getWorkspace() {
        return null;
    }

}
