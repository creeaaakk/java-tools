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

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import junit.framework.TestCase;

import org.junit.Test;

import com.creeaaakk.tools.util.CycleQueue;

public class CycleQueueTest extends TestCase
{
  @Test
  public void test()
  {
    boolean threw;
    Queue<String> queue = new CycleQueue<String>();
    assertTrue(queue.isEmpty());

    queue.add("0");
    assertFalse(queue.isEmpty());
    assertTrue(queue.size() == 1);
    assertEquals("0", queue.remove());
    assertEquals("0", queue.remove());
    assertEquals("0", queue.poll());
    assertEquals("0", queue.poll());
    assertEquals("0", queue.element());
    assertEquals("0", queue.peek());

    queue.add("1");
    assertFalse(queue.isEmpty());
    assertTrue(queue.size() == 2);
    assertEquals("0", queue.remove());
    assertEquals("1", queue.remove());
    assertEquals("0", queue.poll());
    assertEquals("1", queue.element());
    assertEquals("1", queue.peek());
    assertEquals("1", queue.poll());
    assertEquals("0", queue.element());
    assertEquals("0", queue.peek());

    queue.remove();
    queue.remove();
    queue.add("2");
    assertEquals("2", queue.peek());
    assertEquals("2", queue.remove());
    assertEquals("0", queue.peek());

    queue.add("3");
    assertEquals("0", queue.remove());
    assertEquals("1", queue.remove());
    assertEquals("2", queue.remove());
    assertEquals("3", queue.remove());
    assertEquals("0", queue.remove());

    try
    {
      queue.remove("0");
      threw = false;
    }
    catch (UnsupportedOperationException exception)
    {
      threw = true;
    }

    assertTrue(threw);

    try
    {
      queue.iterator().remove();
      threw = false;
    }
    catch (UnsupportedOperationException exception)
    {
      threw = true;
    }

    assertTrue(threw);

    Iterator<String> iterator = queue.iterator();
    assertEquals("0", iterator.next());
    assertEquals("1", iterator.next());
    assertEquals("2", iterator.next());
    assertEquals("3", iterator.next());

    try
    {
      iterator.next();
      threw = false;
    }
    catch (NoSuchElementException exception)
    {
      threw = true;
    }

    assertTrue(threw);
  }
}
