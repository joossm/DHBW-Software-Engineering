import Main.*;
import Main.Components.*;
import Main.Components.BaggageScanner.BS;
import Main.Components.Reader;
import Main.Components.Scanner.*;
import Main.Components.Scanner.Recorder.ScanRecorder;
import Main.Employee.*;
import Main.Passenger.DestroyedBaggage;
import Main.Passenger.Baggage;
import Main.Passenger.Layer;
import Main.Passenger.Passenger;
import Main.SimulationEmployee.ManualPostControlInspector;
import Main.SimulationEmployee.OperationStationInspector;
import Main.SimulationEmployee.RollerConveyorInspector;
import Main.SimulationEmployee.SupervisorWorkspaceSupervisor;
import Main.Counter.ManualPostControl;
import Main.Counter.OperationStation;
import Main.Counter.SupervisorCounter;
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


public class MainTest
{

    Track track01;
    Track track02;
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


    private Passenger[] generateDummyPassengers() {
        Passenger[] passengers;
        passengers = new Passenger[50];

        for (var k = 0; k < passengers.length; k++) {

            Baggage[] b;
            b = new Baggage[3];

            for (var i = 0; i < b.length; i++) {
                Layer[] l;
                l = new Layer[3];
                for (var y = 0; y < l.length; y++) {
                    l[y] = new Layer(new char[10000]);
                }

                b[i] = new Baggage(l);
            }

            passengers[k] = new Passenger("Reiner Kalmund", b);
        }

        return passengers;
    }
    private BS generateBaggageScanner(Track track01, Track track02) {
        return new BS(new Reader(),new Scanner(), new ScanRecorder(), track01, track02, new Belt(), new Configuration(SearchAlgorithm.boyerMoore));
    }
    private SupervisorWorkspaceSupervisor generateSupervisor(SupervisorCounter supervisorWorkspace) {
        var supervisorIDCard = new IDCard(5,
                LocalDate.of(2030,1,1),
                "***S***1234***",
                IDCardType.staff);

        var supervisor = new SupervisorWorkspaceSupervisor(1,
                "Jodie Foster",
                LocalDate.of(1962,11,19),
                supervisorIDCard,
                false,
                false,
                supervisorWorkspace);

        return supervisor;
    }
    private FederalPoliceStation generateFederalPoliceStation() {
        var federalPoliceStation = new FederalPoliceStation();
        federalPoliceStation.addOfficer(new FederalPoliceOfficer(19,
                "Toto",
                LocalDate.of(1969,1,1),
                new IDCard(20,LocalDate.of(2030,1,1),"***O***1234***",IDCardType.staff),
                5,
                federalPoliceStation));
        federalPoliceStation.addOfficer(new FederalPoliceOfficer(23,
                "Harry",
                LocalDate.of(1969,1,1),
                new IDCard(20,LocalDate.of(2030,1,1),"***O***1234***",IDCardType.staff),
                5,
                federalPoliceStation));

        return federalPoliceStation;
    }
    private OperationStationInspector generateOperationStationInspector(OperationStation operationStation) {
        var inspectorI2IDCard = new IDCard(8,
                LocalDate.of(2030, 1, 1),
                "***I***1234***",
                IDCardType.staff);
        var inspectorI2 = new OperationStationInspector(2,
                "Natalie Portman",
                LocalDate.of(1981, 6, 9),
                inspectorI2IDCard,
                false,
                operationStation);

        return inspectorI2;
    }
    private ManualPostControlInspector generateManualPostControlInspector(ManualPostControl manualPostControl) {
        var idcard = new IDCard(8,
                LocalDate.of(2030, 1, 1),
                "***I***1234***",
                IDCardType.staff);

        return new ManualPostControlInspector(4,
                "Bruce Willis",
                LocalDate.of(1955, 3, 19),
                idcard,
                true,
                manualPostControl);
    }
    private FederalPoliceOfficer generateFederalPoliceOfficer01(FederalPoliceStation federalPoliceStation) {

        var idcard = new IDCard(8,
                LocalDate.of(2030, 1, 1),
                "***O***1234***",
                IDCardType.staff);

        return new FederalPoliceOfficer(4,
                "Wesley Snipes",
                LocalDate.of(1962, 7, 31),
                idcard, 3, federalPoliceStation);
    }
    private RollerConveyorInspector generateRollerConveyorInspector(RollerConveyor rollerConveyor) {

        var idcard = new IDCard(8,
                LocalDate.of(2030, 1, 1),
                "***I***1234***",
                IDCardType.staff);

        return new RollerConveyorInspector(4,
                "Clint Eastwood",
                LocalDate.of(1930, 5, 31),
                idcard,
                true,
                rollerConveyor);
    }
    private Technician generateTechnician() {

        var idcard = new IDCard(666,
                LocalDate.of(2030, 1, 1),
                "***T***1234***",
                IDCardType.staff);

        return new Technician(4,
                "Jasen Statham",
                LocalDate.of(1967, 7, 26),
                idcard);
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
    private char[] generateRandomData(int length) {
        char[] data = new char[length];

        //ASCII characters
        final int min = 33;
        final int max = 125;

        for(var i = 0; i < data.length; i++) {
            data[i] = (char)ThreadLocalRandom.current().nextInt(min, max + 1);
        }

        return data;
    }
    private Baggage generateRandomHandBaggage() {
        Layer[] layers = new Layer[3];

        for(var i = 0; i < layers.length; i++) {

            layers[i] = new Layer(generateRandomData(10000));
        }

        return new Baggage(layers);
    }
    private Baggage[] generateRandomHandBaggage(int num) {
        Baggage[] baggages = new Baggage[num];
        for(var i = 0; i < baggages.length; i++) {
            baggages[i] = generateRandomHandBaggage();
        }

        return baggages;
    }
    private Passenger generatePassenger(String input) {
        var splitted = input.split(";",3);

        var name = splitted[0];
        var baggageAmount = Integer.parseInt(splitted[1]);
        var props = splitted[2];

        Baggage[] baggages = generateRandomHandBaggage(baggageAmount);


        if(!props.equals("-")) {
            props = props.substring(1,props.length()-1); // remove [...]

            var count = 0;
            for ( var prop : props.split(";")) {
                var propValues = prop.split(",");
                var type = propValues[0];
                var layer = Integer.parseInt(propValues[1]);
                var pos = Integer.parseInt(propValues[2]);

                var selectedbaggage = baggages[count];
                var selectedLayer = selectedbaggage.getLayers()[layer];
                switch (type) {
                    case "K" : {
                        selectedLayer.rewriteFromPos(pos, "kn!fe");
                        break;
                    }
                    case "W" : {
                        selectedLayer.rewriteFromPos(pos, "glock|7");
                        break;
                    }
                    case "E" : {
                        selectedLayer.rewriteFromPos(pos, "exp|os!ve");
                        break;
                    }
                    default : {
                        throw new RuntimeException("Unknown type: " + type);
                    }
                }

                selectedbaggage.setLayer(layer, selectedLayer);
                baggages[count] = selectedbaggage;


                count++;
            }
        }

        return new Passenger(name, baggages);
    }
    private List<Passenger> generatePassengersFromLines(List<String> lines) {

        var passangers = new ArrayList<Passenger>();

        for (var line : lines) {
            var passenger = generatePassenger(line);
            passangers.add(passenger);
        }
        return passangers;
    }
    private List<Passenger> generatePassengersFromStream(InputStream stream) {

        var lines = readLinesFromStream(stream);
        return generatePassengersFromLines(lines);

    }


    private Tray processOnWeaponFound(Tray tray, Passenger passenger, FederalPoliceOfficer policeOfficer03, ScanResult result) {

        var ret = policeOfficer01.openHandBaggageWeaponToOfficer(passenger, supervisor, policeOfficer03, tray, result);

        inspectorI2.clickButtonLeft(); //Into scanner

        return ret;
    }
    private DestroyedBaggage processOnExplosiveFound(Tray tray, FederalPoliceOfficer policeOfficer02, FederalPoliceOfficer policeOfficer03) {
        Robot robot = policeOfficer01.callRoboter();
        policeOfficer02.controlRoboter(robot);

        TestStripe teststripe = policeOfficer03.swipe(tray.getHandBaggage());
        var resultDetector = policeOfficer03.checkTestStripeWithDetector(teststripe, detector);

        DestroyedBaggage destroyedBaggage = policeOfficer02.destroy(tray.getHandBaggage());

        return destroyedBaggage;
    }
    private void processOnKnifeFound(Tray tray, Passenger passenger, ScanResult result) {
        inspectorI2.contact(inspectorI3);
        Tray removedTray = inspectorI3.openHandBaggageAndDisposeKnife(passenger, tray, result);
        inspectorI3.putTrayToBaggageScannerExit(removedTray);
        inspectorI2.clickButtonLeft(); //back Into scanner
    }
    private Passenger returnHandBaggageToPassanger(Passenger passenger, List<Baggage> scannedBaggage) {
        for ( var hb : scannedBaggage) {
            passenger.addBaggage(hb);
        }
        return passenger;
    }




    @BeforeEach
    private void init() {
        track01 = new Track();
        track02 = new Track();
        BS = generateBaggageScanner(track01, track02);
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
    Stream<DynamicTest> passengerProhibitItemsTest() {
        var passengers = new ArrayList<Passenger>();
        passengers.add(generatePassenger("Clyde Barrow;1;[W,1,4]"));
        passengers.add(generatePassenger("Philip Bottomley;2;[K,2,1]"));
        passengers.add(generatePassenger("Joanne Edwards;2;[K,1,2;W,2,5]"));
        passengers.add(generatePassenger("Joaquin Loera;1;[E,1,1]"));

        List<String> names = new ArrayList<String>(Arrays.asList("Clyde Barrow", "Philip Bottomley", "Joanne Edwards", "Joaquin Loera"));
        List<ScanResult> scanResults = new ArrayList<ScanResult>();
        scanResults.add(new ScanResult(1,4, ProhibitedItem.WEAPON_GLOCK7));

        scanResults.add(new ScanResult(2,1, ProhibitedItem.KNIFE));
        scanResults.add(new ScanResult(-1,-1, ProhibitedItem.CLEAN));

        scanResults.add(new ScanResult(1,2, ProhibitedItem.KNIFE));
        scanResults.add(new ScanResult(2,5, ProhibitedItem.WEAPON_GLOCK7));

        scanResults.add(new ScanResult(1,1, ProhibitedItem.EXPLOSIVE));

        AtomicInteger baggageCounter = new AtomicInteger();

        startBaggageScannerRoutine();

        return passengers.stream()
                .map(passenger -> DynamicTest.dynamicTest("Checking passenger: " + passenger.getName(),
                        () -> {
                                int id = passengers.indexOf(passenger);
                                assertEquals(passenger.getName(), names.get(id));

                                for (var i = 0; i < passenger.getBaggages().length; i++) {

                                    var curBaggage = passenger.getBaggages()[i];

                                    Tray t = new Tray();
                                    t.setHandBaggage(curBaggage);
                                    rollerConveyor.addTray(t);

                                    inspectorI1.push(); //to scanner
                                    inspectorI2.clickButtonRight(); //into scanner

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

        List<Integer> outputList = Arrays.asList(1,1,1,2,1,1,1,1,1,2,1,1,1,1,3,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,
                2,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,
                1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
                1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1);

        assertEquals(passengers.size(), 568);

        return passengers.stream()
                .map(passenger -> DynamicTest.dynamicTest("Checking passenger: " + passenger.getName(),
                        () -> {int id = passengers.indexOf(passenger);
                            assertEquals(passenger.getBaggages().length, outputList.get(id));
                        }));
    }

    @Test
    public void lockCardTest() {
        Reader reader = new Reader();

        assertTrue(supervisor.auth(reader, new Pin("1234"), EmployeeProfileType.S));

        for(var i = 0; i < 5; i++) {
            var ret = supervisor.auth(reader, new Pin("0000"), EmployeeProfileType.S);
            assertFalse(ret);
        }
        assertFalse(supervisor.auth(reader, new Pin("1234"), EmployeeProfileType.S));
    }

    @Test
    public void checkProfileOBaggageScanner() {
        assertFalse(BS.activate(policeOfficer01, new Pin("1234")));
    }

    @Test
    public void unlockBaggageScanner() {
        startBaggageScannerRoutine();
        assertTrue(inspectorI2.activateAlarm()); //lock

        assertFalse(BS.unlock(inspectorI1,new Pin("1234")));
        assertFalse(BS.unlock(inspectorI2,new Pin("1234")));
        assertFalse(BS.unlock(inspectorI3,new Pin("1234")));
        assertFalse(BS.unlock(policeOfficer01,new Pin("1234")));
        assertFalse(BS.unlock(technician,new Pin("1234")));

        assertTrue(BS.unlock(supervisor,new Pin("1234")));
    }



    @Test
    public void workplacesTest() {

        Reader reader = new Reader();



        assertEquals(inspectorI1.getName(), "Clint Eastwood");
        assertEquals(inspectorI1.getBirthDate(), LocalDate.of(1930,5,31));
        assertTrue(inspectorI1.isSenior());
        assertNotNull(inspectorI1.getWorkspace());
        assertTrue(reader.checkCard(inspectorI1.getIDCard(),EmployeeProfileType.I));

        assertEquals(inspectorI2.getName(), "Natalie Portman");
        assertEquals(inspectorI2.getBirthDate(), LocalDate.of(1981,6,9));
        assertFalse(inspectorI2.isSenior());
        assertNotNull(inspectorI2.getWorkspace());
        assertTrue(reader.checkCard(inspectorI2.getIDCard(),EmployeeProfileType.I));

        assertEquals(inspectorI3.getName(), "Bruce Willis");
        assertEquals(inspectorI3.getBirthDate(), LocalDate.of(1955,3,19));
        assertTrue(inspectorI3.isSenior());
        assertNotNull(inspectorI3.getWorkspace());
        assertTrue(reader.checkCard(inspectorI3.getIDCard(),EmployeeProfileType.I));

        assertEquals(supervisor.getName(), "Jodie Foster");
        assertEquals(supervisor.getBirthDate(), LocalDate.of(1962,11,19));
        assertFalse(supervisor.isSenior());
        assertNotNull(supervisor.getWorkspace());
        assertTrue(reader.checkCard(supervisor.getIDCard(),EmployeeProfileType.S));

        assertEquals(policeOfficer01.getName(), "Wesley Snipes");
        assertEquals(policeOfficer01.getBirthDate(), LocalDate.of(1962,7,31));
        assertTrue(reader.checkCard(policeOfficer01.getIDCard(),EmployeeProfileType.O));

        assertEquals(technician.getName(), "Jasen Statham");
        assertEquals(technician.getBirthDate(), LocalDate.of(1967,7,26));
        assertTrue(reader.checkCard(technician.getIDCard(),EmployeeProfileType.T));
    }

    private void startBaggageScannerRoutine() {
        //Start baggage scanner
        assertTrue(supervisor.pressButtonStartBaggageScanner());

        //login
        boolean accepted = false;
        while (!accepted)
        {
            accepted = inspectorI2.activateBaggageScanner(new Pin("1234"));
        }

        assertTrue(accepted);
    }

    @Test
    public void mainTest()
    {
        var stream = getClass().getClassLoader().getResourceAsStream("passenger_baggage.txt");
        var passengers = generatePassengersFromStream(stream);


       startBaggageScannerRoutine();

        for ( Passenger passenger : passengers ) {
            Baggage baggage;
            List<Baggage> scannedBaggage = new ArrayList<Baggage>();
            while ((baggage = passenger.getNextBaggage()) != null) {
                Tray t = new Tray();
                t.setHandBaggage(baggage);
                rollerConveyor.addTray(t);

                inspectorI1.push(); //to scanner
                inspectorI2.clickButtonRight(); //into scanner

                ScanResult scanResult = null;

                do {

                    var scanWorked = inspectorI2.clickButtonRect(); //scan
                    assertEquals(scanWorked,true);

                    var result = BS.getCurrentScanResult();

                    Tray tray = null;

                    if(result.isClean()) {
                        tray = track02.removeTray();
                    } else {
                        tray = track01.removeTray();
                    }

                    //track1 manual control
                    switch (result.getType()) {
                        case KNIFE : {
                            processOnKnifeFound(tray, passenger, result);
                            break;
                        }
                        case EXPLOSIVE: {

                            inspectorI2.activateAlarm();

                            policeOfficer01.arrestPassenger(passenger);

                            FederalPoliceOfficer policeOfficer02 = policeOfficer01.callBackup();
                            FederalPoliceOfficer policeOfficer03 = policeOfficer01.callBackup();


                            if (result.getType() == ProhibitedItem.EXPLOSIVE) {
                                processOnExplosiveFound(tray, policeOfficer02, policeOfficer03);
                            }
                            else if (result.getType() == ProhibitedItem.WEAPON_GLOCK7) {
                                processOnWeaponFound(tray, passenger, policeOfficer03, result);
                            }

                            Passenger releasedPassenger = policeOfficer01.releasePassenger();
                            policeOfficer02.arrestPassenger(passenger);
                            var unlockWorked = supervisor.unlockBaggageScanner(new Pin("1234"));
                            assertEquals(unlockWorked, true);

                            federalPoliceStation.addOfficer(policeOfficer02);
                            federalPoliceStation.addOfficer(policeOfficer03);
                            break;
                        }
                        case WEAPON_GLOCK7: {

                            inspectorI2.activateAlarm();

                            policeOfficer01.arrestPassenger(passenger);

                            FederalPoliceOfficer policeOfficer02 = policeOfficer01.callBackup();
                            FederalPoliceOfficer policeOfficer03 = policeOfficer01.callBackup();


                            if (result.getType() == ProhibitedItem.EXPLOSIVE) {
                                processOnExplosiveFound(tray, policeOfficer02, policeOfficer03);
                            }
                            else if (result.getType() == ProhibitedItem.WEAPON_GLOCK7) {
                                processOnWeaponFound(tray, passenger, policeOfficer03, result);
                            }

                            Passenger releasedPassenger = policeOfficer01.releasePassenger();
                            policeOfficer02.arrestPassenger(passenger);
                            var unlockWorked = supervisor.unlockBaggageScanner(new Pin("1234"));
                            assertEquals(unlockWorked, true);

                            federalPoliceStation.addOfficer(policeOfficer02);
                            federalPoliceStation.addOfficer(policeOfficer03);
                            break;
                        }
                        case CLEAN : {
                            scannedBaggage.add(tray.getHandBaggage());
                            break;
                        }
                        default : {
                            throw new IllegalStateException("Unexpected value: " + result.getType());
                        }
                    }

                } while (!BS.getCurrentScanResult().isClean());
            }
            passenger = returnHandBaggageToPassanger(passenger, scannedBaggage);
        }
    }
}
