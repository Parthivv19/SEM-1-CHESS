/**
 * Represents a Rook piece in chess.
 */
public class Rook extends Piece {
    private boolean hasMoved = false;
    
    public Rook(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }
    
    @Override
    public char getSymbol() {
        return isWhite ? '♖' : '♜';
    }
    
    @Override
    public String getPieceName() {
        return "Rook";
    }
    
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    
    public boolean hasMoved() {
        return hasMoved;
    }
    
    @Override
    public boolean isValidMove(Board board, int targetRow, int targetCol) {
        // Rook moves horizontally or vertically
        if (row != targetRow && col != targetCol) {
            return false;
        }
        
        // Check if path is clear
        int rowStep = targetRow > row ? 1 : (targetRow < row ? -1 : 0);
        int colStep = targetCol > col ? 1 : (targetCol < col ? -1 : 0);
        
        int currentRow = row + rowStep;
        int currentCol = col + colStep;
        
        while (currentRow != targetRow || currentCol != targetCol) {
            if (board.getPiece(currentRow, currentCol) != null) {
                return false; // Path blocked
            }
            currentRow += rowStep;
            currentCol += colStep;
        }
        
        // Check if target square is empty or has opponent's piece
        Piece targetPiece = board.getPiece(targetRow, targetCol);
        return targetPiece == null || targetPiece.isWhite() != isWhite;
    }
}
