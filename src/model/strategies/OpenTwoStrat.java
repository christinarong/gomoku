package model.strategies;

import java.util.ArrayList;
import java.util.Arrays;

import model.Piece;

/**
 * Pattern strategy that checks for a two in a row with two open endings. An offensive player would
 * add onto the two in a row to create a three in a row. A defensive player would block the opponent
 * from following suit.
 */
public class OpenTwoStrat extends AbsPatternStrat {

  /**
   * Constructor for a {@code OpenTwoStrat} object.
   *
   * @param offensive whether playing offensive or not
   */
  public OpenTwoStrat(boolean offensive) {
    super(offensive, 5);
  }

  @Override
  public void setPatternsList(Piece pieceToCheck) {
    Piece P = pieceToCheck;
    Piece E = Piece.EMPTY;

    Piece[] pattern1 = {E, E, P, P, E};
    Piece[] pattern2 = {E, P, E, P, E};
    Piece[] pattern3 = {E, P, P, E, E};

    patternsList.put(pattern1, new ArrayList<>());
    patternsList.get(pattern1).addAll(Arrays.asList(1, 4));
    patternsList.put(pattern2, new ArrayList<>());
    patternsList.get(pattern2).addAll(Arrays.asList(0, 2, 4));
    patternsList.put(pattern3, new ArrayList<>());
    patternsList.get(pattern3).addAll(Arrays.asList(0, 3));
  }
}
