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
        for(var i = 0; i < baggages.length; i++) {
            if(baggages[i] == null) {
                baggages[i] = baggage;
                return;
            }
        }
        throw new ArrayStoreException();
    }
}
