/**
 * Represents a square on the chessboard.
 */
public class Square {
    private int row;
    private int col;
    private Piece piece;
    private boolean isWhite;
    
    public Square(int row, int col, boolean isWhite) {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
        this.piece = null;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            piece.setPosition(row, col);
        }
    }
    
    public boolean isEmpty() {
        return piece == null;
    }
    
    public boolean isWhiteSquare() {
        return isWhite;
    }
}
