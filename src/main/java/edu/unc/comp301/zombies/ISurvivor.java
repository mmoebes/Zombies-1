package edu.unc.comp301.zombies;

public interface ISurvivor extends Runnable {
  void stop(); // Stops the survivor thread

  @Override
  void run(); // Defines how the survivor executes tasks asynchronously
}
