package Main.Employee;

import Main.Components.BaggageScanner.BS;
import Main.Counter.Counter;

import java.time.LocalDate;

public class Technician extends Employee {

    public Technician(int id, String name, LocalDate birthDate, IDCard idCard) {

        super(id, name, birthDate, idCard);
    }

    public void maintenance(BS scanner) throws Exception {
        scanner.maintenance(this);
    }

    @Override
    public Counter getWorkspace() {
        return null;
    }

}
