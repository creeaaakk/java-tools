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

package com.creeaaakk.tools.util.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.creeaaakk.tools.util.InCallback;
import com.creeaaakk.tools.util.InOutCallback;
import com.creeaaakk.tools.util.Once;
import com.creeaaakk.tools.util.OutCallback;

public class OnceTest extends TestCase
{
  @Test
  public void test()
  {
    final int counts[] = new int[1];

    counts[0] = 0;

    Once once = new Once(new Runnable()
    {
      @Override
      public void run()
      {
        counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    once.run();
    assertEquals(1, counts[0]);
    once.run();
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new InCallback()
    {
      @Override
      public void callIn(Object object)
      {
        counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    once.run();
    assertEquals(1, counts[0]);
    once.run();
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new OutCallback()
    {
      @Override
      public Object callOut()
      {
        return counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    once.run();
    assertEquals(1, counts[0]);
    once.run();
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new InOutCallback()
    {
      @Override
      public Object callInOut(Object object)
      {
        return counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    once.run();
    assertEquals(1, counts[0]);
    once.run();
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new Runnable()
    {
      @Override
      public void run()
      {
        counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    once.callIn(null);
    assertEquals(1, counts[0]);
    once.callIn(null);
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new InCallback()
    {
      @Override
      public void callIn(Object object)
      {
        counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    once.callIn(null);
    assertEquals(1, counts[0]);
    once.callIn(null);
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new OutCallback()
    {
      @Override
      public Object callOut()
      {
        return counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    once.callIn(null);
    assertEquals(1, counts[0]);
    once.callIn(null);
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new InOutCallback()
    {
      @Override
      public Object callInOut(Object object)
      {
        return counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    once.callIn(null);
    assertEquals(1, counts[0]);
    once.callIn(null);
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new Runnable()
    {
      @Override
      public void run()
      {
        counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    assertNull(once.callOut());
    assertEquals(1, counts[0]);
    assertNull(once.callOut());
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new InCallback()
    {
      @Override
      public void callIn(Object object)
      {
        counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    assertNull(once.callOut());
    assertEquals(1, counts[0]);
    assertNull(once.callOut());
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new OutCallback()
    {
      @Override
      public Object callOut()
      {
        return counts[0]++;
      }
    });

    assertEquals(0, once.callOut());
    assertEquals(1, counts[0]);
    assertNull(once.callOut());
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new InOutCallback()
    {
      @Override
      public Object callInOut(Object object)
      {
        return counts[0]++;
      }
    });

    assertEquals(0, once.callOut());
    assertEquals(1, counts[0]);
    assertNull(once.callOut());
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new Runnable()
    {
      @Override
      public void run()
      {
        counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    assertNull(once.callInOut(null));
    assertEquals(1, counts[0]);
    assertNull(once.callInOut(null));
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new InCallback()
    {
      @Override
      public void callIn(Object object)
      {
        counts[0]++;
      }
    });

    assertEquals(0, counts[0]);
    assertNull(once.callInOut(null));
    assertEquals(1, counts[0]);
    assertNull(once.callInOut(null));
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new OutCallback()
    {
      @Override
      public Object callOut()
      {
        return counts[0]++;
      }
    });

    assertEquals(0, once.callInOut(null));
    assertEquals(1, counts[0]);
    assertNull(once.callInOut(null));
    assertEquals(1, counts[0]);

    counts[0] = 0;

    once = new Once(new InOutCallback()
    {
      @Override
      public Object callInOut(Object object)
      {
        return counts[0]++;
      }
    });

    assertEquals(0, once.callInOut(null));
    assertEquals(1, counts[0]);
    assertNull(once.callInOut(null));
    assertEquals(1, counts[0]);
  }
}
