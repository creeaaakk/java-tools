package com.creeaaakk.tools.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.creeaaakk.tools.util.InCallback;
import com.creeaaakk.tools.util.Once;

public class ThreadedQueue<T>
{
  private static final long DEFAULT_TIMEOUT = 60 * 1000;

  public static volatile ExecutorService defaultPool;

  private final ExecutorService pool;
  private final BlockingQueue<T> queue = new LinkedBlockingQueue<T>();
  private final long timeoutMillis;

  private final Once setRunnable = new Once(new InCallback()
  {
    @SuppressWarnings("unchecked")
    @Override
    public void call(Object object)
    {
      runnable = (ThreadedRunnable<T>) object;
    }
  });

  private ThreadedRunnable<T> runnable;
  private Future<?> task;

  public ThreadedQueue(ThreadedRunnable<T> runnable)
  {
    this(runnable, defaultPool, DEFAULT_TIMEOUT);
  }

  public ThreadedQueue(ThreadedRunnable<T> runnable, ExecutorService pool)
  {
    this(runnable, pool, DEFAULT_TIMEOUT);
  }

  public ThreadedQueue(ThreadedRunnable<T> runnable, long timeoutMillis)
  {
    this(runnable, defaultPool, timeoutMillis);
  }

  public ThreadedQueue(ThreadedRunnable<T> runnable, ExecutorService pool, long timeoutMillis)
  {
    if (runnable == null)
    {
      throw new IllegalArgumentException("runnable is null");
    }

    if (timeoutMillis < 0)
    {
      throw new IllegalArgumentException("negative timeoutMillis: " + timeoutMillis);
    }

    if (pool == null)
    {
      pool = Executors.newCachedThreadPool();
    }

    this.pool = pool;
    this.runnable = runnable;
    this.timeoutMillis = timeoutMillis;
  }

  /**
   * Sets the runnable that will process the queued items. This can only be
   * set once.
   */
  public void setRunnable(ThreadedRunnable<T> runnable)
  {
    setRunnable.run(runnable);
  }

  public void enqueue(T item)
  {
    boolean put = false;

    while (!put)
    {
      try
      {
        queue.put(item);
        put = true;
      }
      catch (InterruptedException exception)
      {
      }
    }

    synchronized (queue)
    {
      if (task == null || task.isDone())
      {
        task = pool.submit(new Runnable()
        {
          @Override
          public void run()
          {
            boolean done = false;

            while (!done)
            {
              try
              {
                T item = queue.poll(timeoutMillis, TimeUnit.MILLISECONDS);

                if (item == null)
                {
                  done = true;
                }
                else
                {
                  runnable.run(item);
                }
              }
              catch (InterruptedException exception)
              {
              }
              catch (Exception exception)
              {
              }
            }

            synchronized (queue)
            {
              task = null;
            }
          }
        });
      }
    }
  }
}
