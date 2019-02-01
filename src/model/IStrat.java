package model;

/**
 * Represents an algorithm to choose a potential move to play.
 */
public interface IStrat {
  /**
   * Chooses a cell to place the next piece.
   *
   * @param board the current board
   * @return the coordinate of the chosen cell
   */
  Coord chooseMove(IBoard board, Piece curPiece);
}
