package science.powell.lib.mc.nbt.tags;

import java.io.DataInputStream;
import java.io.PrintStream;

/**
 * Created by joe on 22/05/17.
 */

public abstract class Tag {

    private static final byte TAG_End = 0;
    private static final byte TAG_Byte = 1;
    private static final byte TAG_Short = 2;
    private static final byte TAG_Int = 3;
    private static final byte TAG_Long = 4;
    private static final byte TAG_Float = 5;
    private static final byte TAG_Double = 6;
    private static final byte TAG_Byte_Array = 7;
    private static final byte TAG_String = 8;
    private static final byte TAG_List = 9;
    private static final byte TAG_Compound = 10;
    private static final byte TAG_Int_Array = 11;

    public String name;

    public static Tag readTag(DataInputStream tagdata) throws Exception {
        byte tagid = tagdata.readByte();
        if (tagid == 0) return new EndTag();

        int stringlen = tagdata.readUnsignedShort();
        byte[] stringbytes = new byte[stringlen];
        tagdata.readFully(stringbytes);
        String name = new String(stringbytes, "UTF-8");

        return createTag(tagid, name, tagdata);
    }

    static Tag createTag(byte tagid, String name, DataInputStream tagdata) throws Exception {
        switch (tagid) {
            case Tag.TAG_Byte:
                return new ByteTag(name, tagdata);
            case Tag.TAG_Short:
                return new ShortTag(name, tagdata);
            case Tag.TAG_Int:
                return new IntTag(name, tagdata);
            case Tag.TAG_Long:
                return new LongTag(name, tagdata);
            case Tag.TAG_Float:
                return new FloatTag(name, tagdata);
            case Tag.TAG_Double:
                return new DoubleTag(name, tagdata);
            case Tag.TAG_Byte_Array:
                return new ByteArrayTag(name, tagdata);
            case Tag.TAG_String:
                return new StringTag(name, tagdata);
            case Tag.TAG_List:
                return new ListTag(name, tagdata);
            case Tag.TAG_Compound:
                return new CompoundTag(name, tagdata);
            case Tag.TAG_Int_Array:
                return new IntArrayTag(name, tagdata);
            case Tag.TAG_End:
                throw new Exception("Tried to create an end tag in createTag()");
            default:
                throw new Exception("Unrecognised tag id of " + tagid);
        }
    }

    public String toString() {
        return "[generic ] Generic tag " + name ;
    }

    public abstract void print(int depth, PrintStream output);

    String indent(int count) {
        if (count > 0)
            return String.format("%"+2*count+"s", "");
        else
            return "";
    }


//    public abstract byte[] toByteArray();
}
