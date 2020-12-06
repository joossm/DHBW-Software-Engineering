package Main.Components.Scanner;

public class Shape {

    private final SearchAlgorithm usedAlgorithm;

    public Shape(SearchAlgorithm usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }

    public SearchAlgorithm getUsedAlgorithm() {
        return usedAlgorithm;
    }

}
