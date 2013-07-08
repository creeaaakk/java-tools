package com.creeaaakk.tools.util;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Thread safe wrapper for a Runnable, InCallback, OutCallback, or InOutCallback
 * that can only be called once. Regardless of the type wrapped, all of the
 * methods call the object. If no object is provided for an in-style wrapped
 * callback, a null is supplied. If a callback does not return a value but a
 * return value is expected in the call to the Once, null is returned.
 * 
 * In blocking mode, if multiple threads try to run the callback, they will
 * block until the callback has finished running.
 * 
 * In non-blocking mode, if multiple threads try to run the callback, the
 * superfluous threads will not block and instead do nothing.
 */
public class Once implements Runnable, InCallback, OutCallback, InOutCallback
{
  private final AtomicBoolean done = new AtomicBoolean(false);
  private final boolean blocking;

  private Runnable runnable;
  private InCallback inCallback;
  private OutCallback outCallback;
  private InOutCallback inOutCallback;

  public Once(Runnable runnable)
  {
    this(runnable, true);
  }

  public Once(InCallback inCallback)
  {
    this(inCallback, true);
  }

  public Once(OutCallback outCallback)
  {
    this(outCallback, true);
  }

  public Once(InOutCallback inOutCallback)
  {
    this(inOutCallback, true);
  }

  public Once(Runnable runnable, boolean blocking)
  {
    if (runnable == null)
    {
      throw new IllegalArgumentException("runnable is null");
    }

    this.runnable = runnable;
    this.blocking = blocking;
  }

  public Once(InCallback inCallback, boolean blocking)
  {
    if (inCallback == null)
    {
      throw new IllegalArgumentException("inCallback is null");
    }

    this.inCallback = inCallback;
    this.blocking = blocking;
  }

  public Once(OutCallback outCallback, boolean blocking)
  {
    if (outCallback == null)
    {
      throw new IllegalArgumentException("outCallback is null");
    }

    this.outCallback = outCallback;
    this.blocking = blocking;
  }

  public Once(InOutCallback inOutCallback, boolean blocking)
  {
    if (inOutCallback == null)
    {
      throw new IllegalArgumentException("inOutCallback is null");
    }

    this.inOutCallback = inOutCallback;
    this.blocking = blocking;
  }

  public void run()
  {
    callInOut(null);
  }

  @Override
  public void callIn(Object object)
  {
    callInOut(object);
  }

  @Override
  public Object callOut()
  {
    return callInOut(null);
  }

  @Override
  public Object callInOut(Object object)
  {
    if (blocking)
    {
      return executeBlocking(object);
    }
    else
    {
      return executeNonBlocking(object);
    }
  }

  private Object executeBlocking(Object object)
  {
    Object ret = null;

    if (!done.get())
    {
      synchronized (this)
      {
        if (!done.getAndSet(true))
        {
          ret = execute(object);
        }
      }
    }

    return ret;
  }

  private Object executeNonBlocking(Object object)
  {
    Object ret = null;

    if (!done.getAndSet(true))
    {
      ret = execute(object);
    }

    return ret;
  }

  private Object execute(Object object)
  {
    if (runnable != null)
    {
      runnable.run();
    }
    else if (inCallback != null)
    {
      inCallback.callIn(object);
    }
    else if (outCallback != null)
    {
      return outCallback.callOut();
    }
    else if (inOutCallback != null)
    {
      return inOutCallback.callInOut(object);
    }

    return null;
  }
}
