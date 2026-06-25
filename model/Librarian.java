//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package model;


import data.LibraryDatabase;
import java.io.PrintStream;

public class Librarian extends Person {
    private String employeeId;
    private String hireDate;
    private String position;

    public Librarian(int var1, String var2, String var3) {
        super(var1, var2, var3);
    }

    public Librarian(int var1, String var2, String var3, String var4) {
        super(var1, var2, var3);
        this.employeeId = var4;
    }

    public Librarian(int var1, String var2, String var3, String var4, String var5) {
        super(var1, var2, var3);
        this.employeeId = var4;
        this.position = var5;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String var1) {
        this.employeeId = var1;
    }

    public String getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(String var1) {
        this.hireDate = var1;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String var1) {
        this.position = var1;
    }

    public String getRole() {
        return "Librarian";
    }

    public void addBook(Book var1, LibraryDatabase var2) {
        var2.saveBook(var1);
        PrintStream var10000 = System.out;
        String var10001 = this.getName();
        var10000.println("Librarian " + var10001 + " added book: " + var1.getTitle());
    }

    public void removeBook(int var1, LibraryDatabase var2) {
        var2.deleteBook(var1);
        PrintStream var10000 = System.out;
        String var10001 = this.getName();
        var10000.println("Librarian " + var10001 + " removed book ID: " + var1);
    }

    public void registerMember(Member var1, LibraryDatabase var2) {
        var2.saveMember(var1);
        PrintStream var10000 = System.out;
        String var10001 = this.getName();
        var10000.println("Librarian " + var10001 + " registered member: " + var1.getName());
    }

    public String toString() {
        return String.format("Librarian: %s (ID: %s)", this.getName(), this.employeeId);
    }
}
