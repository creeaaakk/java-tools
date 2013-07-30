package com.creeaaakk.tools.util;

import java.io.Closeable;
import java.io.IOException;

public final class CloseableUtil
{
  public static void close(Closeable ... closeables)
  {
    if (closeables != null)
    {
      for (Closeable closeable : closeables)
      {
        if (closeable != null)
        {
          try
          {
            closeable.close();
          }
          catch (IOException exception)
          {
          }
        }
      }
    }
  }
}
