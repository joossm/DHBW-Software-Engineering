package Main.Passenger;

public class Layer {


    private char[] content;

    public Layer(char[] content)
    {
        if(content.length != 10000)
        {
            throw new IllegalArgumentException();
        }
        setContent(content);
    }

    public char[] getContent()
    {
        return content;
    }
    public void setContent(char[] content)
    {
        this.content = content;
    }
    public void rewriteFromPos(int pos, char[] str) {
        System.arraycopy(str, 0, content, pos, pos + str.length - pos);
    }
    public void rewriteFromPos(int pos, String str) {
        rewriteFromPos(pos, str.toCharArray());
    }
}
