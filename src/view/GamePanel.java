package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import model.Cell;
import model.Piece;

/**
 * Represents the panel to display the board.
 */
public class GamePanel extends JPanel {
  private Color CELL_COLOR = Color.CYAN; // color of the board
  private int cellSize;
  private Piece[][] prevGrid; // TODO used to undo a move / revert to the previous board state ?
  private Piece[][] grid;

  /**
   * Constructor for a {@code GamePanel} object.
   */
  public GamePanel() {
    this.cellSize = 0;
    this.prevGrid = new Piece[0][0];
    this.grid = new Piece[0][0];
  }

  /**
   * Stores the given grid as the grid of this panel.
   *
   * @param grid the grid to be displayed
   */
  public void setGrid(Piece[][] grid) {
    this.prevGrid = this.grid;
    this.grid = grid;
  }

  /**
   * Sets the panel's cell size to the given cell size.
   *
   * @param cellSize the given cell size
   */
  public void setCellSize(int cellSize) {
    this.cellSize = cellSize;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int pieceSize = cellSize / 2;

    int curY = 0;
    for (int row = 0; row < grid.length; row++) {
      Piece[] curRow = grid[row];
      int curX = 0;

      for (int col = 0; col < curRow.length; col++) {
        Piece curPiece = curRow[col];
        g2d.setColor(CELL_COLOR);
        Shape emptyCell = new Rectangle(curX, curY, cellSize, cellSize);
        g2d.fill(emptyCell);
        g2d.setColor(Color.BLACK); // outline cell
        g2d.draw(emptyCell);

        int offsetX = curX + cellSize / 4;
        int offsetY = curY + cellSize / 4;

        switch (curPiece) {
          case BLACK:
            g2d.setColor(Color.BLACK);
            Shape blackCell = new Ellipse2D.Double(offsetX, offsetY, pieceSize, pieceSize);
            g2d.fill(blackCell);
            g2d.setColor(Color.BLACK); // outline piece
            g2d.draw(blackCell);
            break;
          case WHITE:
            g2d.setColor(Color.WHITE);
            Shape whiteCell = new Ellipse2D.Double(offsetX, offsetY, pieceSize, pieceSize);
            g2d.fill(whiteCell);
            g2d.setColor(Color.BLACK); // outline piece
            g2d.draw(whiteCell);
            break;
          default:
            break;
        }
        curX = curX + cellSize;
      }
      curY = curY + cellSize;
    }
  }
}
