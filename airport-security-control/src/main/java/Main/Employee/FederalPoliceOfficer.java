package Main.Employee;

import Main.Components.Scanner.ProhibitedItem;
import Main.Components.Scanner.ScanResult;
import Main.Components.Tray;
import Main.Counter.Counter;
import Main.ExplosivesTraceDetector;
import Main.FederalPoliceStation;
import Main.Passenger.DestroyedBaggage;
import Main.Passenger.Baggage;
import Main.Passenger.Passenger;
import Main.Robot;
import Main.SimulationEmployee.SupervisorWorkspaceSupervisor;
import Main.TestStripe;

import java.time.LocalDate;

public class FederalPoliceOfficer extends Employee {
    private final int grade;
    private final FederalPoliceStation station;
    private Passenger arrestedPassenger;
    private Robot controlledRobot;

    public FederalPoliceOfficer(int id, String name, LocalDate birthDate, IDCard idCard, int grade, FederalPoliceStation station) {
        super(id, name, birthDate, idCard);
        this.grade = grade;
        this.station = station;
    }

    public void arrestPassenger(Passenger passenger) {
        arrestedPassenger = passenger;
    }

    public FederalPoliceOfficer callBackup() {
        return station.getBackup();
    }

    public Robot callRoboter() {
        return station.getRoboter();
    }

    public TestStripe swipe(Baggage baggage) {

        dummyTestStripe();

        return new TestStripe(new char[30][10]);
    }

    private static void dummyTestStripe() {

        var data = "0123456789".toCharArray();

        char[][] chars = new char[30][10];

        chars[0] = "000exp0000".toCharArray();

        for (var i = 1; i < chars.length; i++) {
            chars[i] = data;
        }

    }

    public void checkTestStripeWithDetector(TestStripe teststripe, ExplosivesTraceDetector detector) {
        detector.test(teststripe);
    }

    public DestroyedBaggage destroy(Baggage baggage) {
        return controlledRobot.destroy(baggage);
    }

    public void openHandBaggageWeaponToOfficer(Passenger passenger, SupervisorWorkspaceSupervisor supervisor, FederalPoliceOfficer policeOfficer03, Tray removeTray, ScanResult result) {
        var baggage = removeTray.getHandBaggage();
        if (result.getType() != ProhibitedItem.WEAPON_GLOCK7) {
            throw new IllegalArgumentException();
        }

        var layer = baggage.getLayers()[result.getLayer()];


        layer.rewriteFromPos(result.getPosition(), "000000000000000");
        baggage.setLayer(result.getLayer(), layer);

        removeTray.setHandBaggage(baggage);

    }

    public void releasePassenger() {
        arrestedPassenger = null;
    }

    @Override
    public Counter getCounter() {
        return null;
    }

    public void controlRoboter(Robot robot) {
        controlledRobot = robot;
    }
}

