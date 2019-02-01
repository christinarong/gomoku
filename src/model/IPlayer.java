package model;

/**
 * Represents a player in the game.
 */
public interface IPlayer {

  /**
   * Gets the name of this player.
   *
   * @return the name of the player
   */
  String getName();

  /**
   * Gets the piece color of this player.
   *
   * @return the piece color
   */
  Piece getPiece();
}
