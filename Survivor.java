package edu.unc.comp301.zombies;

import java.util.Random;

public class Survivor implements ISurvivor {
  private final Base base;
  private final Random random = new Random();
  private volatile boolean running = true;
  private volatile boolean defending = false;

  public Survivor(Base base) {
    this.base = base;
  }

  protected void fortify() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " is fortifying the base.");
    base.useTool("fortification");
    Thread.sleep(2000);
  }

  protected void scavenge() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " is scavenging for supplies.");
    Thread.sleep(1000 + random.nextInt(3001)); // 1 to 4 seconds
    base.addSupplies(2);
  }

  protected void rest() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " is resting.");
    Thread.sleep(2000);
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

  public boolean isDefending() {
    return defending;
  }

  protected void performAction() throws InterruptedException {
    if (base.isUnderAttack()) {
      defend();
      return;
    }
    switch (random.nextInt(3)) {
      case 0:
        scavenge();
        break;
      case 1:
        fortify();
        break;
      default:
        rest();
        break;
    }
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

  @Override
  public void stop() {
    running = false;
  }
}
