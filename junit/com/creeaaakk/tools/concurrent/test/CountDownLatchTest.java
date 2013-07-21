package com.creeaaakk.tools.concurrent.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.creeaaakk.tools.concurrent.CountDownLatch;

public class CountDownLatchTest extends TestCase
{
  @Test
  public void test()
  {
    final boolean[] flags = new boolean[2];

    for (int i = 0; i < flags.length; i++)
    {
      flags[i] = false;
    }

    new CountDownLatch(0)
    {
      @Override
      protected void onZero()
      {
        flags[0] = true;
      }
    };

    assertTrue(flags[0]);

    CountDownLatch latch = new CountDownLatch(2)
    {
      @Override
      protected void onZero()
      {
        flags[1] = true;
      }
    };

    latch.countDown();
    assertFalse(flags[1]);
    latch.countDown();
    assertTrue(flags[1]);
  }
}
