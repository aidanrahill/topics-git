
public class Main {
    public static void main(String[] args) {
        Blob blob = new Blob("example.txt");
        System.out.println("Blob SHA1: " + blob.getSHA1());

        Index index = new Index();
        index.initialize();
        index.addBlob("example.txt", blob.getSHA1());

        // To remove a blob from the index:
         index.removeBlob("example.txt");
    }
}
