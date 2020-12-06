package Main.Employee;

import Main.Components.Reader;
import Main.Workspaces.Workspace;

import java.time.LocalDate;

public abstract class Employee {
    private final int id;
    private final String name;
    private final LocalDate birthDate;
    private final IDCard idCard;



    public Employee(int id, String name, LocalDate birthDate, IDCard idCard)
    {
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

    public abstract Workspace getWorkspace();

    public boolean auth(Reader reader, Pin enteredPin, EmployeeProfileType type) {
        var res = reader.checkCard(idCard,enteredPin, type);
        if(res)  {
            idCard.resetWrongPinCounter();
        } else {
            idCard.wrongPinEntered();
        }
        return res;
    }
    public boolean auth(Reader reader, EmployeeProfileType type) {
        var res = reader.checkCard(idCard, type);
        if(res)  {
            idCard.resetWrongPinCounter();
        } else {
            idCard.wrongPinEntered();
        }
        return res;
    }
}
