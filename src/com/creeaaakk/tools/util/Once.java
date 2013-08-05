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

package com.creeaaakk.tools.util;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Thread safe wrapper for a Runnable, InCallback, OutCallback, or InOutCallback
 * that can only be called once. Regardless of the type wrapped, all of the
 * methods call the object. If no object is provided for an in-style wrapped
 * callback, a null is supplied. If a callback does not return a value but a
 * return value is expected in the call to the Once, null is returned.
 * 
 * In blocking mode (default), if multiple threads try to run the callback, they
 * will block until the callback has finished running.
 * 
 * In non-blocking mode, if multiple threads try to run the callback, the
 * superfluous threads will not block and instead do nothing.
 * 
 * Superfluous calls that expect to return a value return null.
 */
public class Once implements Runnable, InCallback, OutCallback, InOutCallback
{
  private final AtomicBoolean doneNonBlocking;
  private final Runnable runnable;
  private final InCallback inCallback;
  private final OutCallback outCallback;
  private final InOutCallback inOutCallback;

  private boolean doneBlocking = false;

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
    this.inCallback = null;
    this.outCallback = null;
    this.inOutCallback = null;
    this.doneNonBlocking = blocking ? null : new AtomicBoolean(false);
  }

  public Once(InCallback inCallback, boolean blocking)
  {
    if (inCallback == null)
    {
      throw new IllegalArgumentException("inCallback is null");
    }

    this.runnable = null;
    this.inCallback = inCallback;
    this.outCallback = null;
    this.inOutCallback = null;
    this.doneNonBlocking = blocking ? null : new AtomicBoolean(false);
  }

  public Once(OutCallback outCallback, boolean blocking)
  {
    if (outCallback == null)
    {
      throw new IllegalArgumentException("outCallback is null");
    }

    this.runnable = null;
    this.inCallback = null;
    this.outCallback = outCallback;
    this.inOutCallback = null;
    this.doneNonBlocking = blocking ? null : new AtomicBoolean(false);
  }

  public Once(InOutCallback inOutCallback, boolean blocking)
  {
    if (inOutCallback == null)
    {
      throw new IllegalArgumentException("inOutCallback is null");
    }

    this.runnable = null;
    this.inCallback = null;
    this.outCallback = null;
    this.inOutCallback = inOutCallback;
    this.doneNonBlocking = blocking ? null : new AtomicBoolean(false);
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
    if (doneNonBlocking != null)
    {
      return executeNonBlocking(object);
    }
    else
    {
      return executeBlocking(object);
    }
  }

  private Object executeBlocking(Object object)
  {
    Object ret = null;

    if (!doneBlocking)
    {
      synchronized (this)
      {
        if (!doneBlocking)
        {
          ret = execute(object);
          doneBlocking = true;
        }
      }
    }

    return ret;
  }

  private Object executeNonBlocking(Object object)
  {
    Object ret = null;

    if (!doneNonBlocking.getAndSet(true))
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
