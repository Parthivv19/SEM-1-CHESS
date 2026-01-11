# Chess Game in Java

A fully functional, graphical Chess game built with Java Swing.

## Features

- **Complete Chess Rules**: All pieces (Pawn, Rook, Knight, Bishop, Queen, King) with proper movement logic
- **Turn-based Gameplay**: Alternating moves between White and Black players
- **Visual Feedback**: 
  - Selected square highlighting (yellow)
  - Valid move highlighting (light green)
  - Unicode chess piece symbols
- **Game State Detection**: Check, Checkmate, and Stalemate detection
- **Clean OOP Design**: Separated classes for pieces, board, game logic, and GUI

## How to Run

1. Compile all Java files:
   ```bash
   javac *.java
   ```

2. Run the main class:
   ```bash
   java Main
   ```

## How to Play

1. Click on a piece to select it (it will be highlighted in yellow)
2. Valid moves for that piece will be highlighted in light green
3. Click on a valid destination square to make your move
4. The game alternates between White and Black turns
5. The status bar shows the current turn and game state (Check, Checkmate, Stalemate)

## Project Structure

- `Piece.java` - Abstract base class for all chess pieces
- `Pawn.java`, `Rook.java`, `Knight.java`, `Bishop.java`, `Queen.java`, `King.java` - Individual piece classes
- `Square.java` - Represents a square on the chessboard
- `Board.java` - Manages the 8x8 grid and piece positions
- `Game.java` - Handles game logic, turns, check, and checkmate detection
- `ChessGUI.java` - Swing-based graphical user interface
- `Main.java` - Entry point to launch the application

## Requirements

- Java JDK 8 or higher
- Standard Java libraries only (no external dependencies)
