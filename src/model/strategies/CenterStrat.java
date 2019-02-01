package model.strategies;

import model.Coord;
import model.IBoard;
import model.IStrat;
import model.Piece;

/**
 * Strategy to place a piece in the center of the board.
 */
public class CenterStrat implements IStrat {
  @Override
  public Coord chooseMove(IBoard board, Piece curPiece) {
    int midWidth = board.getWidth() / 2;
    int midHeight = board.getHeight() / 2;
    return new Coord(midWidth, midHeight);
  }
}
