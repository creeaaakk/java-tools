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
