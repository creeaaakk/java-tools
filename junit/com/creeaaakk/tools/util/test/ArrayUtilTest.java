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

package com.creeaaakk.tools.util.test;

import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;

import com.creeaaakk.tools.util.ArrayUtil;

public class ArrayUtilTest extends TestCase
{
  @Test
  public void test()
  {
    Integer[] forward;
    Integer[] reversed;

    forward = new Integer[0];
    reversed = ArrayUtil.reverse(forward, new Integer[0]);
    assertTrue(Arrays.equals(new Integer[0], reversed));

    forward = new Integer[] { 1 };
    reversed = ArrayUtil.reverse(forward, new Integer[1]);
    assertTrue(Arrays.equals(new Integer[] { 1 }, reversed));

    forward = new Integer[] { 1, 2 };
    reversed = ArrayUtil.reverse(forward, new Integer[2]);
    assertTrue(Arrays.equals(new Integer[] { 2, 1 }, reversed));

    forward = new Integer[] { 1, 2, 3 };
    reversed = ArrayUtil.reverse(forward, new Integer[3]);
    assertTrue(Arrays.equals(new Integer[] { 3, 2, 1 }, reversed));

    forward = new Integer[] { 1, 2, 3, 4 };
    reversed = ArrayUtil.reverse(forward, new Integer[4]);
    assertTrue(Arrays.equals(new Integer[] { 4, 3, 2, 1 }, reversed));

    reversed = new Integer[0];
    ArrayUtil.reverse(reversed);
    assertTrue(Arrays.equals(new Integer[0], reversed));

    reversed = new Integer[] { 1 };
    ArrayUtil.reverse(reversed);
    assertTrue(Arrays.equals(new Integer[] { 1 }, reversed));

    reversed = new Integer[] { 1, 2 };
    ArrayUtil.reverse(reversed);
    assertTrue(Arrays.equals(new Integer[] { 2, 1 }, reversed));

    reversed = new Integer[] { 1, 2, 3 };
    ArrayUtil.reverse(reversed);
    assertTrue(Arrays.equals(new Integer[] { 3, 2, 1 }, reversed));

    reversed = new Integer[] { 1, 2, 3, 4 };
    ArrayUtil.reverse(reversed);
    assertTrue(Arrays.equals(new Integer[] { 4, 3, 2, 1 }, reversed));
  }
}
