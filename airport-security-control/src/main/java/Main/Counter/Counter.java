package Main.Counter;

import Main.Components.BaggageScanner.BS;

public abstract class Counter {
    public Counter(BS BS) {
        this.BS = BS;
    }

    public BS getBaggageScanner() {
        return BS;
    }

    private final BS BS;
}
