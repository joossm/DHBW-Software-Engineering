package Main.Components.Scanner.Recorder;

import Main.Components.Scanner.ProhibitedItem;
import Main.Components.Scanner.ScanResult;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScanRecorder {

    private final List<Record> records;

    public ScanRecorder()
    {
        records = new ArrayList<>();
    }

    private static String getTimeStamp()
    {

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        return ts.toString();
    }

    public void logResult(ScanResult result) {

        String resultString = "";

        if(result.getType() == ProhibitedItem.CLEAN || result.getPosition() == -1)
        {
            resultString = "clean";
        } else {
            resultString = String.format("%s detected at position #%d",result.getType().toString(),result.getPosition());
        }

        Record r = new Record(getTimeStamp(),resultString);
        records.add(r);
    }

    public List<Record> getRecords()
    {
        return records;
    }
}
