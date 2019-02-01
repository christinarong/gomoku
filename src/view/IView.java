package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import model.Piece;

/**
 * Represents the GUI.
 */
public interface IView {

  /**
   * Sets the grid of this view to the given grid.
   *
   * @param grid the given grid
   */
  void setGrid(Piece[][] grid);

  /**
   * Sets the size of this View.
   *
   * @param numRows height of the board
   * @param numCols width of the board
   */
  void setSize(int numRows, int numCols, int cellSize);

  /**
   * Sets the message to be displayed.
   *
   * @param message the message
   */
  void setGameMessage(String message);

  /**
   * Creates a panel to prompt the user to choose an opponent before starting.
   */
  void setStartPanel();

  /**
   * Creates a panel to display the board and allow players to play.
   */
  void setGamePanel();

  /**
   * Sets the mouse listener of this view to the given mouse listener.
   *
   * @param mlistener the mouse listener to add
   */
  void setMouseListener(MouseListener mlistener, boolean active);

  /**
   * Sets the action listener of every button to the given action listener.
   *
   * @param alistener the action listener to add
   */
  void setButtonListener(ActionListener alistener, boolean active);

  /**
   * Refreshes this view by repainting the grid.
   */
  void refresh();

  /**
   * Makes this view visible to the user.
   */
  void makeVisible();
}
