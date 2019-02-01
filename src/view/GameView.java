package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;

import model.Piece;

/**
 * Implementation of an {@code IView} object.
 */
public class GameView implements IView {
  private JFrame mainFrame; // the entire window displayed
  private JPanel startPanel; // the buttons displayed
  private JPanel gamePanel; // the center panel (changes, start or board)
  private JLabel messageLabel; // the current message displayed (changes)
  private GamePanel boardPanel; // the panel displaying the board
  private List<JButton> buttons;

  /**
   * Constructor of a {@code GameView} object.
   */
  public GameView() {
    this.buttons = new ArrayList<>();
    this.initMainFrame();
    this.initInfoPanel();
    this.initStartPanel();
    this.initGamePanel();
    this.setStartPanel();
  }

  /**
   * Creates a window for the game to be displayed.
   */
  private void initMainFrame() {
    mainFrame = new JFrame();
    mainFrame.setTitle("Gomoku");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(new BorderLayout());
    mainFrame.setResizable(false);
  }

  @Override
  public void setStartPanel() {
    mainFrame.remove(gamePanel);
    mainFrame.add(startPanel, BorderLayout.CENTER);

    StringBuilder text = new StringBuilder();
    text.append("<html><center>");
    text.append("Welcome to Gomoku!<br/><br/>");
    text.append("Players take turns putting pieces on the board.<br/>");
    text.append("The goal is to get five in a row!<br/><br/>");
    text.append("Choose whether you'd like to play against<br/>" +
            "the computer (one-player) or a friend (two-player):");
    text.append("</center></html>");
    this.setGameMessage(text.toString());
  }

  @Override
  public void setGamePanel() {
    mainFrame.remove(startPanel);
    mainFrame.add(gamePanel, BorderLayout.CENTER);

    this.setGameMessage("Gomoku has started!");
  }

  /**
   * Creates a panel for messages to be displayed.
   */
  private void initInfoPanel() {
    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    mainFrame.add(infoPanel, BorderLayout.NORTH);

    this.messageLabel = new JLabel();
    this.setGameMessage("Welcome to Gomoku!");
    infoPanel.add(messageLabel);
  }

  /**
   * Creates a panel to prompt the user to choose an opponent.
   */
  private void initStartPanel() {
    startPanel = new JPanel();
    startPanel.setLayout(new FlowLayout());
    startPanel.setPreferredSize(new Dimension(400, 50));

    JButton onePlayerButton = new JButton("one player");
    onePlayerButton.setActionCommand("one player");
    buttons.add(onePlayerButton);
    startPanel.add(onePlayerButton);

    JButton twoPlayerButton = new JButton("two player");
    twoPlayerButton.setActionCommand("two player");
    buttons.add(twoPlayerButton);
    startPanel.add(twoPlayerButton);
  }

  /**
   * Creates a panel for the board to be displayed.
   */
  private void initGamePanel() {
    gamePanel = new JPanel();
    gamePanel.setLayout(new BorderLayout());

    this.boardPanel = new GamePanel();
    JScrollPane scrollPane = new JScrollPane(boardPanel);
    gamePanel.add(scrollPane, BorderLayout.CENTER);
    gamePanel.add(boardPanel, BorderLayout.CENTER);

    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
    gamePanel.add(controlPanel, BorderLayout.SOUTH);

    // button to start a new game
    JButton restartButton = new JButton("restart");
    restartButton.setActionCommand("restart");
    buttons.add(restartButton);
    controlPanel.add(restartButton);

    // button to display instructions
    JButton aboutButton = new JButton("about");
    aboutButton.setActionCommand("about");
    buttons.add(aboutButton);
    controlPanel.add(aboutButton);
  }

  @Override
  public void setGrid(Piece[][] grid) {
    boardPanel.setGrid(grid);
  }

  @Override
  public void setSize(int numRows, int numCols, int cellSize) {
    int widthX = numCols * cellSize;
    int heightY = numRows * cellSize;
    boardPanel.setPreferredSize(new Dimension(widthX, heightY));
    boardPanel.setCellSize(cellSize);
  }

  @Override
  public void setGameMessage(String message) {
    messageLabel.setText(message);
  }

  @Override
  public void setMouseListener(MouseListener mlistener, boolean active) {
    if (active) {
      boardPanel.addMouseListener(mlistener);
    } else {
      boardPanel.removeMouseListener(mlistener);
    }
  }

  @Override
  public void setButtonListener(ActionListener alistener, boolean active) {
    for (JButton obj : buttons) {
      if (active) {
        obj.addActionListener(alistener);
      } else {
        obj.removeActionListener(alistener);
      }
    }
  }

  @Override
  public void refresh() {
    mainFrame.pack(); // is this necessary?
    mainFrame.repaint();
  }

  @Override
  public void makeVisible() {
    mainFrame.pack();
    mainFrame.setVisible(true);
  }

}
