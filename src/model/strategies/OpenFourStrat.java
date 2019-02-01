package model.strategies;

import java.util.ArrayList;

import model.Piece;

/**
 * Pattern strategy that checks for a four in a row with (at least) one open ending. An offensive
 * player would add onto the four in a row to create a five in a row (and win the game). A defensive
 * player would block the four in a row to prevent the opponent from winning.
 */
public class OpenFourStrat extends AbsPatternStrat {

  /**
   * Constructor for a {@code OpenFourStrat} object.
   *
   * @param offensive whether playing offensive or not
   */
  public OpenFourStrat(boolean offensive) {
    super(offensive, 5);
  }

  @Override
  public void setPatternsList(Piece pieceToCheck) {
    Piece P = pieceToCheck;
    Piece E = Piece.EMPTY;
    Piece[] pattern1 = {E, P, P, P, P};
    Piece[] pattern2 = {P, E, P, P, P};
    Piece[] pattern3 = {P, P, E, P, P};
    Piece[] pattern4 = {P, P, P, E, P};
    Piece[] pattern5 = {P, P, P, P, E};
    patternsList.put(pattern1, new ArrayList<>());
    patternsList.get(pattern1).add(0);
    patternsList.put(pattern2, new ArrayList<>());
    patternsList.get(pattern2).add(1);
    patternsList.put(pattern3, new ArrayList<>());
    patternsList.get(pattern3).add(2);
    patternsList.put(pattern4, new ArrayList<>());
    patternsList.get(pattern4).add(3);
    patternsList.put(pattern5, new ArrayList<>());
    patternsList.get(pattern5).add(4);
  }
}
