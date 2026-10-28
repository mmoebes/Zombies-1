package edu.unc.comp301.zombies;

public class Survivor implements ISurvivor {

  Base b = new Base();
  private volatile boolean running = true;
  private boolean defending = false;

  public Survivor(Base base) {
    this.b = base;
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
    if (defending) {
      System.out.println("Defending zombies!");
      Thread.sleep(2000);
      defending = false;
    } else {
      int random = (int) (Math.random() * 3);
      if (random == 0) {
        scavenge();
      } else if (random == 1) {
        fortify();
      } else {
        rest();
      }
    }
  }

  protected void fortify() throws InterruptedException {
    b.useTool("fortification");
    System.out.println(Thread.currentThread().getName() + " is fortifying.");
    Thread.sleep(2000);
  }

  protected void scavenge() throws InterruptedException {
    int sleep = (int) (Math.random() * 4000);
    System.out.println(Thread.currentThread().getName() + " is searching for supplies.");
    Thread.sleep(sleep);
    b.addSupplies(2);
  }

  protected void rest() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " is resting.");
    Thread.sleep(2000);
  }

  public boolean isDefending() {
    return defending;
  }
}
