package Main.ext.search;

public class BruteForce implements IStringMatching {
    public static void main(String... args) {
        BruteForce bruteForce = new BruteForce();
        String text = "1ado3cqcsov53ufyqwf9m447nozdumn9n3xgti51mahpbfyeddzdd74gavjia1wm8q3npxrlmhx5jqrkfo569vbvw6uw1ywn7azvii2phlfp33v503r3e8mq7javab82dgtb2l1fq6iq68acjwdu4xh33vmq0ggqbf8eiudsh4b0i1aqbd6p5d37ki0mubh506";

        String pattern = "java";
        System.out.println("text    : " + text);
        System.out.println("pattern : " + pattern);
        int pos = bruteForce.search(text, pattern);

        if (pos == -1) {
            System.out.println("pattern not found");
        } else {
            System.out.println("pattern found at position : " + pos);
        }
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
