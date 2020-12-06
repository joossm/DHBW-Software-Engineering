package Main.Passenger;

public class DestroyedBaggage {

    public DestroyedBaggage(char[][] destroyed) {
        if (destroyed.length != 1000 && destroyed[0].length != 50)
            throw new IllegalArgumentException();
    }

}
