package Main.Components;

import Main.Workspaces.Workspace;
import Main.Components.BaggageScanner.BS;
import java.util.Queue;
import java.util.LinkedList;


public class RollerConveyor extends Workspace
{
    Queue<Tray> trays;

    public RollerConveyor(BS BS)
    {
        super(BS);
        trays = new LinkedList<Tray>();
    }

    public boolean isEmpty()
    {
        return trays.isEmpty();
    }

    public void push()
    {
        getBaggageScanner().setIncomingTray(trays.poll());
    }

    public void addTray(Tray tray)
    {
        trays.add(tray);
    }

}
