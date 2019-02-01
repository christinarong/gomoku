package model;

/**
 * Implementation of an {@code ICell} object.
 */
public class Cell implements ICell {
  private int row;
  private int col;
  private Coord coord;
  private Piece piece;
  private boolean winnerPiece;

  /**
   * Constrcutor of a {@code Cell} object.
   *
   * @param row   the row location of this cell
   * @param col   the column location of this cell
   * @param piece the piece on this cell
   */
  public Cell(int row, int col, Piece piece) {
    this.row = row;
    this.col = col;
    this.coord = new Coord(row, col);
    this.piece = piece;
    this.winnerPiece = false;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getCol() {
    return this.col;
  }

  @Override
  public Coord getCoord() {
    return this.coord;
  }

  @Override
  public Piece getPiece() {
    return this.piece;
  }

  @Override
  public void setPiece(Piece piece) {
    this.piece = piece;
  }

  @Override
  public boolean containsPiece(Piece piece) {
    return this.getPiece().equals(piece);
  }

  @Override
  public boolean samePiece(Cell other) {
    return this.getPiece().equals(other.getPiece());
  }
}
