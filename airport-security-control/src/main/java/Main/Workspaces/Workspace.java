package Main.Workspaces;

import Main.Components.BaggageScanner.BaggageScanner;

public abstract class Workspace {
    public Workspace(BaggageScanner baggageScanner) {
        this.baggageScanner = baggageScanner;
    }

    public BaggageScanner getBaggageScanner() {
        return baggageScanner;
    }

    private BaggageScanner baggageScanner;
}
