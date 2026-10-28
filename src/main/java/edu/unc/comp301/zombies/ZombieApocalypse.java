package edu.unc.comp301.zombies;

public class ZombieApocalypse {

  // protected static Base base;
  protected static Survivor[] survivors;
  protected static Thread[] survivorThreads;
  private static Base base = new Base();

  public static void main(String[] args) {
    startSimulation(5);
    simulateDayNightCycle(10000, new RandomZombieAttacksStrategy());
    endSimulation();
  }

  public static void startSimulation(int numSurvivors) {
    survivors = new Survivor[numSurvivors];
    survivorThreads = new Thread[numSurvivors];

    for (int i = 0; i < numSurvivors; i++) {
      survivors[i] = new Survivor(base);
      survivorThreads[i] = new Thread(survivors[i], "Survivor-" + (i + 1));
      survivorThreads[i].start();
    }
  }

  public static void simulateDayNightCycle(int milliseconds, IDayStrategy events) {
    try {
      events.execute(base, milliseconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public static void endSimulation() {
    Survivor s = new Survivor(base);
    System.out.println("Simulation ending...");
    for (Survivor survivor : survivors) {
      survivor.stop();
    }

    for (Thread thread : survivorThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    System.out.println("All survivors have stopped. Simulation over.");
  }
}
