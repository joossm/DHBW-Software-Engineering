package Main.Components;

import java.util.LinkedList;
import java.util.Queue;

public class Track {

    private final Queue<Tray> trays;

    public Track() {
        trays = new LinkedList<>() {
        };
    }

    public void addTray(Tray tray) {
        trays.add(tray);
    }

    public Tray removeTray() {
        return trays.poll();
    }
}
