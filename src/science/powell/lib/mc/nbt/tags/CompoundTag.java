package science.powell.lib.mc.nbt.tags;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.util.HashMap;

/**
 * Created by joe on 22/05/17.
 */
public class CompoundTag extends Tag {
    public HashMap<String, Tag> data = new HashMap<>();

    public CompoundTag(String name, DataInputStream tagdata) throws Exception {
        this.name = name;

        while (true) {
            Tag child = readTag(tagdata);
            if (child instanceof EndTag) break;
            data.put(child.name, child);

        }
    }

    public String toString() {
        return "[compound] " + name + " (" + data.size() + ")";
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
        for (Tag tag: data.values()) {
            tag.print(depth + 1 , output);
        }
    }
}
