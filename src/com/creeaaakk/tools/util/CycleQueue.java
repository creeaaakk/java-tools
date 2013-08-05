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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * A CycleQueue works similar to standard queues, but elements are never removed.
 * Instead, when the end of the queue is reached, it starts returning values from
 * the beginning again. The queue is backed by a java.util.ArrayList. This class
 * is not thread safe.
 * 
 * @author Daniel Tashjian
 */
public class CycleQueue<T> implements Queue<T>, Iterable<T>
{
  private final List<T> queue = new ArrayList<T>();

  private int index = 0;

  public CycleQueue()
  {
  }

  public CycleQueue(Collection<? extends T> c)
  {
    addAll(c);
  }

  @Override
  public int size()
  {
    return queue.size();
  }

  @Override
  public boolean isEmpty()
  {
    return queue.isEmpty();
  }

  @Override
  public boolean contains(Object o)
  {
    return queue.contains(o);
  }

  @Override
  public Iterator<T> iterator()
  {
    return new Iterator<T>()
    {
      private final Iterator<T> iterator = queue.iterator();

      @Override
      public boolean hasNext()
      {
        return iterator.hasNext();
      }

      @Override
      public T next()
      {
        return iterator.next();
      }

      @Override
      public void remove() throws UnsupportedOperationException
      {
        throw new UnsupportedOperationException("CycleQueue does not allow removal of elements.");
      }
    };
  }

  @Override
  public Object[] toArray()
  {
    return queue.toArray();
  }

  @Override
  public <E> E[] toArray(E[] a)
  {
    return queue.toArray(a);
  }

  /**
   * Throws UnsupportedOperationException.
   * 
   * @throws UnsupportedOperationException always
   */
  @Override
  public boolean remove(Object o) throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("CycleQueue does not allow removal of elements.");
  }

  @Override
  public boolean containsAll(Collection<?> c)
  {
    return queue.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends T> c)
  {
    return queue.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c)
  {
    return queue.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c)
  {
    return queue.retainAll(c);
  }

  @Override
  public void clear()
  {
    queue.clear();
  }

  /**
   * Adds the given element to the queue (same as offer(T)).
   * 
   * @return true if the collection changed as a result of this call
   */
  @Override
  public boolean add(T e)
  {
    return queue.add(e);
  }

  /**
   * Adds the given element to the queue (same as add(T)).
   * 
   * @return true if the collection changed as a result of this call
   */
  @Override
  public boolean offer(T e)
  {
    return add(e);
  }

  /**
   * Retrieves the next value and advances the queue (same as poll()).
   * 
   * @return the next value, or null if empty
   */
  @Override
  public T remove()
  {
    if (isEmpty())
    {
      return null;
    }

    T ret = queue.get(getIndex());
    incIndex();
    return ret;
  }

  /**
   * Retrieves the next value and advances the queue (same as remove()).
   * 
   * @return the next value, or null if empty
   */
  @Override
  public T poll()
  {
    return remove();
  }

  /**
   * Retrieves the next value but does not advance the queue.
   * 
   * @return the next value
   * @throws NoSuchElementException is empty
   */
  @Override
  public T element()
  {
    if (isEmpty())
    {
      throw new NoSuchElementException();
    }

    return queue.get(getIndex());
  }

  /**
   * Retrieves the next value but does not advance the queue.
   * 
   * @return the next value, or null if empty
   */
  @Override
  public T peek()
  {
    if (isEmpty())
    {
      return null;
    }

    return queue.get(getIndex());
  }

  private int getIndex()
  {
    if (index >= queue.size())
    {
      index = 0;
    }

    return index;
  }

  private void incIndex()
  {
    index++;
  }
}
