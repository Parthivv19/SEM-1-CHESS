/**
 * Represents a King piece in chess.
 */
public class King extends Piece {
    private boolean hasMoved = false;
    
    public King(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }
    
    @Override
    public char getSymbol() {
        return isWhite ? '♔' : '♚';
    }
    
    @Override
    public String getPieceName() {
        return "King";
    }
    
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    
    public boolean hasMoved() {
        return hasMoved;
    }
    
    @Override
    public boolean isValidMove(Board board, int targetRow, int targetCol) {
        int rowDiff = Math.abs(targetRow - row);
        int colDiff = Math.abs(targetCol - col);
        
        // King moves one square in any direction
        if (rowDiff > 1 || colDiff > 1) {
            return false;
        }
        
        // Check if target square is empty or has opponent's piece
        Piece targetPiece = board.getPiece(targetRow, targetCol);
        return targetPiece == null || targetPiece.isWhite() != isWhite;
    }
}
