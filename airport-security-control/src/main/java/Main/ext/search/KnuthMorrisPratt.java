package Main.ext.search;

public class KnuthMorrisPratt implements IStringMatching {
    public static void main(String... args) {
        KnuthMorrisPratt knuthMorrisPratt = new KnuthMorrisPratt();
        String text = "szdsrztatwazesszetwazsrzdusz5t";
        String pattern = "java";
        knuthMorrisPratt.search(text, pattern);

    }

    public int search(String text, String pattern) {
        int x = text.length();
        int y = pattern.length();

        int[] fail = computeFail(pattern);
        int z = 0;
        int j = 0;

        while (z < x) {
            if (pattern.charAt(j) == text.charAt(z)) {
                if (j == y - 1) {
                    return z - y + 1;
                }
                z++;
                j++;
            } else if (j > 0) {
                j = fail[j - 1];
            } else {
                z++;
            }
        }

        return -1;
    }

    public int[] computeFail(String pattern) {
        int[] fail = new int[pattern.length()];
        fail[0] = 0;
        int m = pattern.length();
        int j = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(j) == pattern.charAt(i)) {
                fail[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = fail[j - 1];
            } else {
                fail[i] = 0;
                i++;
            }
        }

        return fail;
    }
}
