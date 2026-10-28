package edu.unc.comp301.zombies;

public class RandomZombieAttacksStrategy implements IDayStrategy {

  @Override
  public void execute(Base base, int durationMilliseconds) throws InterruptedException {
    int attack = durationMilliseconds / 5;
    int remaining = durationMilliseconds - 2 * attack;
    int gap = remaining / 3;
    int lastGap = remaining - 2 * gap; // absorbs any rounding so the total is exact

    Thread.sleep(gap);

    base.startAttack();
    Thread.sleep(attack);
    base.endAttack();

    Thread.sleep(gap);

    base.startAttack();
    Thread.sleep(attack);
    base.endAttack();

    Thread.sleep(lastGap);
  }
}
