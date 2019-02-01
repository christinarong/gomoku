import controller.ControllerImpl;
import controller.IController;
import view.IView;
import view.GameView;

/**
 * Starts a new game of gomoku.
 */
public class GomokuStart {

  public static void main(String[] args) {
    IView view = new GameView();
    IController controller = new ControllerImpl(view);
    controller.start();
  }
}
