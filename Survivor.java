package edu.unc.comp301.zombies;

public class Survivor implements ISurvivor {

  private final Base base;
  private volatile boolean running = true;
  private volatile boolean defending = false;

  public Survivor(Base base) {
    this.base = base;
  }

  @Override
  public void stop() {
    running = false;
  }

  @Override
  public void run() {
    while (running) {
      try {
        performAction();
      } catch (InterruptedException e) {
        running = false;
        Thread.currentThread().interrupt();
      }
    }
  }

  protected void performAction() throws InterruptedException {
    if (base.isUnderAttack()) {
      defend();
      return;
    }
    int random = (int) (Math.random() * 3);
    if (random == 0) {
      scavenge();
    } else if (random == 1) {
      fortify();
    } else {
      rest();
    }
  }

  protected void defend() throws InterruptedException {
    defending = true;
    try {
      System.out.println(Thread.currentThread().getName() + " is defending the base from zombies!");
      Thread.sleep(2000);
    } finally {
      defending = false;
    }
  }

  protected void fortify() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " is fortifying the base.");
    base.useTool("fortification");
    Thread.sleep(2000);
  }

  protected void scavenge() throws InterruptedException {
    int sleep = 1000 + (int) (Math.random() * 3001); // 1 to 4 seconds
    System.out.println(Thread.currentThread().getName() + " is scavenging for supplies.");
    Thread.sleep(sleep);
    base.addSupplies(2);
  }

  protected void rest() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " is resting.");
    Thread.sleep(2000);
  }

  public boolean isDefending() {
    return defending;
  }
}
