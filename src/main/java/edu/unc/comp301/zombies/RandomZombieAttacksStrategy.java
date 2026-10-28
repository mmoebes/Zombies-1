package edu.unc.comp301.zombies;

public class RandomZombieAttacksStrategy implements IDayStrategy {

  @Override
  public void execute(Base base, int durationMilliseconds) throws InterruptedException {
    Base b = new Base();
    int gap = durationMilliseconds / 5;
    Thread.sleep(gap);
    b.startAttack();
    Thread.sleep(gap);
    b.endAttack();
    Thread.sleep(gap);
    b.startAttack();
    Thread.sleep(gap);
    b.endAttack();
    Thread.sleep(gap);
  }
}
