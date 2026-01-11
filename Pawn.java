/**
 * Represents a Pawn piece in chess.
 */
public class Pawn extends Piece {
    private boolean hasMoved = false;
    
    public Pawn(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }
    
    @Override
    public char getSymbol() {
        return isWhite ? '♙' : '♟';
    }
    
    @Override
    public String getPieceName() {
        return "Pawn";
    }
    
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    
    public boolean hasMoved() {
        return hasMoved;
    }
    
    @Override
    public boolean isValidMove(Board board, int targetRow, int targetCol) {
        int rowDiff = targetRow - row;
        int colDiff = Math.abs(targetCol - col);
        
        // Pawns can only move forward (toward opponent)
        int direction = isWhite ? -1 : 1;
        
        // Moving forward
        if (colDiff == 0) {
            // Can move 1 square forward if empty
            if (rowDiff == direction && board.getPiece(targetRow, targetCol) == null) {
                return true;
            }
            // Can move 2 squares forward on first move if both squares are empty
            if (!hasMoved && rowDiff == 2 * direction && board.getPiece(targetRow, targetCol) == null 
                && board.getPiece(row + direction, col) == null) {
                return true;
            }
        }
        
        // Capturing diagonally
        if (colDiff == 1 && rowDiff == direction) {
            Piece targetPiece = board.getPiece(targetRow, targetCol);
            if (targetPiece != null && targetPiece.isWhite() != isWhite) {
                return true;
            }
        }
        
        return false;
    }
}
