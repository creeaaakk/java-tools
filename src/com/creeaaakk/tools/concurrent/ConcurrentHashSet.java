/*
 * NOTICE: this file has been modified mostly cosmetically from its
 *         original form
 *
 *
 * Copyright 2013 Creeaaakk Ware LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.creeaaakk.tools.concurrent;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashSet<K> implements Set<K>, Serializable
{
  private static final long serialVersionUID = 6565789100653644329L;

  private final ConcurrentMap<K, Boolean> map;

  public ConcurrentHashSet(int initialCapacity)
  {
    map = new ConcurrentHashMap<K, Boolean>(initialCapacity);
  }

  public ConcurrentHashSet()
  {
    map = new ConcurrentHashMap<K, Boolean>();
  }

  public int size()
  {
    return map.size();
  }

  public boolean isEmpty()
  {
    return map.isEmpty();
  }

  public boolean contains(Object o)
  {
    return map.containsKey(o);
  }

  public Iterator<K> iterator()
  {
    return map.keySet().iterator();
  }

  public Object[] toArray()
  {
    return map.keySet().toArray();
  }

  public <T> T[] toArray(T[] a)
  {
    return map.keySet().toArray(a);
  }

  public boolean add(K o)
  {
    return map.putIfAbsent(o, Boolean.TRUE) == null;
  }

  public boolean remove(Object o)
  {
    return map.keySet().remove(o);
  }

  public boolean containsAll(Collection<?> c)
  {
    return map.keySet().containsAll(c);
  }

  public boolean addAll(Collection<? extends K> c)
  {
    boolean ret = false;

    for (K o : c)
    {
      ret |= add(o);
    }

    return ret;
  }

  public boolean retainAll(Collection<?> c)
  {
    return map.keySet().retainAll(c);
  }

  public boolean removeAll(Collection<?> c)
  {
    return map.keySet().removeAll(c);
  }

  public void clear()
  {
    map.clear();
  }
}
