import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TreeTester {
    private Tree tree;

    @Before
    public void setUp() {
        tree = new Tree();
    }

    @Test
    public void testAdd() {
        // Test adding an entry to the tree
        tree.add("entry1");
        assertTrue(tree.entries.contains("entry1"));
    }

    @Test
    public void testRemove() {
        // Test removing an entry from the tree
        tree.add("entry1");
        tree.add("entry2");
        tree.remove("entry1");
        assertFalse(tree.entries.contains("entry1"));
        assertTrue(tree.entries.contains("entry2"));
    }

    @Test
    public void testGenerateBlob() {
        // Test generating a blob and checking if it exists
        tree.add("entry1");
        tree.generateBlob();
        assertTrue(new File("objects").exists()); // Check if objects folder exists
        String sha1 = tree.calculateSHA1("entry1\n");
        String blobFileName = "objects" + File.separator + sha1;
        assertTrue(new File(blobFileName).exists()); // Check if blob file exists
    }

    // You can write more test methods to cover other functionalities as needed

    @Test
    public void testDelete() {
        // Test deleting an entry from the tree
        tree.add("entry1");
        tree.add("entry2");
        tree.delete("entry1");
        assertFalse(tree.entries.contains("entry1"));
        assertTrue(tree.entries.contains("entry2"));
    }

    @Test
    public void testAddDirectoryBasic() throws IOException {
        String testDirectory = "superMarioBrosWii";

        assertTrue(tree.entries.contains("blob : file1.txt " + testDirectory + File.separator + "file1.txt"));
        assertTrue(tree.entries.contains("blob : file2.txt " + testDirectory + File.separator + "file2.txt"));
        assertTrue(tree.entries.contains("blob : file3.txt " + testDirectory + File.separator + "file3.txt"));
    }

    @Test
    public void testAddDirectoryAdvanced() throws IOException {
        String testDirectory = "superMarioBrosWiiU";
        String innerFilePath = testDirectory + File.separator + "folder1" + File.separator + "innerFile.txt";

        String sha1 = tree.addDirectory(testDirectory);

        assertTrue(tree.entries.contains("blov : file1.txt " + testDirectory + File.separator + "file1.txt"));
        assertTrue(tree.entries.contains("blob : file2.txt " + testDirectory + File.separator + "file2.txt"));
        assertTrue(tree.entries.contains("blob : file3.txt " + testDirectory + File.separator + "file3.txt"));
        assertTrue(tree.entries.contains("blob : " + innerFilePath + " " + innerFilePath));
        assertTrue(tree.entries.contains("tree : " + sha1 + " " + testDirectory + File.separator + "folder1"));
        assertTrue(tree.entries.contains("tree : " + sha1 + " " + testDirectory + File.separator + "folder2"));
    }
}
