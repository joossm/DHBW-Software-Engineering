package Main.ext.search;

public class BoyerMoore implements IStringMatching {
    public static void main(String... args) {
        BoyerMoore boyerMoore = new BoyerMoore();
        String text = "szdsrztatwazesszetwazsrzdusz5t";

        String pattern = "java";

        boyerMoore.search(text, pattern);

    }

    public int search(String text, String pattern)
    {
        int[] last = buildLast(pattern);
        int x = text.length();
        int y = pattern.length();
        int z = y - 1;

        if (z > x - 1) {
            return -1;
        }

        int j = y - 1;

        do {
            if (pattern.charAt(j) == text.charAt(z))
            {
                if (j == 0)
                {
                    return z;
                }
                else
                {
                    z--;
                    j--;
                }
            }
            else
            {
                int lo = last[text.charAt(z)];
                z = z + y - Math.min(j, 1 + lo);
                j = y - 1;
            }
        } while (z <= x - 1);

        return -1;
    }

    public int[] buildLast(String pattern)
    {
        int[] last = new int[128];

        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }

        for (int i = 0; i < pattern.length(); i++) {
            last[pattern.charAt(i)] = i;
        }

        return last;
    }
}
