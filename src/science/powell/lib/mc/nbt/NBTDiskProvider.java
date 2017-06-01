package science.powell.lib.mc.nbt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * Created by joe on 22/05/17.
 */

public class NBTDiskProvider {

    Path root;

    public NBTDiskProvider(Path root) throws NotDirectoryException {
        this.root = root.toAbsolutePath();
        if (!Files.isDirectory(this.root)) {
            throw new NotDirectoryException(this.root.toString() + " is not a valid folder");
        }
    }

    DataInputStream getFileStream(String name) throws IOException {
        return new DataInputStream(new FileInputStream(getFile(name)));
    }

    DataInputStream getGZIPFileStream(String name) throws IOException {
        return new DataInputStream(new GZIPInputStream(new FileInputStream(getFile(name))));
    }

    DataInputStream getInflatedFileStream(String name) throws IOException {
        return new DataInputStream(new InflaterInputStream(new FileInputStream(getFile(name))));
    }

    byte[] getFileBytes(String name) throws IOException {
        return Files.readAllBytes(getFile(name).toPath());
    }

    File getFile(String name) throws FileNotFoundException {
        File file = new File(root.toAbsolutePath().toString(), name);
        if (file.isFile())
            return file;
        else
            throw new FileNotFoundException(name + " was not found in " + root);
    }
}
