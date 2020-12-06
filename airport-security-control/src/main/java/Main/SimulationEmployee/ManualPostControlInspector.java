package Main.SimulationEmployee;

import Main.Components.Scanner.ProhibitedItem;
import Main.Components.Scanner.ScanResult;
import Main.Components.Tray;
import Main.Employee.IDCard;
import Main.Employee.Inspector;
import Main.Passenger.Passenger;
import Main.Counter.ManualPostControl;

import java.time.LocalDate;


public class ManualPostControlInspector extends Inspector {

    private final ManualPostControl manualPostControl;

    public ManualPostControlInspector(int id, String name, LocalDate birthDate, IDCard idCard, boolean isSenior, ManualPostControl manualPostControl) {
        super(id, name, birthDate, idCard, isSenior);

        this.manualPostControl = manualPostControl;
    }

    @Override
    public ManualPostControl getCounter() {
        return manualPostControl;
    }

    public Tray openHandBaggageAndDisposeKnife(Passenger passenger, Tray removeTray, ScanResult result) {
        var baggage = removeTray.getBaggage();
        if (result.getType() != ProhibitedItem.KNIFE) {
            throw new IllegalArgumentException();
        }

        var layer = baggage.getLayers()[result.getLayer()];
        layer.rewriteFromPos(result.getPosition(), "00000");

        baggage.setLayer(result.getLayer(), layer);

        removeTray.setBaggage(baggage);

        return removeTray;
    }

    public void putTrayToBaggageScannerExit(Tray tray) {
        manualPostControl.getBaggageScanner().setOutgoingTray(tray);
    }
}
