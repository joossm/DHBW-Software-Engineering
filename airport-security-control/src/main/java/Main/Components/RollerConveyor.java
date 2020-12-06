package Main.Components;

import Main.Components.BaggageScanner.BS;
import Main.Counter.Counter;

import java.util.LinkedList;
import java.util.Queue;

public class RollerConveyor extends Counter {
    Queue<Tray> trays;


    public RollerConveyor(BS BS) {
        super(BS);
        trays = new LinkedList<>();
    }

    public void push() {
        getBaggageScanner().setIncomingTray(trays.poll());
    }

    public void addTray(Tray tray) {
        trays.add(tray);
    }

}
