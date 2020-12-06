package Main.Components.Scanner.Recorder;

public class Record {
    private final int id;
    private final String result;

    protected static int uniqueId = 0;

    public Record(String timestamp, String result) {
        this.id = uniqueId++;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public String getResult() {
        return result;
    }
}
