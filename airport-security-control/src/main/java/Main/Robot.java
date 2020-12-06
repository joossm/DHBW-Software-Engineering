package Main;

import Main.Passenger.DestroyedBaggage;
import Main.Passenger.Baggage;

public class Robot {
    public DestroyedBaggage destroy(Baggage baggage) {
        char[][] destroyed = new char[1000][50];
        var destruction = 0;
        for (int x = 0; x < baggage.getLayers().length; x++) {
            var currentLayer = baggage.getLayers()[x];
            for (var j = 0; j < currentLayer.getContent().length - 50; j = j + 50) {
                destroyed[destruction] = String.valueOf(currentLayer.getContent(), j, 50).toCharArray();
                destruction++;
            }
        }
        return new DestroyedBaggage(destroyed);
    }
}
