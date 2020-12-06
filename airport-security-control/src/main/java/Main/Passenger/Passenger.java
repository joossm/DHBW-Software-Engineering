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
        for (var x = 0; x < getBaggage().length; x++) {
            var cur = getBaggage()[x];
            if (cur != null) {
                setBaggage(x);
                return cur;
            }
        }
        return null;
    }

    public void addBaggage(Baggage baggage) {
        for (var x = 0; x < this.baggage.length; x++) {
            if (this.baggage[x] == null) {
                this.baggage[x] = baggage;
                return;
            }
        }
        throw new ArrayStoreException();
    }
}
