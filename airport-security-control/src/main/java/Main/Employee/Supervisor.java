package Main.Employee;

import Main.Components.Reader;

import java.time.LocalDate;
import java.util.Date;

public abstract class Supervisor extends Employee {
    private final boolean isSenior;
    private final boolean isExecutive;
    public Supervisor(int id, String name, LocalDate birthDate, IDCard idCard, boolean isSenior, boolean isExecutive) {
        super(id, name, birthDate, idCard);
        this.isSenior = isSenior;
        this.isExecutive = isExecutive;
    }
    public boolean isSenior() {
        return isSenior;
    }

    public boolean isExecutive() {
        return isExecutive;
    }
}
