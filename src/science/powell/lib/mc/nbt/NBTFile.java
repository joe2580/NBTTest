package science.powell.lib.mc.nbt;

import science.powell.lib.mc.nbt.tags.CompoundTag;
import science.powell.lib.mc.nbt.tags.Tag;

import java.io.DataInputStream;
import java.io.PrintStream;

/**
  * Created by joe on 22/05/17.
 */

public class NBTFile {

    private CompoundTag root;
    DataInputStream nbtdata;

    public NBTFile(DataInputStream nbtdata) throws Exception {
        this.nbtdata = nbtdata;

        Tag basetag = Tag.readTag(nbtdata);
        if (!(basetag instanceof CompoundTag)) {
            throw new Exception("Base tag is not a compound tag");
        }
        root = (CompoundTag) basetag;
        nbtdata.close();
    }

    public String toString() {
        return root.toString();
    }

    public CompoundTag getRoot() {
        return root;
    }

    public void print(PrintStream output) {
        root.print(0, output);
    }
}
