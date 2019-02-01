package model.strategies;

import model.Coord;
import model.IBoard;
import model.IStrat;
import model.Piece;

/**
 * Combination of multiple strategies, executed in the order listed.
 */
public class ComboStrat implements IStrat {
  private IStrat[] strategies;
  private IStrat defaultStrat;

  /**
   * Constructor for a {@code ComboStrat} object.
   *
   * @param strategies a listing of strategies to be combined
   */
  public ComboStrat(IStrat... strategies) {
    this.strategies = strategies;
    this.defaultStrat = new RandStrat();
  }

  @Override
  public Coord chooseMove(IBoard board, Piece curPiece) {
    for (int i = 0; i < strategies.length; i++) {
      Coord coord = strategies[i].chooseMove(board, curPiece);
      if (coord != null) {
        return coord;
      }
    }
    return defaultStrat.chooseMove(board, curPiece);
  }
}
