package model.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.Cell;
import model.Coord;
import model.IBoard;
import model.IStrat;
import model.Piece;

/**
 * Represents a strategy based off of identifying unique patterns that pose a threat or opportunity
 * for advance (depending on your piece color). Each pattern strategy contains a different set of
 * patterns. Given the state of the board, this type of strategy iterates through each possible
 * grouping of cells to find one that matches a pattern in the list, which determines which cell
 * should be played. A player on defensive will search for patterns that allow the opponent to
 * advance (to be able to block them), while a player on offensive will search for patterns that
 * allow themself to advance.
 */
public abstract class AbsPatternStrat implements IStrat {
  private final boolean offensive;
  protected Map<Piece[], List<Integer>> patternsList;
  private final int numConnect;

  /**
   * Constructor for a pattern strategy object.
   *
   * @param offensive  whether the current player is on offensive or defensive
   * @param numConnect the number of cells in a grouping to check
   */
  public AbsPatternStrat(boolean offensive, int numConnect) {
    this.offensive = offensive;
    this.patternsList = new HashMap<>();
    this.numConnect = numConnect;
  }

  /**
   * Sets the list of patterns to check.
   *
   * @param pieceToCheck the piece color to check
   */
  public abstract void setPatternsList(Piece pieceToCheck);

  @Override
  public Coord chooseMove(IBoard board, Piece curPiece) {
    List<Cell[]> connectList = board.genConnectList(numConnect);
    Piece pieceToCheck = this.pieceToCheck(offensive, curPiece);
    this.setPatternsList(pieceToCheck);
    return this.checkEachConnect(connectList);
  }

  /**
   * Checks each grouping of cells in the given list against each pattern in the pattern list.
   *
   * @param connectList the list of cell groupings to check
   * @return the coordinate of the chosen cell if successful
   */
  private Coord checkEachConnect(List<Cell[]> connectList) {
    for (Cell[] connect : connectList) {
      for (Piece[] pattern : patternsList.keySet()) {
        if (this.matchPatternConnect(pattern, connect)) {
          int numCell = this.randomCoord(patternsList.get(pattern));
          return connect[numCell].getCoord();
        }
      }
    }
    return null;
  }

  /**
   * Determines which piece to check each pattern with, depending on offensive strategy.
   *
   * @param offensive whether the current player is on offensive
   * @param curPiece  the piece of the current player
   * @return which piece to check each pattern with
   */
  private Piece pieceToCheck(boolean offensive, Piece curPiece) {
    if (offensive) {
      return curPiece; // convert your connect 4 to connect 5
    } else {
      return Piece.opposite(curPiece); // block opponent connect of 4
    }
  }

  /**
   * Checks if the given grouping of cells matches the given pattern.
   *
   * @param pattern the pattern to check
   * @param connect the grouping of cells to check
   * @return whether the pattern and the grouping match or not
   */
  private boolean matchPatternConnect(Piece[] pattern, Cell[] connect) {
    if (pattern.length != connect.length) {
      throw new IllegalArgumentException("Cannot be different length!");
    }

    for (int i = 0; i < pattern.length; i++) {
      if (!pattern[i].equals(connect[i].getPiece())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Given a list of choices, randomly picks on from the list.
   *
   * @param choices the list of choices
   * @return a random choice
   */
  private Integer randomCoord(List<Integer> choices) {
    int rand = new Random().nextInt(choices.size());
    return choices.get(rand);
  }
}
