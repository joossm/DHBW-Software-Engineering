package Main.Components.Scanner;

public class Configuration {

    private final SearchAlgorithm usedAlgorithm;

    public Configuration(SearchAlgorithm usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }

    public SearchAlgorithm getUsedAlgorithm() {
        return usedAlgorithm;
    }

}
