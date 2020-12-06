package Main.Employee;

import Main.Counter.Counter;

import java.time.LocalDate;

public class Technician extends Employee {

    public Technician(int id, String name, LocalDate birthDate, IDCard idCard) {

        super(id, name, birthDate, idCard);
    }

    @Override
    public Counter getCounter() {
        return null;
    }

}
