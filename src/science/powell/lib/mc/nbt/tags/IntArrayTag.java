package science.powell.lib.mc.nbt.tags;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by joe on 22/05/17.
 */
public class IntArrayTag extends Tag {
    public int[] data;

    public IntArrayTag(String name, DataInputStream tagdata) throws IOException {
        this.name = name;
        int length = tagdata.readInt();
        this.data = new int[length];
        for(int i = 0; i < length; i++)
            data[i] = tagdata.readInt();
    }

    public String toString() {
        return "[int  arr] " + name + " (" + data.length + ")";
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
    }
}
