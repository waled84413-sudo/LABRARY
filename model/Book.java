//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package model;

public class Book {
    private int bookId;
    private String title;
    private Author author;
    private boolean isAvailable;
    private String isbn;
    private int publicationYear;
    private String publisher;
    private int totalCopies;
    private int availableCopies;

    public Book(int var1, String var2, Author var3) {
        this.bookId = var1;
        this.title = var2;
        this.author = var3;
        this.isAvailable = true;
        this.totalCopies = 1;
        this.availableCopies = 1;
    }

    public Book(int var1, String var2, Author var3, String var4, int var5, String var6) {
        this.bookId = var1;
        this.title = var2;
        this.author = var3;
        this.isAvailable = true;
        this.isbn = var4;
        this.publicationYear = var5;
        this.publisher = var6;
        this.totalCopies = 1;
        this.availableCopies = 1;
    }

    public int getBookId() {
        return this.bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public Author getAuthor() {
        return this.author;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean var1) {
        this.isAvailable = var1;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String var1) {
        this.isbn = var1;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public void setPublicationYear(int var1) {
        this.publicationYear = var1;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String var1) {
        this.publisher = var1;
    }

    public int getTotalCopies() {
        return this.totalCopies;
    }

    public void setTotalCopies(int var1) {
        this.totalCopies = var1;
    }

    public int getAvailableCopies() {
        return this.availableCopies;
    }

    public void setAvailableCopies(int var1) {
        this.availableCopies = var1;
    }
}
