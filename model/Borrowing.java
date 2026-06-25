package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a borrowing transaction
 */
public class Borrowing {
    private int borrowingId;
    private Member member;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fineAmount;
    private String status; // borrowed, returned, overdue

    // Fine rate per day (in currency units)
    private static final double FINE_RATE_PER_DAY = 0.50;
    private static final int LOAN_PERIOD_DAYS = 14;

    public Borrowing(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(LOAN_PERIOD_DAYS);
        this.status = "borrowed";
        this.fineAmount = 0.0;
    }

    public Borrowing(int borrowingId, Member member, Book book,
                     LocalDate borrowDate, LocalDate dueDate, String status) {
        this.borrowingId = borrowingId;
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
        this.fineAmount = 0.0;
    }

    // Getters and Setters
    public int getBorrowingId() { return borrowingId; }
    public void setBorrowingId(int borrowingId) { this.borrowingId = borrowingId; }

    public Member getMember() { return member; }
    public Book getBook() { return book; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        if (returnDate != null) {
            this.status = "returned";
            calculateFine();
        }
    }

    public String getStatus() { return status; }
    public double getFineAmount() { return fineAmount; }

    /**
     * Calculate fine based on return date
     * @return fine amount
     */
    public double calculateFine() {
        LocalDate checkDate = (returnDate != null) ? returnDate : LocalDate.now();

        if (checkDate.isAfter(dueDate)) {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, checkDate);
            fineAmount = daysOverdue * FINE_RATE_PER_DAY;
        } else {
            fineAmount = 0.0;
        }

        return fineAmount;
    }

    /**
     * Check if borrowing is overdue
     */
    public boolean isOverdue() {
        if (status.equals("returned")) return false;
        return LocalDate.now().isAfter(dueDate);
    }

    /**
     * Update status to overdue if applicable
     */
    public void updateStatus() {
        if (status.equals("borrowed") && isOverdue()) {
            status = "overdue";
        }
    }

    /**
     * Get days until due (negative if overdue)
     */
    public long getDaysUntilDue() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }

    @Override
    public String toString() {
        return String.format("Borrowing: %s borrowed '%s' on %s (Due: %s)",
                member.getName(), book.getTitle(), borrowDate, dueDate);
    }
}
