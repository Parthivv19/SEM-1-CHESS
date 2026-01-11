/**
 * Abstract base class for all chess pieces.
 * Defines common properties and methods for pieces.
 */
public abstract class Piece {
    protected boolean isWhite;
    protected int row;
    protected int col;
    
    public Piece(boolean isWhite, int row, int col) {
        this.isWhite = isWhite;
        this.row = row;
        this.col = col;
    }
    
    public boolean isWhite() {
        return isWhite;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    /**
     * Get the Unicode character representing this piece.
     */
    public abstract char getSymbol();
    
    /**
     * Check if a move to the target position is valid for this piece.
     * @param board The current board state
     * @param targetRow Target row
     * @param targetCol Target column
     * @return true if the move is valid
     */
    public abstract boolean isValidMove(Board board, int targetRow, int targetCol);
    
    /**
     * Get the name of the piece type.
     */
    public abstract String getPieceName();
}
