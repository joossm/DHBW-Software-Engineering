package Main.Components.Scanner;

public class ScanResult {
    private final int position;
    private final int layer;
    private final ProhibitedItem type;

    public ScanResult(int layer, int position, ProhibitedItem type) {
        this.position = position;
        this.type = type;
        this.layer = layer;
    }

    public int getLayer() { return layer; }

    public boolean isClean() {
        return getType() == ProhibitedItem.CLEAN;
    }

    public int getPosition() {
        return position;
    }

    public ProhibitedItem getType() {
        return type;
    }
}
