package Main.Employee;

import Main.Components.Scanner.ProhibitedItem;
import Main.Components.Scanner.ScanResult;
import Main.Components.Tray;
import Main.Counter.Counter;
import Main.ExplosivesTraceDetector;
import Main.FederalPoliceStation;
import Main.Passenger.Baggage;
import Main.Passenger.Passenger;
import Main.Robot;
import Main.SimulationEmployee.SupervisorWorkspaceSupervisor;
import Main.TestStripe;

import java.time.LocalDate;

public class FederalPoliceOfficer extends Employee {
    private final FederalPoliceStation station;
    private Robot controlledRobot;

    public FederalPoliceOfficer(int id, String name, LocalDate birthDate, IDCard idCard, int grade, FederalPoliceStation station) {
        super(id, name, birthDate, idCard);
        this.station = station;
    }

    public void arrestPassenger(Passenger passenger) {
    }

    public FederalPoliceOfficer callBackup() {
        return station.getBackup();
    }

    public Robot callRoboter() {
        return station.getRoboter();
    }

    public TestStripe swipe(Baggage baggage) {
        return new TestStripe(new char[30][10]);
    }


    public void checkTestStripeWithDetector(TestStripe teststripe, ExplosivesTraceDetector detector) {
        detector.test(teststripe);
    }

    public void openHandBaggageWeaponToOfficer(Passenger passenger, SupervisorWorkspaceSupervisor supervisor, FederalPoliceOfficer policeOfficer03, Tray removeTray, ScanResult result) {
        var baggage = removeTray.getBaggage();
        if (result.getType() != ProhibitedItem.WEAPON_GLOCK7) {
            throw new IllegalArgumentException();
        }
        var layer = baggage.getLayers()[result.getLayer()];
        layer.rewriteFromPos(result.getPosition(), "000000000000000");
        baggage.setLayer(result.getLayer(), layer);
        removeTray.setBaggage(baggage);
    }

    public void releasePassenger() {
    }

    @Override
    public Counter getCounter() {
        return null;
    }

    public void controlRoboter(Robot robot) {
        controlledRobot = robot;
    }
}

