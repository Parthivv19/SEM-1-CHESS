/**
 * Represents a Queen piece in chess.
 */
public class Queen extends Piece {
    
    public Queen(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }
    
    @Override
    public char getSymbol() {
        return isWhite ? '♕' : '♛';
    }
    
    @Override
    public String getPieceName() {
        return "Queen";
    }
    
    @Override
    public boolean isValidMove(Board board, int targetRow, int targetCol) {
        int rowDiff = Math.abs(targetRow - row);
        int colDiff = Math.abs(targetCol - col);
        
        // Queen moves like Rook (horizontal/vertical) or Bishop (diagonal)
        boolean isRookMove = (row == targetRow || col == targetCol);
        boolean isBishopMove = (rowDiff == colDiff);
        
        if (!isRookMove && !isBishopMove) {
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
