package Main.Passanger;

public class DestroyedHandBaggage {

    private char[][] content;

    public DestroyedHandBaggage(char[][] destroyed) {
        if(destroyed.length != 1000 && destroyed[0].length != 50)
            throw new IllegalArgumentException();
        content = destroyed;
    }

    public char[][] getContent() {
        return content;
    }
}
