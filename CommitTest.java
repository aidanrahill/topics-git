import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CommitTest {
    private Commit commit;

    // i didnt test sha1 because i copied that from tree, so its already tested
    @Before
    public void setUp() {
        commit = new Commit("Summary", "Author", "2023-09-26", "TreeSHA1");
    }

    @Test
    public void testGetDate() {
        assertEquals("2023-09-26", commit.getDate());
    }

    @Test
    public void testSetPrevious() {
        commit.setPrevious("PreviousSHA1");
        assertEquals("PreviousSHA1", commit.previousSHA1);
    }

    @Test
    public void testSetNext() {
        commit.setNext("NextSHA1");
        assertEquals("NextSHA1", commit.nextSHA1);
    }

    @Test
    public void testWriteFile() {
        commit.writeFile();
    }
}
