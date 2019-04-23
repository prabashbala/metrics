package com.clairty.api.da;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class MetricDA implements Serializable
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;

  @Column
  private String system;

  @Column
  private String name;

  @Column
  private int date;

  @Column
  private int value;

  public MetricDA(String system, String name, int date, int value)
  {
    this.system = system;
    this.name = name;
    this.date = date;
    this.value = value;
  }

  protected MetricDA()
  {
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
