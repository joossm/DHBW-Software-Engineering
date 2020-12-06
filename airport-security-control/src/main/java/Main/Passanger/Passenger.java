package Main.Passanger;

public class Passenger {


    private HandBaggage[] handbaggages;



    private final String name;

    public Passenger(String name, HandBaggage[] handbaggages)
    {
        this.name = name;
        if(handbaggages.length > 3)
        {
            throw new IllegalArgumentException("Maximum length of array is 3");
        }
        setHandbaggages(handbaggages);
    }

    public String getName() {
        return name;
    }

    public HandBaggage[] getHandbaggages()
    {
        return handbaggages;
    }

    private void setHandbaggages(HandBaggage[] handbaggages)
    {
        this.handbaggages = handbaggages;
    }
    private void setHandbaggage(HandBaggage handbaggage, int i)
    {
        this.handbaggages[i] = handbaggage;
    }

    public HandBaggage getNextHandBaggage()
    {
        for(var i = 0; i < getHandbaggages().length; i++)
        {
            var cur = getHandbaggages()[i];
            if(cur != null)
            {
                setHandbaggage(null,i);
                return cur;
            }
        }
        return null;
    }
    public void addHandBaggage(HandBaggage baggage) {
        for(var i = 0; i < handbaggages.length; i++) {
            if(handbaggages[i] == null) {
                handbaggages[i] = baggage;
                return;
            }
        }
        throw new ArrayStoreException();
    }
}
