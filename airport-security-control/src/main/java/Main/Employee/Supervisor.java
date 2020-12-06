package Main.Employee;
import java.time.LocalDate;

public abstract class Supervisor extends Employee
{
    private final boolean isExecutive;
    private final boolean isSenior;

    public Supervisor(int id, String name, LocalDate birthDate, IDCard idCard, boolean isSenior, boolean isExecutive)
    {
        super(id, name, birthDate, idCard);
        this.isSenior = isSenior;
        this.isExecutive = isExecutive;
    }

    public boolean isSenior()
    {
        return isSenior;
    }

    public boolean isExecutive()
    {
        return isExecutive;
    }
}
