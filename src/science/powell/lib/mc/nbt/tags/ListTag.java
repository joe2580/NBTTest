package science.powell.lib.mc.nbt.tags;

import java.io.DataInputStream;
import java.io.PrintStream;

/**
 * Created by joe on 22/05/17.
 */

public class ListTag extends Tag {
    public Tag[] data;
    public byte type;

    public ListTag(String name, DataInputStream tagdata) throws Exception {
        this.name = name;
        this.type = tagdata.readByte();
        int size = tagdata.readInt();

        data = new Tag[size];

        for (int i = 0; i < size; i++) {
            data[i] = createTag(type, null, tagdata);
        }
    }

    public String toString() {
        return "[  list  ] " + name + " (" + data.length + ")";
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
        for (Tag tag: data) {
            tag.print(depth + 1 , output);
        }
    }
}
