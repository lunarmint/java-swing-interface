package hw05;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BackgroundLoader extends SwingWorker<List<Flag>, Integer> {

    private final Frame frame;

    protected List<Flag> doInBackground() throws Exception {
        Path path = Paths.get("256x192");
        File folder = new File(String.valueOf(path));
        File[] files = folder.listFiles();
        List<Flag> flags = new ArrayList<>();

        if(!folder.exists()) {
            System.err.println("Directory '" + path + "' does not exist.");
            System.exit(1);
        }

        assert files != null;
        int flagCount = files.length;
        for(File f : files) {
            final String name = f.getName();
            flags.add(new Flag(name.replaceAll(".png", ""), ImageIO.read(f)));
            publish((int)((flags.size() * 100.0f) / flagCount));
        }
        return flags;
    }

    public void done() {
        try {
            List<Flag> flags = get();
            for(Flag f : flags) {
                frame.addFlag(f.toString(), f);
            }
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public BackgroundLoader(Frame frame) {
        this.frame = frame;
    }
}
