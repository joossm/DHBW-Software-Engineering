package Main.Components;
import java.util.Queue;
import java.util.LinkedList;

public class Track
{

    private final Queue<Tray> trays;

    public Track()
    {
        trays = new LinkedList<>()
        {
        };
    }

    public void addTray(Tray tray)
    {
        trays.add(tray);
    }

    public Tray removeTray()
    {
        return trays.poll();
    }
}
