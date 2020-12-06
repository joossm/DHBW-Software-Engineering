package Main.Employee;

import Main.Components.Reader;
import Main.Counter.Counter;

import java.time.LocalDate;

public abstract class Employee {
    private final int id;
    private final String name;
    private final LocalDate birthdate;
    private final IDCard idCard;


    public Employee(int id, String name, LocalDate birthdate, IDCard idCard) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.idCard = idCard;
    }

    public IDCard getIDCard()
    {
        return idCard;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public LocalDate getBirthdate()
    {
        return birthdate;
    }

    public abstract Counter getWorkspace();

    public boolean auth(Reader reader, Pin enteredPin, EmployeeProfileType type)
    {
        var resolution = reader.checkCard(idCard, enteredPin, type);
        if (resolution) {
            idCard.resetWrongPinCounter();
        }

        else
        {
            idCard.wrongPinEntered();
        }
        return resolution;
    }

    public boolean auth(Reader reader, EmployeeProfileType type)
    {
        var resolution = reader.checkCard(idCard, type);
        if (resolution) {
            idCard.resetWrongPinCounter();
        }

        else
        {
            idCard.wrongPinEntered();
        }
        return resolution;
    }
}
