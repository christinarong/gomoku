package model.strategies;

import model.Coord;
import model.IBoard;
import model.IStrat;
import model.Piece;

/**
 * Predetermined combination of strategies at varying difficulty levels (easy, medium, and hard).
 */
public class LevelStrat implements IStrat {
  private String level;
  private IStrat hardStrat = new ComboStrat(
          new OpenFourStrat(true),
          new OpenFourStrat(false),
          new OpenThreeStrat(true),
          new OpenThreeStrat(false),
          new ClosedThreeStrat(true),
          new OpenTwoStrat(true),
          new OpenTwoStrat(false),
          new ClosedThreeStrat(false),
          new OpenOneStrat(true),
          new OpenOneStrat(false),
          new CenterStrat());

  private IStrat midStrat = new ComboStrat(
          new OpenFourStrat(true),
          new OpenFourStrat(false),
          new OpenThreeStrat(true),
          new OpenThreeStrat(false),
          new ClosedThreeStrat(true),
          new RandStrat(6));

  private IStrat easyStrat = new ComboStrat(
          new OpenFourStrat(true),
          new OpenFourStrat(false),
          new OpenThreeStrat(false),
          new RandStrat(10));

  /**
   * Constructor of a {@code LevelStrat} object.
   *
   * @param level the difficulty level
   */
  public LevelStrat(String level) {
    this.level = level;
  }

  @Override
  public Coord chooseMove(IBoard board, Piece curPiece) {
    switch (level) {
      case "easy":
        return easyStrat.chooseMove(board, curPiece);
      case "medium":
        return midStrat.chooseMove(board, curPiece);
      case "hard":
        return hardStrat.chooseMove(board, curPiece);
      default:
        return hardStrat.chooseMove(board, curPiece);
    }
  }
}
