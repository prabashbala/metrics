package com.clairty.api.rest;

import com.clairty.api.dao.Metric;
import com.clairty.api.dao.MetricSummary;
import com.clairty.api.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@EnableSpringDataWebSupport
class MetricsController
{

  @Autowired
  private MetricService metricService;

  /**
   * This is to search for a saved Metric based on the following request
   *
   * GET /metricsummary?system=1010&amp;name=MARS&amp;from=10122018&amp;to=10122019 HTTP/1.1
   * Host: localhost:8083
   * Cache-Control: no-cache
   * Postman-Token: eb536d2c-3881-06d9-2f85-645b49f93e79
   *
   * @param system system
   * @param name name
   * @param from from
   * @param to to
   * @return
   */
  @RequestMapping(value = "/metricsummary", method = {RequestMethod.GET})
  public ResponseEntity<List<MetricSummary>> getMetricSummary(@RequestParam(required = false) String system,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false) int from,
                                                           @RequestParam(required = false) int to)
  {
    if (StringUtils.isEmpty(system))
    {
      return new ResponseEntity("A required parameter was not supplied or is invalid Bonus", HttpStatus.BAD_REQUEST);
    }
    metricService.saveMetric(new Metric(1, system, name, 0, 1));
    List<MetricSummary> results = metricService.getMetricSummaryByName(name);
    return ResponseEntity.ok(results);
  }

  /**
   * This will update a existing metric - following the request to be made
   * PUT /metrics/1 HTTP/1.1
   * Host: localhost:8083
   * Content-Type: application/json
   * Cache-Control: no-cache
   * Postman-Token: aa795025-0417-fce3-ba62-ddfc580b0dae
   * {
   * "system":"John",
   * "name":"Carty",
   * "date":1,
   * "value":2
   * }
   *
   * @param id id of the metric to be updated
   * @param metric new metric value
   * @return success if update was successful
   */

  @RequestMapping(value = "/metrics/{id}", method = {RequestMethod.PUT})
  public String updateMetrics(@PathVariable("id") int id,
                              @RequestBody Metric metric)
  {
    metricService.update(metric);
    return "SUCCESS";
  }

  /**
   * This will handle a POST request of the json object below
   * Request Body: application/json:   *
   * {
   * system*: string,
   * name*: string,
   * date*: integer,
   * value: integer,
   * }
   *
   * @param metric Metric to be saved
   * @return success if the save was successful
   */
  @RequestMapping(value = "/metrics", method = {RequestMethod.POST})
  public String addMetrics(@RequestBody Metric metric)
  {
    metricService.saveMetric(metric);
    return "SUCCESS";
  }

}