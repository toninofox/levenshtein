package dev.code.test.levenshtein.common;

public abstract interface PluginFactory<T>
{
  public abstract T createPlugin();
}
