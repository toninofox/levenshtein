package dev.code.test.levenshtein.common;

public abstract interface PluginResourceManager<T>
{
  public abstract PluginFactory<T> start(IEnvironment env);

  public abstract void stop();
}

