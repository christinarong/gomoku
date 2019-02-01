package model.strategies;

import java.util.ArrayList;
import java.util.Arrays;

import model.Piece;

/**
 * Pattern strategy to check for a one in a row with two open endings. An offensive player would add
 * onto the one in a row to create a two in a row. A defensive player would block the opponent from
 * following suit.
 */
public class OpenOneStrat extends AbsPatternStrat {

  /**
   * Constructor for a {@code OpenOneStrat} object.
   *
   * @param offensive whether playing offensive or not
   */
  public OpenOneStrat(boolean offensive) {
    super(offensive, 5);
  }

  @Override
  public void setPatternsList(Piece pieceToCheck) {
    Piece P = pieceToCheck;
    Piece E = Piece.EMPTY;

    Piece[] pattern1 = {E, P, E, E, E};
    Piece[] pattern2 = {E, E, P, E, E};
    Piece[] pattern3 = {E, E, E, P, E};

    patternsList.put(pattern1, new ArrayList<>());
    patternsList.get(pattern1).addAll(Arrays.asList(0, 2));
    patternsList.put(pattern2, new ArrayList<>());
    patternsList.get(pattern2).addAll(Arrays.asList(1, 3));
    patternsList.put(pattern3, new ArrayList<>());
    patternsList.get(pattern3).addAll(Arrays.asList(2, 4));
  }
}
