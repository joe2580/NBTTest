package science.powell.lib.mc.nbt.tags;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by joe on 22/05/17.
 */
public class IntTag extends Tag {
    public int data;

    public IntTag(String name, DataInputStream tagdata) throws IOException {
        this.name = name;
        this.data = tagdata.readInt();
    }

    public String toString() {
        return "[  int   ] " + name + " = " + data;
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
    }
}
