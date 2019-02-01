package model;

/**
 * Represents a cell (square) on the board.
 */
public interface ICell {

  /**
   * Gets the row location of this cell.
   *
   * @return the row of this cell
   */
  int getRow();

  /**
   * Gets the column location of this cell.
   *
   * @return the column of this cell
   */
  int getCol();

  /**
   * Gets the coordinate (row and column) of this cell.
   *
   * @return the row and column of this cell
   */
  Coord getCoord();

  /**
   * Gets the piece status of this cell (empty, black, or white).
   *
   * @return the piece of the cell
   */
  Piece getPiece();

  /**
   * Sets the piece status of this cell (empty, black, or white).
   *
   * @param piece the given piece to place on this cell
   */
  void setPiece(Piece piece);

  /**
   * Checks if this cell contains the given piece.
   *
   * @param piece the given piece to check
   */
  boolean containsPiece(Piece piece);

  /**
   * Checks if this cell is the same as the given cell.
   *
   * @param other the given cell to compare
   * @return whether the cells are the same or not
   */
  boolean samePiece(Cell other);
}
