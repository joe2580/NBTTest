package science.powell.lib.mc.nbt;

import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by joe on 22/05/17.
 *
 * Runs the parser
 */

public class NBTTest {

    public static void main(String[] args) {

        try {

            NBTDiskProvider prov = new NBTDiskProvider(Paths.get("test"));

            NBTFile level = new NBTFile(prov.getGZIPFileStream("level.dat"));
            NBTFile bigtest = new NBTFile(prov.getGZIPFileStream("bigtest.nbt"));
            level.print(System.out);
            bigtest.print(System.out);

            ArrayList<MCRFile> regions = new ArrayList<>();

            regions.add(new MCRFile(prov.getFileStream("region/r.0.0.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-1.0.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.1.0.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-1.-1.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.1.-1.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-1.-2.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-1.-3.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-1.-4.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.2.0.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-2.-1.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.2.-1.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-2.-2.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-2.-3.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-2.-4.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-3.-2.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-3.-3.mca")));
            regions.add(new MCRFile(prov.getFileStream("region/r.-3.-4.mca")));

            for(MCRFile file : regions)
                file.print(System.out);


        } catch (Exception e) {
            System.err.println("Failed to do thing");
            e.printStackTrace();
        }

    }

}
