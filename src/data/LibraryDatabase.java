




package data;

import java.util.ArrayList;
import java.util.List;
import model.Author;
import model.Book;
import model.Member;

public class LibraryDatabase {
    private List<Book> books = new ArrayList();
    private List<Member> members = new ArrayList();
    private int nextBookId = 1;
    private int nextMemberId = 1;

    public LibraryDatabase() {
    }

    public void saveBook(Book var1) {
        Book var2 = new Book(this.nextBookId++, var1.getTitle(), var1.getAuthor());
        var2.setAvailable(var1.isAvailable());
        this.books.add(var2);
    }

    public List<Book> getAllBooks() {
        return new ArrayList(this.books);
    }

    public List<Member> getAllMembers() {
        return new ArrayList(this.members);
    }

    public void saveMember(Member var1) {
        Member var2 = new Member(this.nextMemberId++, var1.getName(), var1.getEmail());
        this.members.add(var2);
    }

    public List<Book> searchBooksByTitle(String var1) {
        ArrayList var2 = new ArrayList();

        for(Book var4 : this.books) {
            if (var4.getTitle().toLowerCase().contains(var1.toLowerCase())) {
                var2.add(var4);
            }
        }

        return var2;
    }

    public boolean borrowBook(int var1, int var2) {
        for(Book var4 : this.books) {
            if (var4.getBookId() == var2 && var4.isAvailable()) {
                var4.setAvailable(false);
                return true;
            }
        }

        return false;
    }

    public boolean returnBook(int var1) {
        for(Book var3 : this.books) {
            if (!var3.isAvailable()) {
                var3.setAvailable(true);
                return true;
            }
        }

        return false;
    }

    public void saveAuthor(Author var1) {
    }

    public void deleteBook(int var1) {
        this.books.removeIf((var1x) -> var1x.getBookId() == var1);
    }
}
