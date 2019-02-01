package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.BoardImpl;
import model.IBoard;
import model.Piece;
import view.IView;

/**
 * Implementation of an {@code IController} object. Prompts the user to choose either a human
 * opponent or a computer opponent before rendering the board and starting a new game. Users can
 * click a button to choose a new opponent and restart.
 */
public class ControllerImpl extends MouseAdapter implements IController, ActionListener {
  private final IView view;
  private IBoard board;
  private int cellSize;
  private Timer timer;
  private Map<String, ICommand> commands;

  /**
   * Constructor of a {@code ControllerImpl} object.
   *
   * @param view the view of the game
   */
  public ControllerImpl(IView view) {
    this.view = view;
    this.cellSize = 50;
    this.commands = new HashMap<>();
    this.setCommands();
    this.timer = new Timer(1000, this);
    timer.setActionCommand("timer delay");
    timer.setRepeats(false);
  }

  @Override
  public void start() {
    this.setButtonListener(true);
    view.makeVisible();
  }

  /**
   * Sets the size of the board and the mouse and button listeners before rendering the board.
   */
  private void initBoard() {
    view.setSize(board.getHeight(), board.getWidth(), cellSize);
    this.refreshBoard();
    this.setMouseListener(false);
    this.setMouseListener(true);
    this.setButtonListener(false);
    this.setButtonListener(true);
  }

  /**
   * Re-renders the state of the board.
   */
  private void refreshBoard() {
    Piece[][] grid = board.getCopyOfGrid();
    view.setGrid(grid);
    view.setGameMessage(curPlayerString());
    view.refresh();
  }

  /**
   * Set all of the buttons' listeners to this controller.
   *
   * @param active whether the listener is active or not
   */
  private void setButtonListener(boolean active) {
    view.setButtonListener(this, active);
  }

  /**
   * Set the mouse listener to this controller.
   *
   * @param active whether the listener is active or not
   */
  private void setMouseListener(boolean active) {
    view.setMouseListener(this, active);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int curX = e.getX();
    int curY = e.getY();
    int curRow = curY / cellSize;
    int curCol = curX / cellSize;
    if (!this.isCellInvalid(curRow, curCol)) {
      try {
        board.placePiece(curRow, curCol);
        this.refreshBoard();
        this.setMouseListener(false);
        this.setButtonListener(false);

        if (board.isGameOver()) {
          view.setGameMessage("Game over! " + board.getCurPlayer() + " wins!");
          this.setButtonListener(true);
        } else {
          if (board.getComputerOpponent()) {
            board.computerMove();
            timer.start();
          } else {
            this.setMouseListener(true);
            this.setButtonListener(true);
          }
        }

      } catch (IllegalArgumentException ex) {
        this.messageDialog(ex.getMessage());
      }
    }
  }

  /**
   * Create a message specifying who's the current player.
   *
   * @return the message specifying the current player
   */
  private String curPlayerString() {
    return "Current player: " + board.getCurPlayer() + " (" + board.getCurPiece() + ")";
  }

  /**
   * Checks if the given row and column of a cell is invalid (out of bounds).
   *
   * @param row the row of a cell
   * @param col the column of a cell
   * @return whether the cell is invalid
   */
  private boolean isCellInvalid(int row, int col) {
    boolean invalidRow = row < 0 || row >= board.getHeight();
    boolean invalidCol = col < 0 || col >= board.getWidth();
    return invalidRow || invalidCol;
  }

  /**
   * Creates a dialog box to display the given message.
   *
   * @param message the message to display
   */
  private void messageDialog(String message) {
    JOptionPane.showMessageDialog(new JFrame(), message);
  }

  /**
   * Maps all supported commands with their corresponding action command string.
   */
  private void setCommands() {
    commands.put("one player", new OnePlayerCommand());
    commands.put("two player", new TwoPlayerCommand());
    commands.put("restart", new RestartCommand());
    commands.put("about", new AboutCommand());
    commands.put("timer delay", new TimerDelayCommand());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    commands.get(e.getActionCommand()).apply();
  }

  /**
   * Command that initializes the board with a computer opponent.
   */
  private final class OnePlayerCommand implements ICommand {

    /**
     * Randomly determines whether the human player or computer goes first.
     *
     * @return whether the computer goes first
     */
    private boolean doesComputerStart() {
      int rand = new Random().nextInt(2);
      return rand == 0;
    }

    @Override
    public void apply() {
      view.setGamePanel();

      if (this.doesComputerStart()) {
        board = new BoardImpl(true, "Computer", "Player");
        initBoard();
        board.computerMove();
        refreshBoard();
      } else {
        board = new BoardImpl(true, "Player", "Computer");
        initBoard();
      }
    }
  }

  /**
   * Command that initializes the board for two human opponents.
   */
  private final class TwoPlayerCommand implements ICommand {

    @Override
    public void apply() {
      board = new BoardImpl(false, "Player 1", "Player 2");
      view.setGamePanel();
      initBoard();
    }
  }

  /**
   * Command to start a new game with a prompt to choose the opponent.
   */
  private final class RestartCommand implements ICommand {

    @Override
    public void apply() {
      view.setStartPanel();
      view.refresh();
      setMouseListener(false);
    }
  }

  /**
   * Command to display instructions on how the game is played.
   */
  private final class AboutCommand implements ICommand {

    @Override
    public void apply() {
      StringBuilder text = new StringBuilder();
      text.append("<html>");
      text.append("Welcome to Gomoku!<br/><br/>");
      text.append("Players take turns putting pieces on the board.<br/>");
      text.append("The goal is to get five in a row!<br/><br/>");
      text.append("</html>");
      JOptionPane.showMessageDialog(new JFrame(), text.toString());
    }
  }

  /**
   * Command to delay the time while the computer is playing.
   */
  private final class TimerDelayCommand implements ICommand {

    @Override
    public void apply() {
      refreshBoard();
      timer.stop();
      if (board.isGameOver()) {
        view.setGameMessage("Game over! " + board.getCurPlayer() + " wins!");
        setButtonListener(true);
      } else {
        setMouseListener(true);
        setButtonListener(true);
      }

    }
  }
}
