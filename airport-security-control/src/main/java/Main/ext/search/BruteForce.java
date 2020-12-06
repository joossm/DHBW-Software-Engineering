package Main.ext.search;

public class BruteForce implements IStringMatching {
    public static void main(String... args) {
        BruteForce bruteForce = new BruteForce();
        String text = "szdsrztatwazesszetwazsrzdusz5t";
        String pattern = "java";
        bruteForce.search(text, pattern);


    }

    public int search(String text, String pattern)
    {
        int x = text.length();
        int y = pattern.length();
        int j;

        for (int z = 0; z <= (x - y); z++)
        {
            j = 0;

            while ((j < y) && (text.charAt(z + j) == pattern.charAt(j)))
            {
                j++;
            }

            if (j == y)
            {
                return z;
            }
        }
        return -1;
    }
}
