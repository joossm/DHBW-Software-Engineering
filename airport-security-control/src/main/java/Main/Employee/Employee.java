package Main.Employee;

import Main.Components.Reader;
import Main.Counter.Counter;

import java.time.LocalDate;

public abstract class Employee {
    private final int id;
    private final String name;
    private final LocalDate birthDate;
    private final IDCard idCard;


    public Employee(int id, String name, LocalDate birthDate, IDCard idCard) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.idCard = idCard;
    }

    public IDCard getIDCard() {
        return idCard;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public abstract Counter getCounter();

    public boolean auth(Reader reader, Pin enteredPin, EmployeeProfileType type) throws Exception {
        var res = reader.checkCard(idCard, enteredPin, type);
        if (res) {
            idCard.resetWrongPinCounter();
        } else {
            idCard.wrongPinEntered();
        }
        return res;
    }

    public boolean auth(Reader reader, EmployeeProfileType type) throws Exception {
        var res = reader.checkCard(idCard, type);
        if (res) {
            idCard.resetWrongPinCounter();
        } else {
            idCard.wrongPinEntered();
        }
        return res;
    }
}
