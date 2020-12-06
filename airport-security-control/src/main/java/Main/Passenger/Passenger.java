package Main.Passenger;

public class Passenger {


    private Baggage[] baggages;



    private final String name;

    public Passenger(String name, Baggage[] baggages)
    {
        this.name = name;
        if(baggages.length > 3)
        {
            throw new IllegalArgumentException("Maximum length of array is 3");
        }
        setBaggages(baggages);
    }

    public String getName() {
        return name;
    }

    public Baggage[] getBaggages()
    {
        return baggages;
    }

    private void setBaggages(Baggage[] baggages)
    {
        this.baggages = baggages;
    }
    private void setHandbaggage(int i)
    {
        this.baggages[i] = null;
    }

    public Baggage getNextBaggage()
    {
        for(var i = 0; i < getBaggages().length; i++)
        {
            var cur = getBaggages()[i];
            if(cur != null)
            {
                setHandbaggage(i);
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
