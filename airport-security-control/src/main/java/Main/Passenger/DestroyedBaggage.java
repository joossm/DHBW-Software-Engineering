package Main.Passenger;

public class DestroyedBaggage {

    private final char[][] content;

    public DestroyedBaggage(char[][] destroyed) {
        if (destroyed.length != 1000 && destroyed[0].length != 50)
            throw new IllegalArgumentException();
        content = destroyed;
    }

    public char[][] getContent() {
        return content;
    }
}
