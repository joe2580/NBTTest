package science.powell.lib.mc.nbt.tags;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by joe on 22/05/17.
 */
public class ByteTag extends Tag {
    public byte data;

    public ByteTag(String name, DataInputStream tagdata) throws IOException {
        this.name = name;
        this.data = tagdata.readByte();
    }

    public String toString() {
        return "[  byte  ] " + name + " = " + data;
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
    }
}
