package Main;

import Main.Passenger.DestroyedBaggage;
import Main.Passenger.Baggage;

public class Robot {
    public DestroyedBaggage destroy(Baggage baggage) {
        char[][] destroyed = new char[1000][50];
        var destruction = 0;
        for (int i = 0; i < baggage.getLayers().length; i++) {
            var currentLayer = baggage.getLayers()[i];
            for (var j = 0; j < currentLayer.getContent().length - 50; j = j + 50) {
                destroyed[destruction] = String.valueOf(currentLayer.getContent(), j, 50).toCharArray();
                destruction++;
            }
        }
        return new DestroyedBaggage(destroyed);
    }
}
