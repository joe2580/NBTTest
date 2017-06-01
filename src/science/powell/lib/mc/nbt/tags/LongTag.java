package science.powell.lib.mc.nbt.tags;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by joe on 22/05/17.
 */
public class LongTag extends Tag {
    public long data;

    public LongTag(String name, DataInputStream tagdata) throws IOException {
        this.name = name;
        this.data = tagdata.readLong();
    }

    public String toString() {
        return "[  long  ] " + name + " = " + data;
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
    }

}
