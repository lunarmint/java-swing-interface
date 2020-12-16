package hw04;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.imageio.ImageIO;
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

    protected void process(List<Integer> chunks) {
        frame.setProgress(chunks.get(chunks.size() - 1));
    }

    public void done() {
        try {
            List<Flag> flags = get();
            for(Flag f : flags) {
                frame.addFlag(f.toString(), f);
            }
            frame.display();
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public BackgroundLoader(Frame frame) {
        this.frame = frame;
    }
}
