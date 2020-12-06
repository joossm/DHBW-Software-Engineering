package Main.Employee;

import Main.Workspaces.Workspace;
import Main.Components.BaggageScanner.BS;
import java.time.LocalDate;

public class Technician extends Employee
{

    public Technician(int id, String name, LocalDate birthDate, IDCard idCard)
    {

        super(id, name, birthDate, idCard);
    }

    public void maintenance(BS scanner)
    {
        scanner.maintenance(this);
    }

    @Override
    public Workspace getWorkspace()
    {
        return null;
    }

}
