/**
 * Represents the chessboard and manages piece positions.
 */
public class Board {
    private Square[][] squares;
    private static final int SIZE = 8;
    
    public Board() {
        squares = new Square[SIZE][SIZE];
        initializeBoard();
        setupPieces();
    }
    
    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                boolean isWhiteSquare = (row + col) % 2 == 0;
                squares[row][col] = new Square(row, col, isWhiteSquare);
            }
        }
    }
    
    private void setupPieces() {
        // Setup black pieces (top of board)
        squares[0][0].setPiece(new Rook(false, 0, 0));
        squares[0][1].setPiece(new Knight(false, 0, 1));
        squares[0][2].setPiece(new Bishop(false, 0, 2));
        squares[0][3].setPiece(new Queen(false, 0, 3));
        squares[0][4].setPiece(new King(false, 0, 4));
        squares[0][5].setPiece(new Bishop(false, 0, 5));
        squares[0][6].setPiece(new Knight(false, 0, 6));
        squares[0][7].setPiece(new Rook(false, 0, 7));
        
        for (int col = 0; col < SIZE; col++) {
            squares[1][col].setPiece(new Pawn(false, 1, col));
        }
        
        // Setup white pieces (bottom of board)
        squares[7][0].setPiece(new Rook(true, 7, 0));
        squares[7][1].setPiece(new Knight(true, 7, 1));
        squares[7][2].setPiece(new Bishop(true, 7, 2));
        squares[7][3].setPiece(new Queen(true, 7, 3));
        squares[7][4].setPiece(new King(true, 7, 4));
        squares[7][5].setPiece(new Bishop(true, 7, 5));
        squares[7][6].setPiece(new Knight(true, 7, 6));
        squares[7][7].setPiece(new Rook(true, 7, 7));
        
        for (int col = 0; col < SIZE; col++) {
            squares[6][col].setPiece(new Pawn(true, 6, col));
        }
    }
    
    public Square getSquare(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return null;
        }
        return squares[row][col];
    }
    
    public Piece getPiece(int row, int col) {
        Square square = getSquare(row, col);
        return square == null ? null : square.getPiece();
    }
    
    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Square fromSquare = getSquare(fromRow, fromCol);
        Square toSquare = getSquare(toRow, toCol);
        
        if (fromSquare == null || toSquare == null) {
            return;
        }
        
        Piece piece = fromSquare.getPiece();
        if (piece == null) {
            return;
        }
        
        // Handle pawn first move flag
        if (piece instanceof Pawn) {
            ((Pawn) piece).setHasMoved(true);
        }
        
        // Handle rook and king first move flag (for castling)
        if (piece instanceof Rook) {
            ((Rook) piece).setHasMoved(true);
        }
        if (piece instanceof King) {
            ((King) piece).setHasMoved(true);
        }
        
        toSquare.setPiece(piece);
        fromSquare.setPiece(null);
    }
    
    public Board copy() {
        Board newBoard = new Board();
        // Clear the new board
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                newBoard.squares[row][col].setPiece(null);
            }
        }
        
        // Copy pieces
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Piece piece = getPiece(row, col);
                if (piece != null) {
                    Piece newPiece = createPieceCopy(piece);
                    newBoard.squares[row][col].setPiece(newPiece);
                }
            }
        }
        
        return newBoard;
    }
    
    private Piece createPieceCopy(Piece original) {
        if (original instanceof Pawn) {
            Pawn pawn = new Pawn(original.isWhite(), original.getRow(), original.getCol());
            pawn.setHasMoved(((Pawn) original).hasMoved());
            return pawn;
        } else if (original instanceof Rook) {
            Rook rook = new Rook(original.isWhite(), original.getRow(), original.getCol());
            rook.setHasMoved(((Rook) original).hasMoved());
            return rook;
        } else if (original instanceof Knight) {
            return new Knight(original.isWhite(), original.getRow(), original.getCol());
        } else if (original instanceof Bishop) {
            return new Bishop(original.isWhite(), original.getRow(), original.getCol());
        } else if (original instanceof Queen) {
            return new Queen(original.isWhite(), original.getRow(), original.getCol());
        } else if (original instanceof King) {
            King king = new King(original.isWhite(), original.getRow(), original.getCol());
            king.setHasMoved(((King) original).hasMoved());
            return king;
        }
        return null;
    }
    
    public int getSize() {
        return SIZE;
    }
}
