package Main;

import Main.ext.text_search.BruteForce;
import Main.Components.Scanner.ScanResult;
import Main.Components.Scanner.ProhibitedItem;



public class ExplosivesTraceDetector
{
    public ScanResult test(TestStripe stripe)
    {
        var b = new BruteForce();

        for(var x = 0 ; x < stripe.getData().length; x++)
        {
            var result = b.search(stripe.getData()[x].toString(), "exp");

            if(result != -1) {
                return new ScanResult(x,result, ProhibitedItem.EXPLOSIVE);
            }
        }

        return new ScanResult(-1,-1,ProhibitedItem.CLEAN);
    }
}
