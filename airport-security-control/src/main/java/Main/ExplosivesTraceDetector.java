package Main;

import Main.Components.Scanner.ProhibitedItem;
import Main.Components.Scanner.ScanResult;
import Main.ext.text_search.BruteForce;

import java.util.Arrays;


public class ExplosivesTraceDetector {
    public ScanResult test(TestStripe stripe) {
        var b = new BruteForce();

        for (var x = 0; x < stripe.getData().length; x++) {
            var result = b.search(Arrays.toString(stripe.getData()[x]), "exp");

            if (result != -1) {
                return new ScanResult(x, result, ProhibitedItem.EXPLOSIVE);
            }
        }

        return new ScanResult(-1, -1, ProhibitedItem.CLEAN);
    }
}
