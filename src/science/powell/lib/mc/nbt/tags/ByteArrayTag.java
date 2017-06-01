package science.powell.lib.mc.nbt.tags;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by joe on 22/05/17.
 */

public class ByteArrayTag extends Tag {

    public byte[] data;

    public ByteArrayTag(String name, DataInputStream tagdata) throws IOException {
        this.name = name;
        int length = tagdata.readInt();
        this.data = new byte[length];
        tagdata.readFully(this.data);
    }

    public String toString() {
        return "[byte arr] " + name + " (" + data.length + ")";
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
    }
}
