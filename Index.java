import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Index {
    private Map<String, String> indexData;

    public Index() {
        indexData = new HashMap<>();
    }

    public void addBlob(String fileName, String sha1) {
        indexData.put(fileName, sha1);
        saveIndexToFile();
    }

    public void removeBlob(String fileName) {
        indexData.remove(fileName);
        saveIndexToFile();
    }

    private void saveIndexToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("index"));
            for (Map.Entry<String, String> entry : indexData.entrySet()) {
                String line = entry.getKey() + " : " + entry.getValue() + "\n";
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        try {
            new File("index").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
