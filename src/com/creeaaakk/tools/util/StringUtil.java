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

public final class StringUtil
{
  public static List<String> splitString(String string, int length)
  {
    final List<String> split = new ArrayList<String>();
  
    if (string.length() < length)
    {
      split.add(string);
    }
    else
    {
      final int numSplits = string.length() / length;
  
      for (int i = 0; i < numSplits; i++)
      {
        split.add(string.substring(i * length, (i + 1) * length));
      }
  
      split.add(string.substring(length * numSplits));
    }
  
    return split;
  }

  public static String join(Collection<String> strings, String delimiter)
  {
    if (strings == null)
    {
      throw new IllegalArgumentException("strings is null");
    }

    if (delimiter == null)
    {
      throw new IllegalArgumentException("delimiter is null");
    }

    StringBuilder builder = new StringBuilder();

    for (Iterator<String> iterator = strings.iterator(); iterator.hasNext(); )
    {
      builder.append(iterator.next());
      builder.append(delimiter);
    }

    if (builder.length() > 0)
    {
      return builder.substring(0, builder.length() - delimiter.length());
    }
    else
    {
      return "";
    }
  }
}
