package Main.Components.BaggageScanner;

import Main.Components.Belt;
import Main.Components.Reader;
import Main.Components.Scanner.Configuration;
import Main.Components.Scanner.ProhibitedItem;
import Main.Components.Scanner.Recorder.ScanRecorder;
import Main.Components.Scanner.ScanResult;
import Main.Components.Scanner.Scanner;
import Main.Components.Track;
import Main.Components.Tray;
import Main.Employee.*;
import Main.SimulationEmployee.OperationStationInspector;

public class BaggageScanner
{

    private BaggageScannerState state = BaggageScannerState.SHUTDOWN;

    private final Reader reader;
    private final Scanner scanner;
    private final ScanRecorder scanRecorder;
    private final Track track01;
    private final Track track02;
    private final Belt belt;
    private ScanResult currentScanResult;
    private Configuration scannerConfiguration;

    public BaggageScanner(Reader reader, Scanner scanner, ScanRecorder scanRecorder, Track track01, Track track02, Belt belt, Configuration scannerConfiguration) {
        this.reader = reader;
        this.scanner = scanner;
        this.scanRecorder = scanRecorder;
        this.track01 = track01;
        this.track02 = track02;
        this.belt = belt;
        this.scannerConfiguration = scannerConfiguration;
    }

    public ScanRecorder getScanRecorder() {
        return scanRecorder;
    }

    public BaggageScannerState getState() {
        return state;
    }

    protected void setState(BaggageScannerState state) {
        this.state = state;
    }

    public Configuration getScannerConfiguration() {
        return scannerConfiguration;
    }

    public void setScannerConfiguration(Configuration scannerConfiguration) {
        this.scannerConfiguration = scannerConfiguration;
    }

    public boolean alarm(Employee e)
    {
        if(e.auth(reader, EmployeeProfileType.I))
            if(getState() != BaggageScannerState.DEACTIVATED && getState() != BaggageScannerState.SHUTDOWN) {
                setState(BaggageScannerState.LOCKED);
                return true;
            }

       return false;
    }
    public boolean report(Employee e)
    {
        if(e.auth(reader, EmployeeProfileType.S)) {
            //TODO report
            return true;
        } else {
            return false;
        }
    }
    public boolean maintenance(Employee e)
    {
        if(e.auth(reader, EmployeeProfileType.T)) {
            //TODO maintenance
            return true;
        } else {
            return false;
        }
    }
    public boolean start(Employee e)
    {
        if(e.auth(reader, EmployeeProfileType.S)) {
            if (getState() == BaggageScannerState.SHUTDOWN) {
                setState(BaggageScannerState.DEACTIVATED);
                return true;
            }

        }
        return false;
    }
    public boolean shutdown(Employee e)
    {
        if(e.auth(reader, EmployeeProfileType.S)) {
            if (getState() != BaggageScannerState.LOCKED) {
                setState(BaggageScannerState.SHUTDOWN);
                return true;
            }

        }
        return false;
    }
    public boolean unlock(Employee e, Pin pin)
    {
        if(e.auth(reader, pin, EmployeeProfileType.S)) {
            if (getState() == BaggageScannerState.LOCKED) {
                setState(BaggageScannerState.ACTIVATED);
                //reset scan result
                setCurrentScanResult(new ScanResult(-1,-1, ProhibitedItem.CLEAN));
                return true;
            }
        }
        return false;
    }
    public boolean activate(Employee e, Pin pin)
    {
        if(getState() == BaggageScannerState.DEACTIVATED && e.auth(reader, pin, EmployeeProfileType.I))
        {
            setState(BaggageScannerState.ACTIVATED);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean scan(Employee e)
    {
        if(e.auth(reader, EmployeeProfileType.I)) {

            ScanResult result = null;
            if (getState() == BaggageScannerState.ACTIVATED && belt.getCurrent() != null) {
                setState(BaggageScannerState.INUSE);

                result = scanner.scan(belt.getCurrent().getHandBaggage(), scannerConfiguration);
                scanRecorder.logResult(result);

                forwardTrays(e);

                if (result.getPosition() != -1) { //positiv
                    outputOnTrack01(belt.removeOutgoingTray());
                } else { //negativ
                    outputOnTrack02(belt.removeOutgoingTray());
                }

                setState(BaggageScannerState.ACTIVATED);
                setCurrentScanResult(result);
                return true;
            }
            else
            {
                var i = 0;
            }
        }

        return false;
    }

    public ScanResult getCurrentScanResult() {
        return currentScanResult;
    }

    private void setCurrentScanResult(ScanResult result) {
        currentScanResult = result;
    }

    protected void outputOnTrack01(Tray tray)
    {
        track01.addTray(tray);
    }
    protected void outputOnTrack02(Tray tray)
    {
        track02.addTray(tray);
    }

    public boolean forwardTrays(Employee e) {
        if(e.auth(reader, EmployeeProfileType.I)) {
            belt.forwardTrays();
            return true;
        }
        return false;
    }
    public boolean backwardTrays(Employee e) {
        if(e.auth(reader, EmployeeProfileType.I)) {
            belt.backwardTrays();
            return true;
        }

        return false;
    }
    public void setOutgoingTray(Tray tray) {
       belt.setOutgoingTray(tray);
    }
    public void setIncomingTray(Tray tray) {
        belt.setIncomingTray(tray);
    }

}
