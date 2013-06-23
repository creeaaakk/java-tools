package com.creeaaakk.tools.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Enables a callback to occur when countDown() has been called
 * the specified number of times. This class is thread safe.
 */
public abstract class CountDownLatch
{
  private AtomicInteger count;

  /**
   * @param count number of times countDown() must be called before
   *              onZero() is called, if count == 0, onZero is called
   *              during construction
   */
  public CountDownLatch(int count)
  {
    if (count < 0)
    {
      throw new IllegalArgumentException("Count must be at least 0");
    }
    else if (count == 0)
    {
      onZero();
    }

    this.count = new AtomicInteger(count);
  }

  public void countDown()
  {
    if (count.decrementAndGet() == 0)
    {
      onZero();
    }
  }

  protected abstract void onZero();

  public int getCount()
  {
    return count.get();
  }
}
