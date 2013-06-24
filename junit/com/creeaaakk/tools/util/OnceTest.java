package com.creeaaakk.tools.util;

import junit.framework.TestCase;

import org.junit.Test;

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
