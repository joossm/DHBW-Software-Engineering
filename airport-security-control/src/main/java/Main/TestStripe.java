package Main;

public class TestStripe {
    char[][] data;

    public TestStripe(char[][] data) {
        if(data.length != 30 && data[0].length != 10 )
            throw new IllegalArgumentException();
        this.data = data;
    }

    public char[][] getData() {
        return data;
    }
}
