/**
 * Copyright (c) Michael Steindorfer <Centrum Wiskunde & Informatica> and Contributors.
 * All rights reserved.
 *
 * This file is licensed under the BSD 2-Clause License, which accompanies this project
 * and is available under https://opensource.org/licenses/BSD-2-Clause.
 */
package io.usethesource.capsule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.usethesource.capsule.api.deprecated.Set;
import io.usethesource.capsule.core.deprecated.TrieSet_5Bits;

public class DefaultTrieSet {

  @SuppressWarnings("rawtypes")
  private static Class<TrieSet_5Bits> target = TrieSet_5Bits.class;

  private static Method persistentSetOfEmpty;
  private static Method persistentSetOfKeyValuePairs;

  private static Method transientSetOfEmpty;
  private static Method transientSetOfKeyValuePairs;

  @SuppressWarnings("rawtypes")
  public static Class<TrieSet_5Bits> getTargetClass() {
    return target;
  }

  static {
    try {
      persistentSetOfEmpty = target.getMethod("of");
      persistentSetOfKeyValuePairs = target.getMethod("of", Object[].class);

      transientSetOfEmpty = target.getMethod("transientOf");
      transientSetOfKeyValuePairs = target.getMethod("transientOf", Object[].class);
    } catch (NoSuchMethodException | SecurityException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  public static final <K> Set.ImmutableSet<K> of() {
    try {
      return (Set.ImmutableSet<K>) persistentSetOfEmpty.invoke(null);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  public static final <K> Set.ImmutableSet<K> of(K... keys) {
    try {
      return (Set.ImmutableSet<K>) persistentSetOfKeyValuePairs.invoke(null, (Object) keys);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  public static final <K> Set.TransientSet<K> transientOf() {
    try {
      return (Set.TransientSet<K>) transientSetOfEmpty.invoke(null);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  public static final <K> Set.TransientSet<K> transientOf(K... keys) {
    try {
      return (Set.TransientSet<K>) transientSetOfKeyValuePairs.invoke(null, (Object) keys);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

}
