package model;

import java.util.ArrayList;
import java.util.List;

import model.strategies.LevelStrat;

/**
 * Implementation of a Board with a default size of 19x19. Stores information about the players and
 * the state of this board.
 */
public class BoardImpl implements IBoard {
  private final int SIZE = 19;
  private final int width;
  private final int height;
  private final boolean computerOpponent;
  private Cell[][] grid;
  private IPlayer curPlayer;
  private IPlayer player1;
  private IPlayer player2;
  private IStrat computerStrategy;

  public BoardImpl(boolean computerOpponent, String name1, String name2) {
    this.width = SIZE;
    this.height = SIZE;
    this.computerOpponent = computerOpponent;
    this.computerStrategy = new LevelStrat("hard");

    Piece startPiece = Piece.randomPiece();
    this.player1 = new PlayerImpl(name1, startPiece);
    this.player2 = new PlayerImpl(name2, Piece.opposite(startPiece));
    this.curPlayer = player1;
    this.initBoard();
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public boolean getComputerOpponent() {
    return this.computerOpponent;
  }

  @Override
  public Cell getCell(int row, int col) {
    return grid[row][col];
  }

  @Override
  public Piece getPiece(Coord coord) {
    return grid[coord.getRow()][coord.getCol()].getPiece();
  }

  @Override
  public String getCurPlayer() {
    return curPlayer.getName();
  }

  @Override
  public String getCurPiece() {
    return curPlayer.getPiece().toString();
  }

  @Override
  public Piece[][] getCopyOfGrid() {
    Piece[][] copyOfGrid = new Piece[this.height][this.width];
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        copyOfGrid[row][col] = grid[row][col].getPiece();
      }
    }
    return copyOfGrid;
  }

  @Override
  public void initBoard() {
    this.grid = new Cell[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        grid[row][col] = new Cell(row, col, Piece.EMPTY);
      }
    }
  }

  @Override
  public void computerMove() {
    Coord coord = computerStrategy.chooseMove(this, curPlayer.getPiece());
    try {
      this.placePiece(coord.getRow(), coord.getCol());
    } catch (IllegalArgumentException e) {
      this.computerMove();
    }
  }

  @Override
  public void placePiece(int row, int col) throws IllegalArgumentException {
    if (grid[row][col].containsPiece(Piece.EMPTY)) {
      grid[row][col].setPiece(curPlayer.getPiece());
      if (!this.isGameOver()) {
        this.togglePlayer();
      }
    } else {
      throw new IllegalArgumentException("Cell is already occupied!");
    }
  }

  /**
   * Toggles the current player to the other player.
   *
   * @throws IllegalArgumentException if the current player does not exist
   */
  private void togglePlayer() throws IllegalArgumentException {
    if (curPlayer.equals(player1)) {
      this.curPlayer = player2;
    } else if (curPlayer.equals(player2)) {
      this.curPlayer = player1;
    } else {
      throw new IllegalArgumentException("Current player must be either player 1 or 2!");
    }
  }

  @Override
  public boolean isGameOver() {
    List<Cell[]> connectList = this.genConnectList(5);
    for (Cell[] connect : connectList) {
      if (this.checkConnect(connect, curPlayer.getPiece())) {
        return true;
      }
    }
    return this.boardIsFull();
  }

  /**
   * Checks whether every cell on this board is not empty. (indicates game over)
   *
   * @return whether the board is full or not
   */
  private boolean boardIsFull() {
    int emptyCount = 0;
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        if (grid[row][col].containsPiece(Piece.EMPTY)) {
          emptyCount++;
        }
      }
    }
    return emptyCount == 0;
  }

  @Override
  public List<Cell[]> genConnectList(int numConnect) {
    List<Cell[]> connectList = new ArrayList<>();
    int offset = numConnect - 1;
    // horizontal
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth() - offset; col++) {
        Cell[] curConnect = new Cell[numConnect];
        for (int i = 0; i < numConnect; i++) {
          curConnect[i] = this.getCell(row, col + i);
        }
        connectList.add(curConnect);
      }
    }
    // vertical
    for (int row = 0; row < this.getHeight() - offset; row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        Cell[] curConnect = new Cell[numConnect];
        for (int i = 0; i < numConnect; i++) {
          curConnect[i] = this.getCell(row + i, col);
        }
        connectList.add(curConnect);
      }
    }
    // forward diagonal
    for (int row = 0; row < this.getHeight() - offset; row++) {
      for (int col = 0; col < this.getWidth() - offset; col++) {
        Cell[] curConnect = new Cell[numConnect];
        for (int i = 0; i < numConnect; i++) {
          curConnect[i] = this.getCell(row + i, col + i);
        }
        connectList.add(curConnect);
      }
    }
    // backward diagonal
    for (int row = 0; row < this.getHeight() - offset; row++) {
      for (int col = offset; col < this.getWidth(); col++) {
        Cell[] curConnect = new Cell[numConnect];
        for (int i = 0; i < numConnect; i++) {
          curConnect[i] = this.getCell(row + i, col - i);
        }
        connectList.add(curConnect);
      }
    }
    return connectList;
  }

  /**
   * Checks whether each cell in the given grouping matches the given piece color.
   *
   * @param connect    a grouping of cells
   * @param pieceColor the piece color to check
   * @return whether there is a row of a given piece color
   */
  private boolean checkConnect(Cell[] connect, Piece pieceColor) {
    if (!connect[0].containsPiece(pieceColor)) {
      return false;
    }

    for (int cell = 0; cell < connect.length - 1; cell++) {
      boolean pairCheck = connect[cell].samePiece(connect[cell + 1]);
      if (!pairCheck) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void setStrategy(IStrat strat) {
    this.computerStrategy = strat;
  }
}
