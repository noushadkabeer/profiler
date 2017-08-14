package com.lemon.profiler.exceptions;

public class UnauthorizedException extends ConnectionFailedException
{
  private static final long serialVersionUID = -4724095050657100665L;

  public UnauthorizedException(Exception e)
  {
    super(e);
  }
}