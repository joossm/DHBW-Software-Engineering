package Main.Passenger;

public class Baggage
{

    private Layer layers[];

    public Baggage(Layer layers[])
    {
        if(layers.length != 3)
        {
            throw new IllegalArgumentException();
        }
        setLayers(layers);
    }
    public Layer[] getLayers()
    {
        return layers;
    }

    private void setLayers(Layer[] layers)
    {
        this.layers = layers;
    }

    public void setLayer(int i, Layer layer) {
        layers[i] = layer;
    }


}
