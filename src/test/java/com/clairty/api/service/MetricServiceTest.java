package com.clairty.api.service;

import com.clairty.api.da.MetricDA;
import com.clairty.api.da.MetricRepository;
import com.clairty.api.dao.MetricSummary;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class MetricServiceTest
{
  private Fixture fixture;

  @Before
  public void setUp()
  {
    fixture = new Fixture();
  }

  @Test
  public void getMetricSummaryByNameReturnEmptyResult()
  {
    fixture.givenFindByNameReturnEmptyList();
    fixture.whenGetMetricSummary();
    fixture.thenGetMetricsReturnEmptyList();
  }

  @Test
  public void getMetricSummaryByNameReturnValidResult()
  {
    fixture.givenFindByNameReturnNonEmptyList();
    fixture.whenGetMetricSummary();
    fixture.thenGetMetricsReturnValidList();
  }

  @Test
  public void saveMetric()
  {
  }

  private class Fixture
  {
    @Mock
    private MetricRepository metricRepository;
    @Mock
    private List<MetricSummary> metricSummaryList;
    @InjectMocks
    private MetricService testClass;

    public Fixture()
    {
      MockitoAnnotations.initMocks(this);
    }


    public void givenFindByNameReturnEmptyList()
    {
      when(metricRepository.findByName(anyString())).thenReturn(anyList());
    }

    public void whenGetMetricSummary()
    {
      metricSummaryList = testClass.getMetricSummaryByName("");
    }

    public void thenGetMetricsReturnEmptyList()
    {
      assertNotNull(metricSummaryList);
    }


    public void thenGetMetricsReturnValidList()
    {
      assertNotNull(metricSummaryList);
      assertTrue(metricSummaryList.size()>0);
    }

    public void givenFindByNameReturnNonEmptyList()
    {
      List<MetricDA> results = new ArrayList<>();
      results.add(new MetricDA("10", "120123", 4, 8));
      when(metricRepository.findByName("")).thenReturn(results);
    }
  }
}