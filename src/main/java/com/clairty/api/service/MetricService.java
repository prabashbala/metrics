package com.clairty.api.service;

import com.clairty.api.da.MetricDA;
import com.clairty.api.da.MetricRepository;
import com.clairty.api.dao.Metric;
import com.clairty.api.dao.MetricSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MetricService
{
  @Autowired
  private MetricRepository metricRepository;

  public List<MetricSummary> getMetricSummaryByName(String name)
  {
    List<MetricDA> results = metricRepository.findByName(name);
    List<MetricSummary> metricSummaries = new ArrayList<MetricSummary>();
    for (MetricDA metric : results)
    {
      MetricSummary metricSummary = new MetricSummary(metric.getSystem(), metric.getName(), 0, 0, 0);
      metricSummaries.add(metricSummary);
    }
    return metricSummaries;
  }

  public MetricSummary getMetricById(int id)
  {
    Optional<MetricDA> results = metricRepository.findById(id);
    if(results.isPresent())
    {
      MetricDA matric = results.get();
      MetricSummary metricSummary = new MetricSummary(matric.getSystem(), matric.getName(), 0, 0, 0);
      return metricSummary;
    }
    return null;
  }

  public Metric saveMetric(Metric matirc)
  {
    metricRepository.save(new MetricDA(matirc.getSystem(), matirc.getName(), 0, 1));
    return null;
  }

  public void update(Metric metric)
  {
    Optional<MetricDA> results = metricRepository.findById(metric.getId());
    if(results.isPresent())
    {
      MetricDA matricOriginal = results.get();
      matricOriginal.setDate(metric.getDate());
      matricOriginal.setName(metric.getName());
      matricOriginal.setSystem(metric.getSystem());
      matricOriginal.setValue(metric.getValue());
      metricRepository.save(matricOriginal);
    }
  }
}
