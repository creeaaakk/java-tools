package com.creeaaakk.tools.util;

import java.io.Serializable;

/**
 * A key based upon canonical class names that are appropriate for use
 * in data structures that make use of equals() and hashCode().
 */
public final class NameKey implements Serializable
{
  private static final long serialVersionUID = -5715372316218537180L;

  private final String name;

  /**
   * Creates a key from the class of the provided object, null is valid.
   */
  public NameKey(Object object)
  {
    if (object != null)
    {
      name = object.getClass().getCanonicalName();
    }
    else
    {
      name = "";
    }
  }

  /**
   * Creates a key from the provided class, null is valid.
   */
  public NameKey(Class<?> clazz)
  {
    if (clazz != null)
    {
      name = clazz.getCanonicalName();
    }
    else
    {
      name = "";
    }
  }

  @Override
  public boolean equals(Object other)
  {
    if (other == null) return false;
    if (!(other instanceof NameKey)) return false;
    return name.equals(((NameKey) other).name);
  }

  @Override
  public int hashCode()
  {
    return name.hashCode();
  }
}
