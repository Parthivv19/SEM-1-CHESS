/**
 * Represents a Knight piece in chess.
 */
public class Knight extends Piece {
    
    public Knight(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }
    
    @Override
    public char getSymbol() {
        return isWhite ? '♘' : '♞';
    }
    
    @Override
    public String getPieceName() {
        return "Knight";
    }
    
    @Override
    public boolean isValidMove(Board board, int targetRow, int targetCol) {
        int rowDiff = Math.abs(targetRow - row);
        int colDiff = Math.abs(targetCol - col);
        
        // Knight moves in L-shape: 2 squares in one direction, 1 square perpendicular
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }
        
        // Check if target square is empty or has opponent's piece
        Piece targetPiece = board.getPiece(targetRow, targetCol);
        return targetPiece == null || targetPiece.isWhite() != isWhite;
    }
}
