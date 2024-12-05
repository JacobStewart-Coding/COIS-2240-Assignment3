import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LibraryManagementTest {

    private Library library;
    private Transaction transaction;
    private Book book;
    private Member member;

    @Before
    public void setUp() throws Exception {
        library = new Library();
        transaction = Transaction.getTransaction();
        book = new Book(123, "Test Book");
        member = new Member(1, "Test Member");
        library.addBook(book);
        library.addMember(member);
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
    
    @Test
    public void testBookAvailabilityBeforeBorrow() {
        assertTrue(book.isAvailable());
    }

    @Test
    public void testBorrowingBook() {
        assertTrue(transaction.borrowBook(book, member));
        assertFalse(book.isAvailable());
    }

    @Test
    public void testBorrowingBookTwice() {
        assertTrue(transaction.borrowBook(book, member));
        assertFalse(transaction.borrowBook(book, member));
    }

    @Test
    public void testReturningBook() {
        transaction.borrowBook(book, member);
        assertTrue(transaction.returnBook(book, member));
        assertTrue(book.isAvailable());
    }

    @Test
    public void testReturningBookTwice() {
        transaction.borrowBook(book, member);
        transaction.returnBook(book, member);
        assertFalse(transaction.returnBook(book, member));
    }
}