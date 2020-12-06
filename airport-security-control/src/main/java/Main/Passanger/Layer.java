package Main.Passanger;

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
        for(var i = pos; i < pos + str.length; i++) {
            content[i] = str[i - pos];
        }
    }
    public void rewriteFromPos(int pos, String str) {
        rewriteFromPos(pos, str.toCharArray());
    }
}
