package model.strategies;

import model.Coord;
import model.IBoard;
import model.IStrat;
import model.Piece;

// work in progress : scoring potential moves ?
public class ScoreStrat implements IStrat {
  int[][] scoreGrid;

  public ScoreStrat() {
    this.scoreGrid = new int[19][19];
  }

  @Override
  public Coord chooseMove(IBoard board, Piece curPiece) {
    int bestRow = 0;
    int bestCol = 0;
    for (int row = 0; row < scoreGrid.length; row++) {
      int[] curRow = scoreGrid[row];
      for (int col = 0; col < curRow.length; col++) {
        if (scoreGrid[row][col] > scoreGrid[bestRow][bestCol]) {
          bestRow = row;
          bestCol = col;
        }
      }
    }
    return new Coord(bestRow, bestCol);
  }
}
