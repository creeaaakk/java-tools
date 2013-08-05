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

  /**
   * Converts radians to degrees.
   */
  public static double radiansToDegrees(double radians)
  {
    return radians * 180.0 / Math.PI;
  }

  /**
   * Converts degrees to radians.
   */
  public static double degreesToRadians(double degrees)
  {
    return degrees * Math.PI / 180.0;
  }
}
