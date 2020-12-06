package Main.ext.search;

public class KnuthMorrisPratt implements IStringMatching{
    public static void main(String... args) {
        KnuthMorrisPratt knuthMorrisPratt = new KnuthMorrisPratt();
        String text = "1ado3cqcsov53ufyqwf9m447nozdumn9n3xgti51mahpbfyeddzdd74gavjia1wm8q3npxrlmhx5jqrkfo569vbvw6uw1ywn7azvii2phlfp33v503r3e8mq7javab82dgtb2l1fq6iq68acjwdu4xh33vmq0ggqbf8eiudsh4b0i1aqbd6p5d37ki0mubh506";

        String pattern = "java";
        System.out.println("text    : " + text);
        System.out.println("pattern : " + pattern);

        int position = knuthMorrisPratt.search(text, pattern);

        if (position == -1) {
            System.out.println("pattern not found");
        } else {
            System.out.println("pattern found at position : " + position);
        }
    }

    public int search(String text, String pattern)
    {
        int x = text.length();
        int y = pattern.length();

        int[] failure = computeFail(pattern);
        int z = 0;
        int j = 0;

        while (z < x)
        {
            if (pattern.charAt(j) == text.charAt(z))
            {
                if (j == y - 1) {
                    return z - y + 1; // match
                }
                z++;
                j++;
            }
            else if (j > 0)
            {
                j = failure[j - 1];
            }
            else
            {
                z++;
            }
        }

        return -1;
    }

    public int[] computeFail(String pattern)
    {
        int[] failure = new int[pattern.length()];
        failure[0] = 0;
        int m = pattern.length();
        int j = 0;
        int z = 1;

        while (z < m) {
            if (pattern.charAt(j) == pattern.charAt(z)) {
                failure[z] = j + 1;
                z++;
                j++;
            } else if (j > 0) {
                j = failure[j - 1];
            } else {
                failure[z] = 0;
                z++;
            }
        }
        return failure;
    }
}
