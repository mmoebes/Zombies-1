package edu.unc.comp301.zombies;

public class QuietDayStrategy implements IDayStrategy {

  @Override
  public void execute(Base base, int durationMilliseconds) throws InterruptedException {
    Thread.sleep(durationMilliseconds);
  }
}
