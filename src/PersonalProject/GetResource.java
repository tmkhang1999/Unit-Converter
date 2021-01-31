//package PersonalProject;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.imageio.ImageIO;
//import javax.swing.SwingWorker;
//
//public class GetResource extends SwingWorker<List<Flag>, Integer> {
//    private static final String file = "images";
//    private final Frame frame;
//    private File[] files;
//    private List<Flag> flags;
//
//    public GetResource(Frame frame)
//    {
//        this.frame = frame;
//    }
//
//    @Override
//    protected List<Flag> doInBackground() {
//        File resource = new File(file);
//        flags = new ArrayList<>();
//        files = resource.listFiles();
//
//        for(File f : files) {
//            String name = f.getName();
//            try {
//                flags.add(new Flag(name.replaceAll("\\..*", ""), ImageIO.read(f)));
//            }
//            catch(IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return flags;
//    }
//
//    @Override
//    public void done() {
//        System.out.println("Loading is DONE!");
//        for(Flag f : flags)
//            frame.addFlag(f);
//        frame.GUI();
//    }
//
//}
package PersonalProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingWorker;

public class GetResource extends SwingWorker<Map<String, Double>, Integer> {
    private final Frame frame;
    private Map<String, Double> data;

    public GetResource(Frame frame)
    {
        this.frame = frame;
    }

    @Override
    protected Map<String, Double> doInBackground() {
        data = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(frame.getPath()+".csv"));
            reader.readLine(); // Skip the first line (Title)
            String line = reader.readLine();
            int i =0;
            while (line != null) {
                String[] attributes = line.split(",");
                data.put(attributes[0], Double.valueOf(attributes[1]));
                // read next line
                line = reader.readLine();
                i++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void done() {
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            frame.addCurrency(entry.getKey(), entry.getValue());
        }
        frame.GUI();
    }

}