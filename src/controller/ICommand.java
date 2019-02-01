package controller;

/**
 * Represents an action upon a button or mouse event being called.
 */
public interface ICommand {

  /**
   * Execute the command.
   */
  void apply();
}
