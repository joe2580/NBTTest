package science.powell.lib.mc.nbt;

import science.powell.lib.mc.nbt.tags.CompoundTag;
import science.powell.lib.mc.nbt.tags.Tag;

import java.io.DataInputStream;
import java.io.PrintStream;

/**
 * Created by joe on 01/06/17.
 */
public class Chunk implements Comparable<Chunk> {
    public int location, size;
    public int x, z;
    public int timestamp;
    private CompoundTag root;

    public Chunk(int location, int size, int x, int z, int timestamp) {
        this.location = location;
        this.size = size;
        this.x = x;
        this.z = z;
        this.timestamp = timestamp;
    }

    public void load(DataInputStream nbtdata) throws Exception {
        Tag basetag = Tag.readTag(nbtdata);
        if (!(basetag instanceof CompoundTag)) {
            throw new Exception("Base tag is not a compound tag");
        }
        root = (CompoundTag) basetag;
        nbtdata.close();
    }

    public String toString() {
        return "[ chunk ] (" + x + ", " + z + ") location = " + location + ", size = " + size;
    }

    public CompoundTag getRoot() {
        return root;
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
        root.print(depth + 1, output);
    }

    @Override
    public int compareTo(Chunk o) {
        return location - o.location;
    }

    String indent(int count) {
        if (count > 0)
            return String.format("%"+2*count+"s", "");
        else
            return "";
    }
}
