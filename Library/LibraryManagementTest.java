import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LibraryManagementTest {

    private Library library;

    @Before
    public void setUp() {
        library = new Library();
        
    }

    @Test
    public void testValidLowerBoundaryBookId() throws Exception {
        Book validBook100 = new Book(100, "Min book ID");
        assertTrue(validBook100.isValidId(100));
    }

    @Test
    public void testValidUpperBoundaryBookId() throws Exception {
        Book validBook999 = new Book(999, "Max book ID");
        assertTrue(validBook999.isValidId(999));
    }

    @Test
    public void testInvalidBelowBoundaryBookId() {
        try {
            new Book(99, "Invalid ID below 100");
            fail("ID below 100");
        } catch (Exception e) {
            assertEquals("Invalid book ID", e.getMessage());
        }
    }

    @Test
    public void testInvalidAboveBoundaryBookId() {
        try {
            new Book(1000, "Invalid ID above 999");
            fail("ID above 999");
        } catch (Exception e) {
            assertEquals("Invalid book ID", e.getMessage());
        }
    }
}