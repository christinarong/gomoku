package model;

import java.util.Objects;

/**
 * Implementation of an {@code IPlayer} object.
 */
public class PlayerImpl implements IPlayer {
  private final String name;
  private final Piece piece;
  private boolean isComputer;

  /**
   * Constructor for a {@code PlayerImpl} object.
   *
   * @param name  the name of the player
   * @param piece the piece color of the player
   */
  public PlayerImpl(String name, Piece piece) {
    this.name = name;
    this.piece = piece;
    this.isComputer = false;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Piece getPiece() {
    return this.piece;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof PlayerImpl)) {
      return false;
    }

    PlayerImpl that = (PlayerImpl) obj;
    boolean sameName = this.getName().equals(that.getName());
    boolean samePiece = this.getPiece().equals(that.getPiece());
    return sameName && samePiece;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.piece);
  }
}
