package com.creeaaakk.tools.util;

public class ReflectUtil
{
  public static boolean isSubclass(Class<?> child, Class<?> parent)
  {
    while (!child.getSuperclass().equals(Object.class))
    {
      child = child.getSuperclass();

      if (child.equals(parent))
      {
        return true;
      }
    }

    return false;
  }
}
