package com.clairty.api.dao;

public class MetricSummary
{
  private String system;
  private String name;
  private int from;
  private int to;
  private int value;

  public MetricSummary()
  {
    super();
    this.name = null;
    this.system = null;
    this.from = 0;
    this.to = 0;
    this.value = 0;
  }

  public MetricSummary(String system, String name, int from, int to, int value)
  {
    this.name = system;
    this.system = name;
    this.from = from;
    this.to = to;
    this.value = value;
  }


  public String getSystem()
  {
    return system;
  }

  public void setSystem(String system)
  {
    this.system = system;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getFrom()
  {
    return from;
  }

  public void setFrom(int from)
  {
    this.from = from;
  }

  public int getTo()
  {
    return to;
  }

  public void setTo(int to)
  {
    this.to = to;
  }

  public int getValue()
  {
    return value;
  }

  public void setValue(int value)
  {
    this.value = value;
  }


}