package edu.unc.comp301.zombies;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base implements IBase {

  private int supplyCount = 0;
  private boolean isUnderAttack = false;
  private final Lock toolLock = new ReentrantLock();

  @Override
  public synchronized void addSupplies(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be greater than zero.");
    }
    supplyCount += amount;
    System.out.println(
        Thread.currentThread().getName() + " added " + amount + " supplies. Total: " + supplyCount);
  }

  @Override
  public synchronized int getSupplyCount() {
    return supplyCount;
  }

  @Override
  public void useTool(String task) throws InterruptedException {
    toolLock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + " is using a tool.");
      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName() + " is done using a tool.");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      toolLock.unlock();
    }
  }

  public synchronized boolean isUnderAttack() {
    return isUnderAttack;
  }

  public synchronized void startAttack() {
    isUnderAttack = true;
    System.out.println("ZOMBIES ARE APPROACHING");
  }

  public synchronized void endAttack() {
    isUnderAttack = false;
    System.out.println("The zombies were successfully repelled!");
  }
}
