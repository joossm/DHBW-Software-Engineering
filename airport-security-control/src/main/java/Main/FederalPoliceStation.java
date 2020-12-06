package Main;

import Main.Employee.FederalPoliceOfficer;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FederalPoliceStation {

    Queue<FederalPoliceOfficer> officers;
    List<Roboter> roboters;

    public FederalPoliceStation() {
        roboters = new ArrayList<>(Arrays.asList(new Roboter(),new Roboter(), new Roboter()));
        officers = new LinkedList<>();
    }

    public FederalPoliceOfficer getBackup() {

        return officers.poll();
    }

    public void addOfficer(FederalPoliceOfficer officer) {
        officers.add(officer);
    }


    public Roboter getRoboter() {

        final int min = 0;
        final int max = 2;
        final var i = ThreadLocalRandom.current().nextInt(min, max + 1);

        return roboters.get(i);
    }
}
