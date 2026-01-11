import javax.swing.SwingUtilities;

/**
 * Main entry point for the Chess game application.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGUI chessGame = new ChessGUI();
            chessGame.setVisible(true);
        });
    }
}
