import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Commit {
    String summary, author, date, treeSHA1, previousSHA1, nextSHA1;

    public Commit(String summary, String author, String date, String treeSHA1) {
        this.summary = summary;
        this.author = author;
        this.date = date;
        this.treeSHA1 = treeSHA1;
    }

    void writeFile() {
        String fileText = treeSHA1 + "\n";
        if (previousSHA1 != null) {
            fileText += previousSHA1;
        }
        fileText += "\n";
        if (nextSHA1 != null) {
            fileText += nextSHA1;
        }
        fileText += "\n" + author + "\n" + date + "\n" + summary;
        File folder = new File("Objects");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String sha1 = calculateSHA1("" + summary + date + author + treeSHA1 + previousSHA1);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(sha1));
            bufferedWriter.write(fileText);
            bufferedWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String calculateSHA1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    String getDate() {
        return date;
    }

    void setPrevious(String previousSHA1) {
        this.previousSHA1 = previousSHA1;
    }

    void setNext(String nextSHA1) {
        this.nextSHA1 = nextSHA1;
    }
}