package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library member who can borrow books
 * Implements Borrowable interface
 */
public class Member extends Person {
    private String membershipType; // regular, premium
    private String joinDate;
    private List<Book> borrowedBooks;
    private double totalFines;

    public Member(int id, String name, String email) {
        super(id, name, email);
        this.membershipType = "regular";
        this.borrowedBooks = new ArrayList<>();
        this.totalFines = 0.0;
    }

    public Member(int id, String name, String email, String membershipType) {
        super(id, name, email);
        this.membershipType = membershipType;
        this.borrowedBooks = new ArrayList<>();
        this.totalFines = 0.0;
    }

    // Getters and Setters
    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    public String getJoinDate() { return joinDate; }
    public void setJoinDate(String joinDate) { this.joinDate = joinDate; }

    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public double getTotalFines() { return totalFines; }
    public void addFine(double amount) { this.totalFines += amount; }
    public void payFines(double amount) { this.totalFines = Math.max(0, this.totalFines - amount); }

    @Override
    public String getRole() {
        return "Member";
    }

    /**
     * Borrow a book
     * @param book The book to borrow
     * @return true if successful, false if book not available
     */
    public boolean borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            System.out.println(getName() + " borrowed: " + book.getTitle());
            return true;
        } else {
            System.out.println("Book '" + book.getTitle() + "' is not available.");
            return false;
        }
    }

    /**
     * Return a book
     * @param book The book to return
     * @return true if successful
     */
    public boolean returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            System.out.println(getName() + " returned: " + book.getTitle());
            return true;
        } else {
            System.out.println("Book '" + book.getTitle() + "' was not borrowed by this member.");
            return false;
        }
    }

    /**
     * Get number of books currently borrowed
     */
    public int getBorrowedCount() {
        return borrowedBooks.size();
    }

    /**
     * Check if member can borrow more books
     * Premium members can borrow up to 10, regular up to 5
     */
    public boolean canBorrowMore() {
        int maxBooks = membershipType.equals("premium") ? 10 : 5;
        return borrowedBooks.size() < maxBooks;
    }

    @Override
    public String toString() {
        return String.format("Member: %s (%s) - Borrowed: %d books", getName(), membershipType, borrowedBooks.size());
    }
}
