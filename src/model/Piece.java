package model;

import java.util.Random;

/**
 * Represents a cell on a board as empty, black, or white.
 */
public enum Piece {
  EMPTY("EMPTY"), BLACK("BLACK"), WHITE("WHITE");

  private final String type;

  /**
   * Constructor for a {@code Piece} object.
   *
   * @param type the description of cell
   */
  Piece(String type) {
    this.type = type;
  }

  /**
   * Return the cell corresponding to the given type string.
   *
   * @param type the given type string
   * @return the corresponding cell
   * @throws IllegalArgumentException if given type is not supported (black or white only)
   */
  public static Piece fromString(String type) {
    switch (type) {
      case "black":
        return Piece.BLACK;
      case "white":
        return Piece.WHITE;
      default:
        throw new IllegalArgumentException("Given type not supported!");
    }
  }

  /**
   * Returns the opposite piece to the given piece (the other one).
   *
   * @param curPiece the given piece
   * @return the opposite piece
   * @throws IllegalArgumentException if the given type does not exist (black or white only)
   */
  public static Piece opposite(Piece curPiece) {
    switch (curPiece) {
      case BLACK:
        return Piece.WHITE;
      case WHITE:
        return Piece.BLACK;
      default:
        throw new IllegalArgumentException("Given cell does not have an opposite!");
    }
  }

  /**
   * Randomly returns one of the supported pieces with a 50/50 chance.
   *
   * @return one of the pieces supported (black or white)
   */
  public static Piece randomPiece() {
    int rand = new Random().nextInt(1); // TODO currently hardcoded to BLACK
    switch (rand) {
      case 0:
        return Piece.BLACK;
      case 1:
        return Piece.WHITE;
      default:
        throw new IllegalArgumentException("shouldn't be here!");
    }
  }

  @Override
  public String toString() {
    return type;
  }
}
