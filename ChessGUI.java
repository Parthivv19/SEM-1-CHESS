import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main GUI class for the Chess game using Java Swing.
 */
public class ChessGUI extends JFrame {
    private Game game;
    private JPanel boardPanel;
    private JLabel[][] squareLabels;
    private JLabel statusLabel;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private static final int SQUARE_SIZE = 80;
    private static final Font PIECE_FONT = new Font("Serif", Font.PLAIN, 60);
    
    public ChessGUI() {
        game = new Game();
        initializeGUI();
        updateBoard();
    }
    
    private void initializeGUI() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create board panel
        boardPanel = new JPanel(new GridLayout(8, 8));
        squareLabels = new JLabel[8][8];
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JLabel squareLabel = new JLabel("", JLabel.CENTER);
                squareLabel.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                squareLabel.setOpaque(true);
                squareLabel.setFont(PIECE_FONT);
                squareLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                
                // Set background color (alternating pattern)
                boolean isWhiteSquare = (row + col) % 2 == 0;
                squareLabel.setBackground(isWhiteSquare ? new Color(240, 217, 181) : new Color(181, 136, 99));
                
                final int r = row;
                final int c = col;
                squareLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(r, c);
                    }
                });
                
                squareLabels[row][col] = squareLabel;
                boardPanel.add(squareLabel);
            }
        }
        
        // Create status label
        statusLabel = new JLabel("White's turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void handleSquareClick(int row, int col) {
        if (game.isGameOver()) {
            return;
        }
        
        Piece piece = game.getBoard().getPiece(row, col);
        
        // If a square is already selected
        if (selectedRow != -1 && selectedCol != -1) {
            // If clicking the same square, deselect
            if (selectedRow == row && selectedCol == col) {
                selectedRow = -1;
                selectedCol = -1;
                updateBoard();
                return;
            }
            
            // Try to make the move
            if (game.makeMove(selectedRow, selectedCol, row, col)) {
                selectedRow = -1;
                selectedCol = -1;
                updateBoard();
                updateStatus();
            } else {
                // Invalid move - select new piece if clicking on own piece
                if (piece != null && piece.isWhite() == game.isWhiteTurn()) {
                    selectedRow = row;
                    selectedCol = col;
                    updateBoard();
                } else {
                    // Clear selection on invalid move
                    selectedRow = -1;
                    selectedCol = -1;
                    updateBoard();
                }
            }
        } else {
            // No square selected - select if clicking on own piece
            if (piece != null && piece.isWhite() == game.isWhiteTurn()) {
                selectedRow = row;
                selectedCol = col;
                updateBoard();
            }
        }
    }
    
    private void updateBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JLabel squareLabel = squareLabels[row][col];
                Piece piece = game.getBoard().getPiece(row, col);
                
                // Reset background color
                boolean isWhiteSquare = (row + col) % 2 == 0;
                Color baseColor = isWhiteSquare ? new Color(240, 217, 181) : new Color(181, 136, 99);
                
                // Highlight selected square
                if (selectedRow == row && selectedCol == col) {
                    squareLabel.setBackground(new Color(255, 255, 0)); // Yellow highlight
                } else {
                    squareLabel.setBackground(baseColor);
                }
                
                // Highlight valid moves
                if (selectedRow != -1 && selectedCol != -1) {
                    if (game.isValidMove(selectedRow, selectedCol, row, col)) {
                        Color highlightColor = isWhiteSquare ? 
                            new Color(200, 255, 200) : new Color(150, 255, 150); // Light green
                        squareLabel.setBackground(highlightColor);
                    }
                }
                
                // Set piece symbol
                if (piece != null) {
                    squareLabel.setText(String.valueOf(piece.getSymbol()));
                    squareLabel.setForeground(piece.isWhite() ? Color.WHITE : Color.BLACK);
                } else {
                    squareLabel.setText("");
                }
            }
        }
    }
    
    private void updateStatus() {
        String status = game.getGameStatus();
        String turnInfo = game.isWhiteTurn() ? "White's turn" : "Black's turn";
        
        if (game.isGameOver()) {
            statusLabel.setText(status);
            statusLabel.setForeground(Color.RED);
        } else {
            statusLabel.setText(turnInfo + " - " + status);
            statusLabel.setForeground(Color.BLACK);
        }
    }
}
