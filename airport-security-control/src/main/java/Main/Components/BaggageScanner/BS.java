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
import Main.Employee.Employee;
import Main.Employee.EmployeeProfileType;
import Main.Employee.Pin;

public class BS {

    private BSStatus state = BSStatus.SHUTDOWN;

    private final Reader reader;
    private final Scanner scanner;
    private final ScanRecorder scanRecorder;
    private final Track track01;
    private final Track track02;
    private final Belt belt;
    private ScanResult currentScanResult;
    private Configuration scannerConfiguration;

    public BS(Reader reader, Scanner scanner, ScanRecorder scanRecorder, Track track01, Track track02, Belt belt, Configuration scannerConfiguration) {
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

    public BSStatus getState() {
        return state;
    }

    protected void setState(BSStatus state) {
        this.state = state;
    }

    public Configuration getScannerConfiguration() {
        return scannerConfiguration;
    }

    public void setScannerConfiguration(Configuration scannerConfiguration) {
        this.scannerConfiguration = scannerConfiguration;
    }

    public boolean alarm(Employee e) {
        if (e.auth(reader, EmployeeProfileType.I))
            if (getState() != BSStatus.DEACTIVATED && getState() != BSStatus.SHUTDOWN) {
                setState(BSStatus.LOCKED);
                return true;
            }

        return false;
    }

    public boolean report(Employee e) {
        return e.auth(reader, EmployeeProfileType.S);
    }

    public void maintenance(Employee e) {
        e.auth(reader, EmployeeProfileType.T);
    }

    public boolean start(Employee e) {
        if (e.auth(reader, EmployeeProfileType.S)) {
            if (getState() == BSStatus.SHUTDOWN) {
                setState(BSStatus.DEACTIVATED);
                return true;
            }

        }
        return false;
    }

    public boolean shutdown(Employee e) {
        if (e.auth(reader, EmployeeProfileType.S)) {
            if (getState() != BSStatus.LOCKED) {
                setState(BSStatus.SHUTDOWN);
                return true;
            }

        }
        return false;
    }

    public boolean unlock(Employee e, Pin pin) {
        if (e.auth(reader, pin, EmployeeProfileType.S)) {
            if (getState() == BSStatus.LOCKED) {
                setState(BSStatus.ACTIVATED);
                setCurrentScanResult(new ScanResult(-1, -1, ProhibitedItem.CLEAN));
                return true;
            }
        }
        return false;
    }

    public boolean activate(Employee e, Pin pin) {
        if (getState() == BSStatus.DEACTIVATED && e.auth(reader, pin, EmployeeProfileType.I)) {
            setState(BSStatus.ACTIVATED);
            return true;
        } else {
            return false;
        }
    }

    public boolean scan(Employee e) {
        if (e.auth(reader, EmployeeProfileType.I)) {

            ScanResult result = null;
            if (getState() == BSStatus.ACTIVATED && belt.getCurrent() != null) {
                setState(BSStatus.INUSE);

                result = scanner.scan(belt.getCurrent().getHandBaggage(), scannerConfiguration);
                scanRecorder.logResult(result);

                forwardTrays(e);

                if (result.getPosition() != -1) {
                    outputOnTrack01(belt.removeOutgoingTray());
                } else {
                    outputOnTrack02(belt.removeOutgoingTray());
                }

                setState(BSStatus.ACTIVATED);
                setCurrentScanResult(result);
                return true;
            } else {
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

    protected void outputOnTrack01(Tray tray) {
        track01.addTray(tray);
    }

    protected void outputOnTrack02(Tray tray) {
        track02.addTray(tray);
    }

    public boolean forwardTrays(Employee e) {
        if (e.auth(reader, EmployeeProfileType.I)) {
            belt.forwardTrays();
            return true;
        }
        return false;
    }

    public boolean backwardTrays(Employee e) {
        if (e.auth(reader, EmployeeProfileType.I)) {
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