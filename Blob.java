import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class Blob {
    private String sha1;
    private String filePath;

    public Blob(String filePath) {
        this.filePath = filePath;
        this.sha1 = generateSHA1();
        writeInObjects();
    }

    //copied from internet
    private String generateSHA1() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] fileBytes = Files.readAllBytes(new File(filePath).toPath());
            md.update(fileBytes);
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeInObjects() {
        String path = "./objects/" + filePath;
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(sha1);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("didn't work");
        }
    }

    public String getSHA1() {
        return sha1;
    }
}