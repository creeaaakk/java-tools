package com.creeaaakk.tools.util;

public final class MathUtil
{
  /**
   * Computes the percent difference between two non-negative numbers.
   */
  public static int percentDifference(int a, int b)
  {
    if (a < 0 || b < 0) throw new IllegalArgumentException();
    if (a + b == 0) return 0;
    return java.lang.Math.abs(200 * (a - b) / (a + b));
  }

  /**
   * Computes the percent difference between two non-negative numbers.
   */
  public static long percentDifference(long a, long b)
  {
    if (a < 0 || b < 0) throw new IllegalArgumentException();
    if (a + b == 0) return 0;
    return java.lang.Math.abs(200 * (a - b) / (a + b));
  }

  /**
   * Computes the percent difference between two non-negative numbers.
   */
  public static float percentDifference(float a, float b)
  {
    if (a < 0 || b < 0) throw new IllegalArgumentException();
    if (a + b == 0) return 0;
    return java.lang.Math.abs(200 * (a - b) / (a + b));
  }

  /**
   * Computes the percent difference between two non-negative numbers.
   */
  public static double percentDifference(double a, double b)
  {
    if (a < 0 || b < 0) throw new IllegalArgumentException();
    if (a + b == 0) return 0;
    return java.lang.Math.abs(200 * (a - b) / (a + b));
  }

  /**
   * Computes the distance between two points squared.
   */
  public static double squareDistance(double x1, double y1, double x2, double y2)
  {
    double xDiff = x1 - x2;
    double yDiff = y1 - y2;
    return xDiff * xDiff + yDiff * yDiff;
  }

  /**
   * Computes the distance between two points.
   */
  public static double distance(double x1, double y1, double x2, double y2)
  {
    return java.lang.Math.sqrt(squareDistance(x1, y1, x2, y2));
  }
}
