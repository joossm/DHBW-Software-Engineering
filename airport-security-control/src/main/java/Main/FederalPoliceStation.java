package Main;

import Main.Employee.FederalPoliceOfficer;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FederalPoliceStation {

    Queue<FederalPoliceOfficer> officers;
    List<Robot> robots;

    public FederalPoliceStation() {
        robots = new ArrayList<>(Arrays.asList(new Robot(), new Robot(), new Robot()));
        officers = new LinkedList<>();
    }

    public FederalPoliceOfficer getBackup() {

        return officers.poll();
    }

    public void addOfficer(FederalPoliceOfficer officer) {
        officers.add(officer);
    }

    public Robot getRoboter() {
        final int min = 0;
        final int max = 2;
        final var i = ThreadLocalRandom.current().nextInt(min, max + 1);

        return robots.get(i);
    }
}
