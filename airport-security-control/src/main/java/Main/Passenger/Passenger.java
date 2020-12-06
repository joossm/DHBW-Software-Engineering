package Main.Passenger;

public class Passenger {


    private Baggage[] baggage;


    private final String name;

    public Passenger(String name, Baggage[] baggage) {
        this.name = name;
        if (baggage.length > 3) {
            throw new IllegalArgumentException("Maximum length of array is 3");
        }
        setBaggage(baggage);
    }

    public String getName() {
        return name;
    }

    public Baggage[] getBaggage() {
        return baggage;
    }

    private void setBaggage(Baggage[] baggage) {
        this.baggage = baggage;
    }

    private void setBaggage(int i) {
        this.baggage[i] = null;
    }

    public Baggage getNextBaggage() {
        for (var i = 0; i < getBaggage().length; i++) {
            var cur = getBaggage()[i];
            if (cur != null) {
                setBaggage(i);
                return cur;
            }
        }
        return null;
    }

    public void addBaggage(Baggage baggage) {
        for (var i = 0; i < this.baggage.length; i++) {
            if (this.baggage[i] == null) {
                this.baggage[i] = baggage;
                return;
            }
        }
        throw new ArrayStoreException();
    }
}
