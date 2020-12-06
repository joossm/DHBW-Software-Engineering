package Main.Passenger;

public class Passenger {

    private Baggage[] baggages;
    private final String name;

    public Passenger(String name, Baggage[] baggage)
    {
        this.name = name;
        if(baggage.length > 3)
        {
            throw new IllegalArgumentException("Maximum length of array is 3");
        }
        setBaggage(baggage);
    }

    public String getName() {
        return name;
    }

    public Baggage[] getBaggage()
    {
        return baggages;
    }

    private void setBaggage(Baggage[] baggage)
    {
        this.baggages = baggage;
    }
    private void setBaggage(int i)
    {
        this.baggages[i] = null;
    }

    public Baggage getNextBaggage()
    {
        for(var i = 0; i < getBaggage().length; i++)
        {
            var cur = getBaggage()[i];
            if(cur != null)
            {
                setBaggage(i);
                return cur;
            }
        }
        return null;
    }
    public void addBaggage(Baggage baggage) {
        for(var x = 0; x < baggages.length; x++) {
            if(baggages[x] == null) {
                baggages[x] = baggage;
                return;
            }
        }
        throw new ArrayStoreException();
    }
}
