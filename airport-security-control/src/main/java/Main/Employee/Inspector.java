package Main.Employee;

import Main.Components.Reader;

import java.time.LocalDate;

public abstract class Inspector extends Employee {
    private final boolean isSenior;

    public Inspector(int id, String name, LocalDate birthDate, IDCard idCard, boolean isSenior) {
        super(id, name, birthDate, idCard);
        this.isSenior = isSenior;
    }
    public boolean isSenior() {
        return isSenior;
    }

}
