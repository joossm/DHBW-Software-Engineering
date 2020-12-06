import Main.Components.BaggageScanner.BS;
import Main.Components.*;
import Main.Components.Scanner.*;
import Main.Components.Scanner.Recorder.ScanRecorder;
import Main.Counter.ManualPostControl;
import Main.Counter.OperationStation;
import Main.Counter.SupervisorCounter;
import Main.Employee.*;
import Main.ExplosivesTraceDetector;
import Main.FederalPoliceStation;
import Main.Passenger.Baggage;
import Main.Passenger.Layer;
import Main.Passenger.Passenger;
import Main.Robot;
import Main.SimulationEmployee.ManualPostControlInspector;
import Main.SimulationEmployee.OperationStationInspector;
import Main.SimulationEmployee.RollerConveyorInspector;
import Main.SimulationEmployee.SupervisorWorkspaceSupervisor;
import Main.TestStripe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {

    Track trackOne;
    Track trackTwo;
    BS BS;
    RollerConveyor rollerConveyor;
    ExplosivesTraceDetector detector;

    SupervisorCounter supervisorWorkspace;
    SupervisorWorkspaceSupervisor supervisor;

    FederalPoliceStation federalPoliceStation;

    OperationStation operationStation;

    OperationStationInspector inspectorI2;
    RollerConveyorInspector inspectorI1;
    ManualPostControlInspector inspectorI3;
    FederalPoliceOfficer policeOfficer01;
    Technician technician;


    private BS generateBaggageScanner(Track track01, Track track02) {
        return new BS(new Reader(), new Scanner(), new ScanRecorder(), track01, track02, new Belt(), new Configuration(SearchAlgorithm.boyerMoore));
    }

    private SupervisorWorkspaceSupervisor generateSupervisor(SupervisorCounter supervisorWorkspace) throws Exception {
        var supervisorIDCard = new IDCard(5,
                LocalDate.of(2030, 1, 1),
                "***S***1234***"
        );

        return new SupervisorWorkspaceSupervisor(1,
                "Jodie Foster",
                LocalDate.of(1962, 11, 19),
                supervisorIDCard,
                false,
                false,
                supervisorWorkspace);
    }

    private FederalPoliceStation generateFederalPoliceStation() throws Exception {
        var federalPoliceStation = new FederalPoliceStation();
        federalPoliceStation.addOfficer(new FederalPoliceOfficer(19,
                "Toto",
                LocalDate.of(1969, 1, 1),
                new IDCard(20, LocalDate.of(2030, 1, 1), "***O***1234***"),
                5,
                federalPoliceStation));
        federalPoliceStation.addOfficer(new FederalPoliceOfficer(23,
                "Harry",
                LocalDate.of(1969, 1, 1),
                new IDCard(20, LocalDate.of(2030, 1, 1), "***O***1234***"),
                5,
                federalPoliceStation));

        return federalPoliceStation;
    }

    private OperationStationInspector generateOperationStationInspector(OperationStation operationStation) throws Exception {
        var inspectorI2IDCard = new IDCard(8,
                LocalDate.of(2030, 1, 1),
                "***I***1234***"
        );

        return new OperationStationInspector(2,
                "Natalie Portman",
                LocalDate.of(1981, 6, 9),
                inspectorI2IDCard,
                false,
                operationStation);
    }

    private ManualPostControlInspector generateManualPostControlInspector(ManualPostControl manualPostControl) throws Exception {
        var idCard = new IDCard(8,
                LocalDate.of(2030, 1, 1),
                "***I***1234***"
        );

        return new ManualPostControlInspector(4,
                "Bruce Willis",
                LocalDate.of(1955, 3, 19),
                idCard,
                true,
                manualPostControl);
    }

    private FederalPoliceOfficer generateFederalPoliceOfficer01(FederalPoliceStation federalPoliceStation) throws Exception {

        var idCard = new IDCard(8,
                LocalDate.of(2030, 1, 1),
                "***O***1234***"
        );

        return new FederalPoliceOfficer(4,
                "Wesley Snipes",
                LocalDate.of(1962, 7, 31),
                idCard, 3, federalPoliceStation);
    }

    private RollerConveyorInspector generateRollerConveyorInspector(RollerConveyor rollerConveyor) throws Exception {

        var idCard = new IDCard(8,
                LocalDate.of(2030, 1, 1),
                "***I***1234***"
        );

        return new RollerConveyorInspector(4,
                "Clint Eastwood",
                LocalDate.of(1930, 5, 31),
                idCard,
                true,
                rollerConveyor);
    }

    private Technician generateTechnician() throws Exception {

        var idCard = new IDCard(666,
                LocalDate.of(2030, 1, 1),
                "***T***1234***"
        );

        return new Technician(4,
                "Jasen Statham",
                LocalDate.of(1967, 7, 26),
                idCard);
    }


    private List<String> readLinesFromStream(InputStream stream) {

        List<String> retList = new ArrayList<>();

        var myReader = new java.util.Scanner(stream);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            retList.add(data);
        }
        myReader.close();

        return retList;
    }

    private char[] generateRandomData() {
        char[] data = new char[10000];

        final int min = 33;
        final int max = 125;

        for (var i = 0; i < data.length; i++) {
            data[i] = (char) ThreadLocalRandom.current().nextInt(min, max + 1);
        }

        return data;
    }

    private Baggage generateRandomHandBaggage() {
        Layer[] layers = new Layer[3];

        for (var i = 0; i < layers.length; i++) {

            layers[i] = new Layer(generateRandomData());
        }
        return new Baggage(layers);
    }

    private Baggage[] generateRandomHandBaggage(int num) {
        Baggage[] baggage = new Baggage[num];
        for (var i = 0; i < baggage.length; i++) {
            baggage[i] = generateRandomHandBaggage();
        }
        return baggage;
    }

    private Passenger generatePassenger(String input) {
        var split = input.split(";", 3);

        var name = split[0];
        var baggageAmount = Integer.parseInt(split[1]);
        var props = split[2];

        Baggage[] baggage = generateRandomHandBaggage(baggageAmount);


        if (!props.equals("-")) {
            props = props.substring(1, props.length() - 1);

            var count = 0;
            for (var prop : props.split(";")) {
                var propValues = prop.split(",");
                var type = propValues[0];
                var layer = Integer.parseInt(propValues[1]);
                var pos = Integer.parseInt(propValues[2]);

                var selectively = baggage[count];
                var selectedLayer = selectively.getLayers()[layer];
                if ("K".equals(type)) {
                    selectedLayer.rewriteFromPos(pos, "kn!fe");
                } else if ("W".equals(type)) {
                    selectedLayer.rewriteFromPos(pos, "glock|7");
                } else if ("E".equals(type)) {
                    selectedLayer.rewriteFromPos(pos, "exp|os!ve");
                } else {
                    throw new RuntimeException("Unknown type: " + type);
                }

                selectively.setLayer(layer, selectedLayer);
                baggage[count] = selectively;


                count++;
            }
        }

        return new Passenger(name, baggage);
    }

    private List<Passenger> generatePassengersFromLines(List<String> lines) {

        var passengers = new ArrayList<Passenger>();

        for (var line : lines) {
            var passenger = generatePassenger(line);
            passengers.add(passenger);
        }
        return passengers;
    }

    private List<Passenger> generatePassengersFromStream(InputStream stream) {

        var lines = readLinesFromStream(stream);
        return generatePassengersFromLines(lines);

    }


    private void processOnWeaponFound(Tray tray, Passenger passenger, FederalPoliceOfficer policeOfficer03, ScanResult result) throws Exception {

        policeOfficer01.openHandBaggageWeaponToOfficer(passenger, supervisor, policeOfficer03, tray, result);

        inspectorI2.clickButtonLeft();

    }

    private void processOnExplosiveFound(Tray tray, FederalPoliceOfficer policeOfficer02, FederalPoliceOfficer policeOfficer03) {
        Robot robot = policeOfficer01.callRoboter();
        policeOfficer02.controlRoboter(robot);

        TestStripe teststripe = policeOfficer03.swipe(tray.getBaggage());
        policeOfficer03.checkTestStripeWithDetector(teststripe, detector);

    }

    private void processOnKnifeFound(Tray tray, Passenger passenger, ScanResult result) throws Exception {
        inspectorI2.contact();
        Tray removedTray = inspectorI3.openHandBaggageAndDisposeKnife(passenger, tray, result);
        inspectorI3.putTrayToBaggageScannerExit(removedTray);
        inspectorI2.clickButtonLeft();
    }

    private void returnHandBaggageToPassanger(Passenger passenger, List<Baggage> scannedBaggage) {
        for (var hb : scannedBaggage) {
            passenger.addBaggage(hb);
        }
    }


    @BeforeEach
    private void init() throws Exception {
        trackOne = new Track();
        trackTwo = new Track();
        BS = generateBaggageScanner(trackOne, trackTwo);
        rollerConveyor = new RollerConveyor(BS);
        detector = new ExplosivesTraceDetector();

        supervisorWorkspace = new SupervisorCounter(BS);
        supervisor = generateSupervisor(supervisorWorkspace);

        federalPoliceStation = generateFederalPoliceStation();

        operationStation = new OperationStation(BS);

        inspectorI2 = generateOperationStationInspector(operationStation);
        inspectorI1 = generateRollerConveyorInspector(rollerConveyor);
        inspectorI3 = generateManualPostControlInspector(new ManualPostControl(BS));
        policeOfficer01 = generateFederalPoliceOfficer01(federalPoliceStation);

        technician = generateTechnician();

    }

    @TestFactory
    Stream<DynamicTest> passengerProhibitItemsTest() throws Exception {
        var passengers = new ArrayList<Passenger>();
        passengers.add(generatePassenger("Clyde Barrow;1;[W,1,4]"));
        passengers.add(generatePassenger("Philip Bottomley;2;[K,2,1]"));
        passengers.add(generatePassenger("Joanne Edwards;2;[K,1,2;W,2,5]"));
        passengers.add(generatePassenger("Joaquin Loera;1;[E,1,1]"));

        List<String> names = new ArrayList<>(Arrays.asList("Clyde Barrow", "Philip Bottomley", "Joanne Edwards", "Joaquin Loera"));
        List<ScanResult> scanResults = new ArrayList<>();
        scanResults.add(new ScanResult(1, 4, ProhibitedItem.WEAPON_GLOCK7));

        scanResults.add(new ScanResult(2, 1, ProhibitedItem.KNIFE));
        scanResults.add(new ScanResult(-1, -1, ProhibitedItem.CLEAN));

        scanResults.add(new ScanResult(1, 2, ProhibitedItem.KNIFE));
        scanResults.add(new ScanResult(2, 5, ProhibitedItem.WEAPON_GLOCK7));

        scanResults.add(new ScanResult(1, 1, ProhibitedItem.EXPLOSIVE));

        AtomicInteger baggageCounter = new AtomicInteger();

        startBaggageScannerRoutine();

        return passengers.stream()
                .map(passenger -> DynamicTest.dynamicTest("Checking passenger: " + passenger.getName(),
                        () -> {
                            int id = passengers.indexOf(passenger);
                            assertEquals(passenger.getName(), names.get(id));

                            for (var i = 0; i < passenger.getBaggage().length; i++) {

                                var curBaggage = passenger.getBaggage()[i];

                                Tray t = new Tray();
                                t.setBaggage(curBaggage);
                                rollerConveyor.addTray(t);

                                inspectorI1.push();
                                inspectorI2.clickButtonRight();

                                inspectorI2.clickButtonRect();

                                var result = BS.getCurrentScanResult();

                                var c = baggageCounter.getAndIncrement();

                                assertEquals(scanResults.get(c).getType(), result.getType());
                                assertEquals(scanResults.get(c).getLayer(), result.getLayer());
                                assertEquals(scanResults.get(c).getPosition(), result.getPosition());

                                var scanRecorder = BS.getScanRecorder();
                                var records = scanRecorder.getRecords();
                                assertEquals(c + 1, records.size());

                                var curRecord = records.get(c);
                                var curResult = curRecord.getResult().toLowerCase();
                                var scannerResultType = result.getType().toString().toLowerCase();
                                assertTrue(curResult.contains(scannerResultType));

                            }
                        }));

    }


    @TestFactory
    Stream<DynamicTest> dynamicTestsForInitPassengersAmounts() {
        var stream = getClass().getClassLoader().getResourceAsStream("passenger_baggage.txt");
        var passengers = generatePassengersFromStream(stream);

        List<Integer> outputList = Arrays.asList(1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1,
                2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1);

        assertEquals(passengers.size(), 568);

        return passengers.stream()
                .map(passenger -> DynamicTest.dynamicTest("Checking passenger: " + passenger.getName(),
                        () -> {
                            int id = passengers.indexOf(passenger);
                            assertEquals(passenger.getBaggage().length, outputList.get(id));
                        }));
    }

    @Test
    public void lockCardTest() throws Exception {
        Reader reader = new Reader();

        assertTrue(supervisor.auth(reader, new Pin("1234"), EmployeeProfileType.S));

        for (var i = 0; i < 5; i++) {
            var auth = supervisor.auth(reader, new Pin("0000"), EmployeeProfileType.S);
            assertFalse(auth);
        }
        assertFalse(supervisor.auth(reader, new Pin("1234"), EmployeeProfileType.S));
    }

    @Test
    public void checkProfileOBaggageScanner() throws Exception {
        assertFalse(BS.activate(policeOfficer01, new Pin("1234")));
    }

    @Test
    public void unlockBaggageScanner() throws Exception {
        startBaggageScannerRoutine();
        assertTrue(inspectorI2.activateAlarm());

        assertFalse(BS.unlock(inspectorI1, new Pin("1234")));
        assertFalse(BS.unlock(inspectorI2, new Pin("1234")));
        assertFalse(BS.unlock(inspectorI3, new Pin("1234")));
        assertFalse(BS.unlock(policeOfficer01, new Pin("1234")));
        assertFalse(BS.unlock(technician, new Pin("1234")));

        assertTrue(BS.unlock(supervisor, new Pin("1234")));
    }


    @Test
    public void workplacesTest() throws Exception {

        Reader reader = new Reader();


        assertEquals(inspectorI1.getName(), "Clint Eastwood");
        assertEquals(inspectorI1.getBirthDate(), LocalDate.of(1930, 5, 31));
        assertTrue(inspectorI1.isSenior());
        assertNotNull(inspectorI1.getCounter());
        assertTrue(reader.checkCard(inspectorI1.getIDCard(), EmployeeProfileType.I));

        assertEquals(inspectorI2.getName(), "Natalie Portman");
        assertEquals(inspectorI2.getBirthDate(), LocalDate.of(1981, 6, 9));
        assertFalse(inspectorI2.isSenior());
        assertNotNull(inspectorI2.getCounter());
        assertTrue(reader.checkCard(inspectorI2.getIDCard(), EmployeeProfileType.I));

        assertEquals(inspectorI3.getName(), "Bruce Willis");
        assertEquals(inspectorI3.getBirthDate(), LocalDate.of(1955, 3, 19));
        assertTrue(inspectorI3.isSenior());
        assertNotNull(inspectorI3.getCounter());
        assertTrue(reader.checkCard(inspectorI3.getIDCard(), EmployeeProfileType.I));

        assertEquals(supervisor.getName(), "Jodie Foster");
        assertEquals(supervisor.getBirthDate(), LocalDate.of(1962, 11, 19));
        assertFalse(supervisor.isSenior());
        assertNotNull(supervisor.getCounter());
        assertTrue(reader.checkCard(supervisor.getIDCard(), EmployeeProfileType.S));

        assertEquals(policeOfficer01.getName(), "Wesley Snipes");
        assertEquals(policeOfficer01.getBirthDate(), LocalDate.of(1962, 7, 31));
        assertTrue(reader.checkCard(policeOfficer01.getIDCard(), EmployeeProfileType.O));

        assertEquals(technician.getName(), "Jasen Statham");
        assertEquals(technician.getBirthDate(), LocalDate.of(1967, 7, 26));
        assertTrue(reader.checkCard(technician.getIDCard(), EmployeeProfileType.T));
    }

    private void startBaggageScannerRoutine() throws Exception {
        assertTrue(supervisor.pressButtonStartBaggageScanner());

        boolean accepted = false;
        while (!accepted) {
            accepted = inspectorI2.activateBaggageScanner(new Pin("1234"));
        }

        assertTrue(accepted);
    }

    @Test
    public void mainTest() throws Exception {
        var stream = getClass().getClassLoader().getResourceAsStream("passenger_baggage.txt");
        var passengers = generatePassengersFromStream(stream);


        startBaggageScannerRoutine();

        for (Passenger passenger : passengers) {
            Baggage baggage;
            List<Baggage> scannedBaggage = new ArrayList<>();
            while ((baggage = passenger.getNextBaggage()) != null) {
                Tray t = new Tray();
                t.setBaggage(baggage);
                rollerConveyor.addTray(t);

                inspectorI1.push();
                inspectorI2.clickButtonRight();

                do {

                    var scanWorked = inspectorI2.clickButtonRect();
                    assertTrue(scanWorked);

                    var result = BS.getCurrentScanResult();

                    Tray tray;

                    if (result.isClean()) {
                        tray = trackTwo.removeTray();
                    } else {
                        tray = trackOne.removeTray();
                    }

                    if (result.getType() == ProhibitedItem.KNIFE) {
                        processOnKnifeFound(tray, passenger, result);
                    } else if (result.getType() == ProhibitedItem.EXPLOSIVE || result.getType() == ProhibitedItem.WEAPON_GLOCK7) {
                        inspectorI2.activateAlarm();

                        policeOfficer01.arrestPassenger(passenger);

                        FederalPoliceOfficer policeOfficer02 = policeOfficer01.callBackup();
                        FederalPoliceOfficer policeOfficer03 = policeOfficer01.callBackup();


                        if (result.getType() == ProhibitedItem.EXPLOSIVE) {
                            processOnExplosiveFound(tray, policeOfficer02, policeOfficer03);
                        } else if (result.getType() == ProhibitedItem.WEAPON_GLOCK7) {
                            processOnWeaponFound(tray, passenger, policeOfficer03, result);
                        }

                        policeOfficer01.releasePassenger();
                        policeOfficer02.arrestPassenger(passenger);
                        var unlockWorked = supervisor.unlockBaggageScanner(new Pin("1234"));
                        assertTrue(unlockWorked);

                        federalPoliceStation.addOfficer(policeOfficer02);
                        federalPoliceStation.addOfficer(policeOfficer03);
                    } else if (result.getType() == ProhibitedItem.CLEAN) {
                        scannedBaggage.add(tray.getBaggage());
                    } else {
                        throw new IllegalStateException("Unexpected value: " + result.getType());
                    }

                } while (!BS.getCurrentScanResult().isClean());
            }
            returnHandBaggageToPassanger(passenger, scannedBaggage);
        }
    }
}
