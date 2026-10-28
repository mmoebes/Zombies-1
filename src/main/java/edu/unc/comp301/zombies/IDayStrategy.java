package edu.unc.comp301.zombies;

public interface IDayStrategy {
  void execute(Base base, int durationMilliseconds) throws InterruptedException;
}
