package science.powell.lib.mc.nbt.tags;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by joe on 26/05/17.
 */

public class StringTag extends Tag {
    public String data;

    public StringTag(String name, DataInputStream tagdata) throws IOException {
        this.name = name;
        int stringlen = tagdata.readUnsignedShort();
        byte[] stringdata = new byte[stringlen];
        tagdata.readFully(stringdata);
        data = new String(stringdata, "UTF-8");
    }

    public String toString() {
        return "[ string ] " + name + " = " + data;
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
    }
}
