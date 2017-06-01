package science.powell.lib.mc.nbt.tags;

import java.io.PrintStream;

/**
 * Created by joe on 22/05/17.
 *
 * End tag is only present to finish a compound tag, it should never do anything
 */
public class EndTag extends Tag {

    public String toString() {
        return "[  END!  ]";
    }

    public void print(int depth, PrintStream output) {
        output.println(indent(depth) + toString());
    }
}
