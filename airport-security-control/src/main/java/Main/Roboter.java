package Main;

import Main.Passanger.DestroyedHandBaggage;
import Main.Passanger.HandBaggage;

public class Roboter
{
    public DestroyedHandBaggage destroy(HandBaggage handBaggage)
    {
        char[][] destroyed = new char[1000][50];
        var destroyedcounter = 0;
        for (var  = 0; i < handBaggage.getLayers().length; i++)
        {
            var currentLayer = handBaggage.getLayers()[i];
            for(var j = 0; j < currentLayer.getContent().length - 50; j = j + 50)
            {
                destroyed[destroyedcounter] = String.valueOf( currentLayer.getContent(), j, 50).toCharArray();
                destroyedcounter++;
            }
        }
        return new DestroyedHandBaggage(destroyed);
    }
}
