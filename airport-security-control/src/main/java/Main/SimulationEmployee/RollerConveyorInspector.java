package Main.SimulationEmployee;

import Main.Employee.IDCard;
import Main.Employee.Inspector;
import Main.Components.RollerConveyor;

import java.time.LocalDate;

public class RollerConveyorInspector extends Inspector {

    private RollerConveyor rollerConveyor;
    public RollerConveyorInspector(int id, String name, LocalDate birthDate, IDCard idCard, boolean isSenior, RollerConveyor rollerConveyor) {
        super(id, name, birthDate, idCard, isSenior);

        this.rollerConveyor = rollerConveyor;
    }

    @Override
    public RollerConveyor getWorkspace() {
        return rollerConveyor;
    }

    public void push() {
        rollerConveyor.push();
    }
}
