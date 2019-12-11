package IO;

import java.io.*;

public class InputOutput {
    private final StreamTokenizer in;
    private PrintWriter out;

    public InputOutput() {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        out = new PrintWriter(System.out);
    }

    public int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public void write(int output) {
        out.println(output);
        out.flush();
    }

}
