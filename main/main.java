package main;
import gui.LibraryGUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // تشغيل واجهة المستخدم
        SwingUtilities.invokeLater(() -> {
            try {
                // تعيين مظهر واجهة جميل
                javax.swing.UIManager.setLookAndFeel(
                        javax.swing.UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            new LibraryGUI();
        });
    }
}
