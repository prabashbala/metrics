package com.clairty.api.dao;

import javax.validation.constraints.NotBlank;

public class Metric
{
  @NotBlank
  private int id;
  private String system;
  private String name;
  private int date;
  private int value;


  public Metric()
  {
    super();
    this.id = 0;
    this.system = null;
    this.name = null;
    this.date = 0;
    this.value = 0;
  }

  public Metric(int id, String system, String name, int date, int value)
  {
    this.id = id;
    this.system = system;
    this.name = name;
    this.date = date;
    this.value = value;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
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

  public int getDate()
  {
    return date;
  }

  public void setDate(int date)
  {
    this.date = date;
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
