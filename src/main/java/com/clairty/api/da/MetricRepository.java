package com.clairty.api.da;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MetricRepository extends CrudRepository<MetricDA, Integer>
{
  List<MetricDA> findByName(String name);
}
