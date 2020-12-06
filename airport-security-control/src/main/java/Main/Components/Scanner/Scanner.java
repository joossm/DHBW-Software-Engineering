package Main.Components.Scanner;

import Main.Passanger.HandBaggage;
import Main.ext.text_search.BoyerMoore;
import Main.ext.text_search.IStringMatching;
import Main.ext.text_search.KnuthMorrisPratt;

public class Scanner {



    public ScanResult scan(HandBaggage baggage, Configuration configuration)
    {
        ScanResult result = null;

        var counter = 0;

        for (var l : baggage.getLayers()) {
            var content = l.getContent();

            IStringMatching algo;

            switch (configuration.getUsedAlgorithm())
            {
                case boyerMoore -> algo = new BoyerMoore();
                case knuthMorrisPratt -> algo = new KnuthMorrisPratt();
                default -> throw new IllegalArgumentException();
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
