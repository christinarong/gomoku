package model.strategies;

import java.util.Random;

import model.Coord;
import model.IBoard;
import model.IStrat;
import model.Piece;

/**
 * Strategy to randomly generate a cell on the board.
 */
public class RandStrat implements IStrat {
  private Random rand;
  private int numFromCenter;

  /**
   * Constructor for a {@code RandStrat} object.
   *
   * @param numFromCenter determines range of cells to choose from
   */
  public RandStrat(int numFromCenter) {
    this.rand = new Random();
    this.numFromCenter = numFromCenter;
  }

  /**
   * Default constructor that selected the entire board as the range.
   */
  public RandStrat() {
    this(18); // hardcoded ?
  }

  @Override
  public Coord chooseMove(IBoard board, Piece curPiece) {
    Coord randCoord = this.genRandomCoord(board.getWidth(), board.getHeight());
    if (board.getPiece(randCoord).equals(Piece.EMPTY)) {
      return randCoord;
    } else {
      return this.chooseMove(board, curPiece);
    }
  }

  /**
   * Generates a random coordinate given the width and height of the board.
   *
   * @param width  the width of the board
   * @param height the height of the board
   * @return a random coordinate
   */
  private Coord genRandomCoord(int width, int height) {
    int midWidth = width / 2;
    int midHeight = height / 2;
    int offset = numFromCenter;
    int row = rand.nextInt(offset) + midHeight - offset / 2;
    int col = rand.nextInt(offset) + midWidth - offset / 2;
    return new Coord(row, col);
  }
}
