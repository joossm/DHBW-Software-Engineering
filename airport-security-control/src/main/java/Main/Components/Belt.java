package Main.Components;

public class Belt
{

    private Tray incoming;
    private Tray outgoing;
    private Tray current;


    public Tray getCurrent()
    {
        return current;
    }

    protected void setCurrent(Tray current)
    {
        this.current = current;
    }

    public void setIncomingTray(Tray tray)
    {
        if(incoming != null)
        {
            throw new RuntimeException();
        }

        incoming = tray;
    }

    public Tray removeIncomingTray() {
        var temp = incoming;
        incoming = null;
        return temp;
    }

    public Tray removeCurrentTray() {
        var temp = current;
        current = null;
        return temp;
    }

    public void setOutgoingTray(Tray tray)
    {
        if(outgoing != null)
        {
            throw new RuntimeException();
        }
        outgoing = tray;
    }

    public Tray removeOutgoingTray() {
        var temp = outgoing;
        outgoing = null;
        return temp;
    }

    public void forwardTrays() {
        setOutgoingTray(removeCurrentTray());
        setCurrent(removeIncomingTray());
    }

    public void backwardTrays() {
        setIncomingTray(removeCurrentTray());
        setCurrent(removeOutgoingTray());
    }

}
