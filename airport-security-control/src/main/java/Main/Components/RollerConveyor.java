package Main.Components;

import Main.Components.BaggageScanner.BS;
import Main.Workspaces.Workspace;

import java.util.LinkedList;
import java.util.Queue;

public class RollerConveyor extends Workspace {
    Queue<Tray> trays;


    public RollerConveyor(BS BS) {
        super(BS);
        trays = new LinkedList<Tray>();
    }

    public boolean isEmpty() {
        return trays.isEmpty();
    }

    public void push() {
        getBaggageScanner().setIncomingTray(trays.poll());
    }

    public void addTray(Tray tray) {
        trays.add(tray);
    }

}
