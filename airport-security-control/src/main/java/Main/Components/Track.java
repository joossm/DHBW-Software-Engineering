package Main.Components;

import java.util.*;

public class Track {

    private final Queue<Tray> trays;

    public Track() {
        trays = new LinkedList<>() {
        };
    }
    public void addTray(Tray tray){
        trays.add(tray);
    }
    public Tray removeTray(){
        return trays.poll();
    }
}
