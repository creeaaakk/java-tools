/*
 * Copyright (c) 2013, Creeaaakk Ware
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Creeaaakk Ware nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
    public void callIn(Object object)
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
    if (timeoutMillis < 0)
    {
      throw new IllegalArgumentException("negative timeoutMillis: " + timeoutMillis);
    }

    if (runnable != null)
    {
      setRunnable(runnable);
    }

    if (pool == null)
    {
      pool = Executors.newCachedThreadPool();
    }

    this.pool = pool;
    this.timeoutMillis = timeoutMillis;
  }

  /**
   * Sets the runnable that will process the queued items. This can only be
   * set once.
   */
  public void setRunnable(ThreadedRunnable<T> runnable)
  {
    setRunnable.callIn(runnable);
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
