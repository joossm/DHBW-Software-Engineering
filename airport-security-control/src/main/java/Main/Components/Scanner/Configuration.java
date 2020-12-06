package Main.Components.Scanner;

public class Configuration {

    private SearchAlgorithm usedAlgorithm;

    public Configuration(SearchAlgorithm usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }

    public SearchAlgorithm getUsedAlgorithm() {
        return usedAlgorithm;
    }

    public void setUsedAlgorithm(SearchAlgorithm usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }
}
