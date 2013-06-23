package com.creeaaakk.tools.util;

public interface IReplaceable
{
  /**
   * Implementations should check if the given object needs placing.
   */
  boolean needReplace(Object toReplace);
}
