package model;

/**
 * Represents a coordinate (row and column) of a cell.
 */
public class Coord {
  private int row;
  private int col;

  /**
   * Constructor for a {@code Coord} object.
   *
   * @param row the row of the coordinate
   * @param col the column of the coordinate
   */
  public Coord(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Gets the row of this coordinate.
   *
   * @return the row
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Gets the column of this coordinate.
   *
   * @return the column
   */
  public int getCol() {
    return this.col;
  }
}
