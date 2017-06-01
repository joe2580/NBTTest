package science.powell.lib.mc.nbt.tags;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by joe on 22/05/17.
 */
public class FloatTag extends Tag {
    public float data;

    public FloatTag(String name, DataInputStream tagdata) throws IOException {
        this.name = name;
        this.data = tagdata.readFloat();
    }

    public String toString() {
        return "[ float  ] " + name + " = " + data;
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
    }
}
