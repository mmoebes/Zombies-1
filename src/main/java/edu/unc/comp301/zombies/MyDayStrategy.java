package edu.unc.comp301.zombies;

public class MyDayStrategy implements IDayStrategy {

  @Override
  public void execute(Base base, int durationMilliseconds) throws InterruptedException {
    Base b = new Base();
    b.startAttack();
    Thread.sleep(durationMilliseconds);
    b.endAttack();
  }
}
