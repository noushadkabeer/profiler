package com.lemon.profiler.exceptions;

public class ConnectionFailedException extends Exception
{
  private static final long serialVersionUID = 2029009543064490671L;

  public ConnectionFailedException(Exception e)
  {
    super(e);
  }
}