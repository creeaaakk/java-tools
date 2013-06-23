package com.creeaaakk.tools.concurrent;

public interface ThreadedRunnable<T>
{
  void run(T item);
}
