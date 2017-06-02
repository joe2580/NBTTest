package science.powell.lib.mc.nbt;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
  * Created by joe on 22/05/17.
 */

public class MCRFile {

    private static final int COMPRESSION_NONE = 0;
    private static final int COMPRESSION_GZIP = 1;
    private static final int COMPRESSION_INFLATE = 2;

    private static final int XWIDTH = 32;
    private static final int ZWIDTH = 32;
    private static final int SECTORSIZE = 4096;

    private Chunk[][] data = new Chunk[XWIDTH][ZWIDTH];
    int count = 0;

    public MCRFile(DataInputStream mcrdata) throws Exception {

        PriorityQueue<Chunk> locations = new PriorityQueue<>();

        for (int z = 0; z < ZWIDTH; z++) {
            for (int x = 0; x < XWIDTH; x++) {
                int location = mcrdata.readUnsignedByte() << 16;
                location |= mcrdata.readUnsignedByte() << 8;
                location |= mcrdata.readUnsignedByte();
                int size = mcrdata.readUnsignedByte();
                if (location != 0) {
                    count++;
                    data[x][z] = new Chunk(location, size, x, z, 0);
                    locations.add(data[x][z]);
                }
            }
        }

        for (int z = 0; z < ZWIDTH; z++)
            for (int x = 0; x < XWIDTH; x++)
                if (data[x][z] == null)
                    mcrdata.readInt();
                else
                    data[x][z].timestamp = mcrdata.readInt();

        int read = 2;
        while(!locations.isEmpty()) {
            Chunk toLoad = locations.remove();
            if (read != toLoad.location)
                mcrdata.skipBytes((toLoad.location - read) * SECTORSIZE);
            int length = mcrdata.readInt();
            int compression = mcrdata.readUnsignedByte();
            byte[] chunkdata = new byte[length];
            mcrdata.readFully(chunkdata);
            mcrdata.skipBytes(SECTORSIZE * toLoad.size - length - 5);
            switch (compression) {
                case COMPRESSION_NONE:
                    toLoad.load(new DataInputStream(new ByteArrayInputStream(chunkdata)));
                    break;
                case COMPRESSION_GZIP:
                    toLoad.load(new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(chunkdata))));
                    break;
                case COMPRESSION_INFLATE:
                    toLoad.load(new DataInputStream(new InflaterInputStream(new ByteArrayInputStream(chunkdata))));
            }
            read = toLoad.location + toLoad.size;
        }
        mcrdata.close();
    }

    public String toString() {
        return "[mcrfile ] " + count + " chunks";
    }

    public void print(PrintStream output) {
        for (int z = 0; z < ZWIDTH; z++) {
            for (int x = 0; x < XWIDTH; x++) {
                if (data[x][z] != null) {
                    output.println(toString());
                    data[x][z].print(1, output);
                }
            }
        }
    }


}
