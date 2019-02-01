package model.strategies;

import java.util.ArrayList;
import java.util.Arrays;

import model.Piece;

/**
 * Pattern strategy that checks for a three in a row with one open ending. An offensive player would
 * add onto the three in a row to create a four in a row. A defensive player would block the three
 * in a row to prevent the opponent from following suit.
 */
public class ClosedThreeStrat extends AbsPatternStrat {

  /**
   * Constructor for a {@code ClosedThreeStrat} object.
   *
   * @param offensive whether playing offensive or not
   */
  public ClosedThreeStrat(boolean offensive) {
    super(offensive, 5);
  }

  @Override
  public void setPatternsList(Piece pieceToCheck) {
    Piece P = pieceToCheck;
    Piece E = Piece.EMPTY;
    Piece[] pattern1 = {P, P, P, E, E};
    Piece[] pattern2 = {E, P, P, P, E};
    Piece[] pattern3 = {E, E, P, P, P};

    patternsList.put(pattern1, new ArrayList<>());
    patternsList.get(pattern1).add(3);
    patternsList.put(pattern2, new ArrayList<>());
    patternsList.get(pattern2).addAll(Arrays.asList(0, 4));
    patternsList.put(pattern3, new ArrayList<>());
    patternsList.get(pattern3).add(1);
  }
}
