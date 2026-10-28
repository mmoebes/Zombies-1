package edu.unc.comp301.zombies;

public class RandomZombieAttacksStrategy implements IDayStrategy {

  @Override
  public void execute(Base base, int durationMilliseconds) throws InterruptedException {
    int gap = durationMilliseconds / 5;
    int f = durationMilliseconds - 4*gap;

    Thread.sleep(gap);
    base.startAttack();
    Thread.sleep(gap);
    base.endAttack();
    Thread.sleep(gap);
    base.startAttack();
    Thread.sleep(gap);
    base.endAttack();
    Thread.sleep(f);
  }
}
