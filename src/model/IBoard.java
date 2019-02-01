package model;

import java.util.List;

/**
 * Represents the grid board. (default size is 19x19)
 */
public interface IBoard {

  /**
   * Gets the width of this board.
   *
   * @return the width of this board
   */
  int getWidth();

  /**
   * Gets the height of this board.
   *
   * @return the height of this board
   */
  int getHeight();

  /**
   * Checks whether this board contains a computer opponent.
   *
   * @return whether there exists a computer opponent
   */
  boolean getComputerOpponent();

  /**
   * Gets the cell at the given row and column on this board.
   *
   * @param row the row location of the cell
   * @param col the column location of the cell
   * @return the cell at the given coordinate
   */
  Cell getCell(int row, int col);

  /**
   * Gets the piece on the cell at the given coordinate.
   *
   * @param coord the row and column of the cell to check
   * @return the type of piece on the cell to check
   */
  Piece getPiece(Coord coord);

  /**
   * Gets the current player.
   *
   * @return the name of the current player
   */
  String getCurPlayer();

  /**
   * Gets the piece that will be placed next.
   *
   * @return the next piece to play
   */
  String getCurPiece();

  /**
   * Gets a copy of the state of the board.
   *
   * @return a copy of the grid
   */
  Piece[][] getCopyOfGrid();

  /**
   * Generates a new grid for this board and sets each cell to empty.
   */
  void initBoard();

  /**
   * Places the current piece to play onto the cell at the given row and column.
   *
   * @param row the row of the cell
   * @param col the column of the cell
   * @throws IllegalArgumentException if the cell is already occupied
   */
  void placePiece(int row, int col);

  /**
   * Generates a cell given a set strategy and places the current piece on that cell.
   */
  void computerMove();

  /**
   * Checks whether the game is over (when a player has achieved five in a row).
   *
   * @return whether the game is over or not
   */
  boolean isGameOver();

  /**
   * Generates a list of every grouping of a given length from this board.
   *
   * @param numConnect the number of cells in a grouping
   * @return a list of groupings
   */
  List<Cell[]> genConnectList(int numConnect);

  /**
   * Sets the computer strategy to the given strategy.
   *
   * @param strat the given strategy
   */
  void setStrategy(IStrat strat);
}
