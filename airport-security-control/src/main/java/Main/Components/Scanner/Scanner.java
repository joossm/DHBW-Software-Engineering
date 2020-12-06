package Main.Components.Scanner;

import Main.Passenger.Baggage;
import Main.ext.search.BoyerMoore;
import Main.ext.search.IStringMatching;
import Main.ext.search.KnuthMorrisPratt;

public class Scanner {



    public ScanResult scan(Baggage baggage, Configuration configuration) throws IllegalArgumentException {
        ScanResult result = null;

        var counter = 0;

        for (var l : baggage.getLayers()) {
            var content = l.getContent();

            IStringMatching algo;

            switch (configuration.getUsedAlgorithm())
            {
                case boyerMoore: {
                    algo = new BoyerMoore();
                    break;
                }
                case knuthMorrisPratt: {
                    algo = new KnuthMorrisPratt();
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }

            final String knife = "kn!fe";
            final String weapon = "glock|7";
            final String explosive = "exp|os!ve";

            int pos;

            if((pos = algo.search(String.valueOf(content), knife)) >= 0) { result = new ScanResult(counter,pos, ProhibitedItem.KNIFE); }
            else if((pos = algo.search(String.valueOf(content), weapon)) >= 0)  { result = new ScanResult(counter, pos, ProhibitedItem.WEAPON_GLOCK7); }
            else if((pos = algo.search(String.valueOf(content), explosive)) >= 0)  { result = new ScanResult(counter, pos, ProhibitedItem.EXPLOSIVE); }

            counter++;

            if(result != null)
                continue;

        }

        if(result == null)
            result = new ScanResult(-1,-1,ProhibitedItem.CLEAN);

        return result;
    }
}
