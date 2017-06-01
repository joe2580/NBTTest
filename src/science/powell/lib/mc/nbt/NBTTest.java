package science.powell.lib.mc.nbt;

import java.nio.file.Paths;
import java.util.LinkedList;

/**
 * Created by joe on 22/05/17.
 *
 * Runs the parser
 */

public class NBTTest {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Please provide an nbt folder and file name");
            return;
        }

        try {

            NBTDiskProvider prov = new NBTDiskProvider(Paths.get(System.getProperty("user.dir") + "/" + args[0]));

            LinkedList<NBTFile> nbtfiles = new LinkedList<>();

            nbtfiles.add(new NBTFile(prov.getGZIPFileStream("level.dat")));
            nbtfiles.add(new NBTFile(prov.getGZIPFileStream("bigtest.nbt")));

            for (NBTFile file : nbtfiles)
                file.print(System.out);

        } catch (Exception e) {
            System.err.println("Failed to do thing");
            e.printStackTrace();
        }

    }

}
