package com.creeaaakk.tools.concurrent;

import java.util.concurrent.ExecutorService;

public class ThreadedRunnableQueue extends ThreadedQueue<Runnable>
{
  private static final ThreadedRunnable<Runnable> threadedRunnable = new ThreadedRunnable<Runnable>()
  {
    @Override
    public void run(Runnable item)
    {
      item.run();
    }
  };

  public ThreadedRunnableQueue()
  {
    super(threadedRunnable);
  }

  public ThreadedRunnableQueue(ExecutorService pool)
  {
    super(threadedRunnable, pool);
  }

  public ThreadedRunnableQueue(long timeoutMillis)
  {
    super(threadedRunnable, timeoutMillis);
  }

  public ThreadedRunnableQueue(ExecutorService pool, long timeoutMillis)
  {
    super(threadedRunnable, pool, timeoutMillis);
  }
}
