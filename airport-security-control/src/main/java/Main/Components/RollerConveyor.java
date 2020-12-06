package Main.Components;

import Main.Components.BaggageScanner.BaggageScanner;
import Main.Workspaces.Workspace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RollerConveyor extends Workspace
{
    Queue<Tray> trays;


    public RollerConveyor(BaggageScanner baggageScanner)
    {
        super(baggageScanner);
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
