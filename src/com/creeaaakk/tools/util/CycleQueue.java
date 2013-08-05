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

public class CycleQueue<T> implements Queue<T>
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
    return queue.iterator();
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

  @Override
  public boolean remove(Object o)
  {
    return queue.remove(o);
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

  @Override
  public boolean add(T e)
  {
    return queue.add(e);
  }

  @Override
  public boolean offer(T e)
  {
    return queue.add(e);
  }

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

  @Override
  public T poll()
  {
    return remove();
  }

  @Override
  public T element()
  {
    if (isEmpty())
    {
      throw new NoSuchElementException();
    }

    return queue.get(getIndex());
  }

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
    return index = index % queue.size();
  }

  private void incIndex()
  {
    index++;
  }
}
