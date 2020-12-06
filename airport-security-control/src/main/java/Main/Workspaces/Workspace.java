package Main.Workspaces;

import Main.Components.BaggageScanner.BS;

public abstract class Workspace {
    public Workspace(BS BS) {
        this.BS = BS;
    }

    public BS getBaggageScanner() {
        return BS;
    }

    private final BS BS;
}
