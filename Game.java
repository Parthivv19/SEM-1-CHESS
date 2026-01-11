/**
 * Manages game logic, turns, check, and checkmate detection.
 */
public class Game {
    private Board board;
    private boolean whiteTurn;
    private boolean gameOver;
    private String gameStatus; // "Playing", "Check", "Checkmate", "Stalemate"
    
    public Game() {
        board = new Board();
        whiteTurn = true;
        gameOver = false;
        gameStatus = "Playing";
    }
    
    public Board getBoard() {
        return board;
    }
    
    public boolean isWhiteTurn() {
        return whiteTurn;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public String getGameStatus() {
        return gameStatus;
    }
    
    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (gameOver) {
            return false;
        }
        
        Piece piece = board.getPiece(fromRow, fromCol);
        if (piece == null) {
            return false;
        }
        
        // Check if it's the correct player's turn
        if (piece.isWhite() != whiteTurn) {
            return false;
        }
        
        // Check if move is valid
        if (!piece.isValidMove(board, toRow, toCol)) {
            return false;
        }
        
        // Make a copy of the board to test the move
        Board testBoard = board.copy();
        testBoard.movePiece(fromRow, fromCol, toRow, toCol);
        
        // Check if move puts own king in check
        if (isInCheck(testBoard, whiteTurn)) {
            return false;
        }
        
        // Execute the move
        board.movePiece(fromRow, fromCol, toRow, toCol);
        
        // Switch turns
        whiteTurn = !whiteTurn;
        
        // Check for check, checkmate, or stalemate
        updateGameStatus();
        
        return true;
    }
    
    private void updateGameStatus() {
        boolean currentPlayerInCheck = isInCheck(board, whiteTurn);
        boolean hasValidMove = hasValidMove(whiteTurn);
        
        if (currentPlayerInCheck && !hasValidMove) {
            gameOver = true;
            gameStatus = whiteTurn ? "Black wins - Checkmate!" : "White wins - Checkmate!";
        } else if (!currentPlayerInCheck && !hasValidMove) {
            gameOver = true;
            gameStatus = "Stalemate!";
        } else if (currentPlayerInCheck) {
            gameStatus = whiteTurn ? "White is in Check" : "Black is in Check";
        } else {
            gameStatus = "Playing";
        }
    }
    
    public boolean isInCheck(Board board, boolean isWhite) {
        // Find the king
        King king = null;
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Piece piece = board.getPiece(row, col);
                if (piece instanceof King && piece.isWhite() == isWhite) {
                    king = (King) piece;
                    break;
                }
            }
            if (king != null) break;
        }
        
        if (king == null) {
            return false;
        }
        
        // Check if any opponent piece can attack the king
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.isWhite() != isWhite) {
                    if (piece.isValidMove(board, king.getRow(), king.getCol())) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean hasValidMove(boolean isWhite) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.isWhite() == isWhite) {
                    for (int toRow = 0; toRow < board.getSize(); toRow++) {
                        for (int toCol = 0; toCol < board.getSize(); toCol++) {
                            if (piece.isValidMove(board, toRow, toCol)) {
                                // Test if this move is legal (doesn't put own king in check)
                                Board testBoard = board.copy();
                                testBoard.movePiece(row, col, toRow, toCol);
                                if (!isInCheck(testBoard, isWhite)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = board.getPiece(fromRow, fromCol);
        if (piece == null) {
            return false;
        }
        
        if (piece.isWhite() != whiteTurn) {
            return false;
        }
        
        if (!piece.isValidMove(board, toRow, toCol)) {
            return false;
        }
        
        // Check if move puts own king in check
        Board testBoard = board.copy();
        testBoard.movePiece(fromRow, fromCol, toRow, toCol);
        return !isInCheck(testBoard, whiteTurn);
    }
}
