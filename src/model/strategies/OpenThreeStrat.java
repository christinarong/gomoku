package model.strategies;

import java.util.ArrayList;
import java.util.Arrays;

import model.Piece;

/**
 * Pattern strategy that checks for a three in a row with two open endings. An offensive player
 * would add onto the three in a row to create a four in a row. A defensive player would block the
 * opponent from following suit.
 */
public class OpenThreeStrat extends AbsPatternStrat {

  /**
   * Constructor for a {@code OpenThreeStrat} object.
   *
   * @param offensive whether playing offensive or not
   */
  public OpenThreeStrat(boolean offensive) {
    super(offensive, 6);
  }

  @Override
  public void setPatternsList(Piece pieceToCheck) {
    Piece P = pieceToCheck;
    Piece E = Piece.EMPTY;

    Piece[] pattern1 = {E, E, P, P, P, E};
    Piece[] pattern2 = {E, P, E, P, P, E};
    Piece[] pattern3 = {E, P, P, E, P, E};
    Piece[] pattern4 = {E, P, P, P, E, E};

    patternsList.put(pattern1, new ArrayList<>());
    patternsList.get(pattern1).addAll(Arrays.asList(1, 5));
    patternsList.put(pattern2, new ArrayList<>());
    patternsList.get(pattern2).add(2);
    //patternsList.get(pattern2).addAll(Arrays.asList(0, 2, 5));
    patternsList.put(pattern3, new ArrayList<>());
    patternsList.get(pattern3).add(3);
    //patternsList.get(pattern3).addAll(Arrays.asList(0, 3, 5));
    patternsList.put(pattern4, new ArrayList<>());
    patternsList.get(pattern4).addAll(Arrays.asList(0, 4));
  }
}
