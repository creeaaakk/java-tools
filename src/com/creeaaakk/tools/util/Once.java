package com.creeaaakk.tools.util;

public class Once implements Runnable
{
  private Runnable runnable;
  private InCallback inCallback;
  private OutCallback outCallback;
  private InOutCallback inOutCallback;
  private volatile boolean done = false;

  public Once(Runnable runnable)
  {
    if (runnable == null)
    {
      throw new IllegalArgumentException("runnable is null");
    }

    this.runnable = runnable;
  }

  public Once(InCallback inCallback)
  {
    if (inCallback == null)
    {
      throw new IllegalArgumentException("inCallback is null");
    }

    this.inCallback = inCallback;
  }

  public Once(OutCallback outCallback)
  {
    if (outCallback == null)
    {
      throw new IllegalArgumentException("outCallback is null");
    }

    this.outCallback = outCallback;
  }

  public Once(InOutCallback inOutCallback)
  {
    if (inOutCallback == null)
    {
      throw new IllegalArgumentException("inOutCallback is null");
    }

    this.inOutCallback = inOutCallback;
  }

  public void run()
  {
    run(null);
  }

  public Object run(Object object)
  {
    Object ret = null;

    if (!done)
    {
      synchronized (this)
      {
        if (!done)
        {
          ret = call(object);
          done = true;
        }
      }
    }

    return ret;
  }

  private Object call(Object object)
  {
    if (runnable != null)
    {
      runnable.run();
    }
    else if (inCallback != null)
    {
      inCallback.call(object);
    }
    else if (outCallback != null)
    {
      return outCallback.call();
    }
    else if (inOutCallback != null)
    {
      return inOutCallback.call(object);
    }

    return null;
  }
}
