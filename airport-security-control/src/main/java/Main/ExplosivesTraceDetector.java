package Main;

import Main.Components.Scanner.ProhibitedItem;
import Main.Components.Scanner.ScanResult;
import Main.ext.Search.BruteForce;

import java.util.Arrays;


public class ExplosivesTraceDetector {
    public void test(TestStripe stripe) {
        var bf = new BruteForce();
        for (var x = 0; x < stripe.getData().length; x++) {
            var result = bf.search(Arrays.toString(stripe.getData()[x]), "exp");
            if (result != -1) {
                new ScanResult(x, result, ProhibitedItem.EXPLOSIVE);
                return;
            }
        }
        new ScanResult(-1, -1, ProhibitedItem.CLEAN);
    }
}
