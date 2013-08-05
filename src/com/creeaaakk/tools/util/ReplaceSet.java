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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ReplaceSet<T extends IReplaceable> implements Set<T>, Serializable
{
  private static final long serialVersionUID = 7976383212867753829L;

  private final Map<T, T> map = new HashMap<T, T>();
  private final Set<T> set = map.keySet();

  @Override
  public int size()
  {
    return map.size();
  }

  @Override
  public boolean isEmpty()
  {
    return map.isEmpty();
  }

  @Override
  public boolean contains(Object key)
  {
    if (key == null || !(key instanceof IReplaceable))
    {
      return false;
    }

    IReplaceable replaceable = (IReplaceable) key;
    return map.containsKey(replaceable) && !replaceable.needReplace(map.get(replaceable));
  }

  @Override
  public Iterator<T> iterator()
  {
    return set.iterator();
  }

  @Override
  public Object[] toArray()
  {
    return set.toArray();
  }

  @Override
  public <S> S[] toArray(S[] array)
  {
    return set.toArray(array);
  }

  @Override
  public boolean add(T element)
  {
    if (element == null)
    {
      throw new IllegalArgumentException("element is null");
    }

    if (element.needReplace(map.get(element)))
    {
      map.remove(element);
      map.put(element, element);
      return true;
    }
    else
    {
      return false;
    }
  }

  @Override
  public boolean addAll(Collection<? extends T> collection)
  {
    boolean changed = false;

    for (T item : collection)
    {
      changed |= add(item);
    }

    return changed;
  }

  @Override
  public boolean remove(Object key)
  {
    return map.remove(key) != null;
  }

  @Override
  public boolean containsAll(Collection<?> collection)
  {
    for (Object object : collection)
    {
      if (!set.contains(object))
      {
        return false;
      }
    }

    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean retainAll(Collection<?> collection)
  {
    boolean changed = set.retainAll(collection);

    for (Object object : collection)
    {
      if (object != null && object instanceof IReplaceable)
      {
        IReplaceable replaceable = (IReplaceable) object;

        if (map.containsKey(object) && replaceable.needReplace(map.get(replaceable)))
        {
          map.remove(replaceable);
          map.put((T) replaceable, (T) replaceable);
          changed = true;
        }
      }
    }

    return changed;
  }

  @Override
  public boolean removeAll(Collection<?> collection)
  {
    return set.removeAll(collection);
  }

  @Override
  public void clear()
  {
    set.clear();
  }
}
