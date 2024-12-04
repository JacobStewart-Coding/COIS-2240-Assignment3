public class Transaction {

	
	private static Transaction instance = null;
	
	//private so only this class can make a new Transaction directly
	private Transaction() {}
	
	public static synchronized Transaction getTransaction() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
	}
	
    // Perform the borrowing of a book 
	// removed static
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    // removed static
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Get the current date and time in a readable format
    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    public void saveTransaction(String transactionDetails) {
        try {
            FileWriter writer = new FileWriter("transactions.txt", true);
            writer.write(transactionDetails + System.lineSeparator());
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }
    public void displayTransactionHistory() {
        try {
            File file = new File("transactions.txt");
            Scanner scanner = new Scanner(file);
            System.out.println("\nTransaction History:");
            System.out.println("---------------------");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No transaction history found.");
        }
    }
}