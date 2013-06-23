package com.creeaaakk.tools.concurrent;

public abstract class CountDownLatch
{
  private int count;

  public CountDownLatch(int count)
  {
    if (count < 0)
    {
      throw new IllegalArgumentException("Count must be at least 0");
    }

    this.count = count;
    checkZero();
  }

  public synchronized void countDown()
  {
    if (count > 0)
    {
      count--;
      checkZero();
    }
  }

  protected abstract void onZero();

  public int getCount()
  {
    return count;
  }

  private void checkZero()
  {
    if (count == 0)
    {
      onZero();
    }
  }
}
