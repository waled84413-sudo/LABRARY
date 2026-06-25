//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gui;

import data.LibraryDatabase;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Author;
import model.Book;
import model.Member;

public class LibraryGUI extends JFrame {
    private LibraryDatabase db = new LibraryDatabase();
    private JTable booksTable;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    public LibraryGUI() {
        this.initUI();
    }

    private void initUI() {
        this.setTitle("نظام إدارة المكتبة");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.addTab("الكتب", this.createBooksPanel());
        this.tabbedPane.addTab("الأعضاء", this.createMembersPanel());
        this.tabbedPane.addTab("الاستعارات", this.createBorrowingPanel());
        this.add(this.tabbedPane);
        this.setVisible(true);
    }

    private JPanel createBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        this.tableModel = new DefaultTableModel(new String[]{"ID", "العنوان", "المؤلف", "متاح"}, 0);
        this.booksTable = new JTable(this.tableModel);
        JScrollPane scrollPane = new JScrollPane(this.booksTable);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loadBtn = new JButton("عرض الكتب");
        loadBtn.addActionListener((e) -> this.loadBooks());
        JButton addBtn = new JButton("إضافة كتاب");
        addBtn.addActionListener((e) -> this.showAddBookDialog());
        JButton searchBtn = new JButton("بحث");
        searchBtn.addActionListener((e) -> this.searchBooks());
        buttonPanel.add(loadBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(searchBtn);
        panel.add(buttonPanel, "North");
        panel.add(scrollPane, "Center");
        this.loadBooks();
        return panel;
    }

    private JPanel createMembersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea memberArea = new JTextArea();
        memberArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(memberArea);
        JButton loadBtn = new JButton("عرض الأعضاء");
        loadBtn.addActionListener((e) -> {
            List<Member> members = this.db.getAllMembers();
            StringBuilder sb = new StringBuilder();

            for(Member m : members) {
                sb.append(m.toString()).append("\n");
            }

            memberArea.setText(sb.toString());
        });
        JButton addBtn = new JButton("إضافة عضو");
        addBtn.addActionListener((e) -> this.showAddMemberDialog());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadBtn);
        buttonPanel.add(addBtn);
        panel.add(buttonPanel, "North");
        panel.add(scrollPane, "Center");
        return panel;
    }

    private JPanel createBorrowingPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel memberIdLabel = new JLabel("رقم العضو:");
        JTextField memberIdField = new JTextField(15);
        JLabel bookIdLabel = new JLabel("رقم الكتاب:");
        JTextField bookIdField = new JTextField(15);
        JButton borrowBtn = new JButton("استعارة");
        JButton returnBtn = new JButton("إرجاع");
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(memberIdLabel, gbc);
        gbc.gridx = 1;
        panel.add(memberIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(bookIdLabel, gbc);
        gbc.gridx = 1;
        panel.add(bookIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(borrowBtn, gbc);
        gbc.gridx = 1;
        panel.add(returnBtn, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);
        borrowBtn.addActionListener((e) -> {
            try {
                int memberId = Integer.parseInt(memberIdField.getText());
                int bookId = Integer.parseInt(bookIdField.getText());
                this.db.borrowBook(memberId, bookId);
                resultArea.append("تم استعارة الكتاب بنجاح!\n");
                this.loadBooks();
            } catch (NumberFormatException var7) {
                resultArea.append("خطأ: الرجاء إدخال أرقام صحيحة\n");
            } catch (Exception ex) {
                resultArea.append("خطأ: " + ex.getMessage() + "\n");
            }

        });
        returnBtn.addActionListener((e) -> {
            String borrowingIdStr = JOptionPane.showInputDialog(this, "أدخل رقم عملية الاستعارة:");
            if (borrowingIdStr != null) {
                try {
                    int borrowingId = Integer.parseInt(borrowingIdStr);
                    this.db.returnBook(borrowingId);
                    resultArea.append("تم إرجاع الكتاب بنجاح!\n");
                    this.loadBooks();
                } catch (NumberFormatException var5) {
                    resultArea.append("خطأ: رقم غير صحيح\n");
                }
            }

        });
        return panel;
    }

    private void loadBooks() {
        this.tableModel.setRowCount(0);

        for(Book book : this.db.getAllBooks()) {
            this.tableModel.addRow(new Object[]{book.getBookId(), book.getTitle(), book.getAuthor().getName(), book.isAvailable() ? "نعم" : "لا"});
        }

    }

    private void showAddBookDialog() {
        JTextField titleField = new JTextField(15);
        JTextField authorField = new JTextField(15);
        JTextField isbnField = new JTextField(15);
        JTextField yearField = new JTextField(15);
        JTextField publisherField = new JTextField(15);
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("عنوان الكتاب:"));
        panel.add(titleField);
        panel.add(new JLabel("اسم المؤلف:"));
        panel.add(authorField);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("سنة النشر:"));
        panel.add(yearField);
        panel.add(new JLabel("الناشر:"));
        panel.add(publisherField);
        int result = JOptionPane.showConfirmDialog(this, panel, "إضافة كتاب جديد", 2);
        if (result == 0) {
            String title = titleField.getText().trim();
            String authorName = authorField.getText().trim();
            if (title.isEmpty() || authorName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "الرجاء إدخال عنوان الكتاب واسم المؤلف", "خطأ", 0);
                return;
            }

            Author author = new Author(0, authorName, "");
            Book book = new Book(0, title, author, isbnField.getText().trim(), 0, publisherField.getText().trim());

            try {
                if (!yearField.getText().trim().isEmpty()) {
                    book.setPublicationYear(Integer.parseInt(yearField.getText().trim()));
                }
            } catch (NumberFormatException var13) {
                JOptionPane.showMessageDialog(this, "سنة النشر يجب أن تكون رقماً", "تحذير", 2);
            }

            this.db.saveBook(book);
            this.loadBooks();
            JOptionPane.showMessageDialog(this, "تمت إضافة الكتاب بنجاح!");
        }

    }

    private void showAddMemberDialog() {
        JTextField nameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        String[] types = new String[]{"regular", "premium"};
        JComboBox<String> typeCombo = new JComboBox(types);
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("الاسم:"));
        panel.add(nameField);
        panel.add(new JLabel("البريد الإلكتروني:"));
        panel.add(emailField);
        panel.add(new JLabel("نوع العضوية:"));
        panel.add(typeCombo);
        int result = JOptionPane.showConfirmDialog(this, panel, "إضافة عضو جديد", 2);
        if (result == 0) {
            Member member = new Member(0, nameField.getText(), emailField.getText(), (String)typeCombo.getSelectedItem());
            this.db.saveMember(member);
            JOptionPane.showMessageDialog(this, "تمت إضافة العضو بنجاح!");
        }

    }

    private void searchBooks() {
        String searchTerm = JOptionPane.showInputDialog(this, "أدخل عنوان الكتاب للبحث:");
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            this.tableModel.setRowCount(0);
            List<Book> books = this.db.searchBooksByTitle(searchTerm);

            for(Book book : books) {
                this.tableModel.addRow(new Object[]{book.getBookId(), book.getTitle(), book.getAuthor().getName(), book.isAvailable() ? "نعم" : "لا"});
            }

            if (books.isEmpty()) {
                JOptionPane.showMessageDialog(this, "لم يتم العثور على كتب");
            }
        }

    }
}
