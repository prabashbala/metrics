package com.clairty.api.rest;

import com.clairty.api.dao.Metric;
import com.clairty.api.dao.MetricSummary;
import com.clairty.api.error.ApiError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MetricsControllerIntegrationTest
{
  private static final String url = "http://localhost:8080/metrics";

  @LocalServerPort
  private int port;

  @Autowired
  private MetricsController metricsController;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void contexLoads() throws Exception
  {
    assertThat(metricsController).isNotNull();
  }

  /**
   * This test will post a json object as below to the metric method
   * POST /metrics HTTP/1.1
   * Host: localhost:8083
   * Content-Type: application/json
   * Cache-Control: no-cache
   * Postman-Token: 59c252f3-bdbb-e33d-0f0b-a7d40acb2f52
   * {
   * "system":"John",
   * "name":"Carty",
   * "date":1,
   * "value":2
   * }
   */
  @Test
  public void whenPOSTRequestToMetricsIsValidThenReturnSuccess()
  {
    String newURL = url.replace("8080", String.valueOf(port));
    Metric metric = new Metric(1, "", "", 1, 2);
    String response = this.restTemplate.postForObject(newURL, metric, String.class);
    assertTrue(response.equalsIgnoreCase("SUCCESS"));
  }

  /**
   * This is to test exception handling on RestExceptionHandler. This will send following message#
   * * POST /metrics HTTP/1.1
   * * Host: localhost:8083
   * * Content-Type: application/json
   * * Cache-Control: no-cache
   * * Postman-Token: 59c252f3-bdbb-e33d-0f0b-a7d40acb2f52
   * * {
   * // invalid JSON
   * * }
   */
  @Test
  public void whenRequestToMetricsIsMulFormattedThenReturnError()
  {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    String requestJson = "{ // invalid JSON  }";
    String newURL = url.replace("8080", String.valueOf(port));
    HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
    ApiError response = this.restTemplate.postForObject(newURL, entity, ApiError.class);
    assertFalse(response.getMessage().isEmpty());
  }

  /**
   * This is to test PUT request posted in the following format
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
   */
  @Test
  public void whenPUTRequestToMetricsIsValidThenReturnSuccess()
  {
    final String updateURI = "http://localhost:8080/metrics/{id}";
    String newURL = updateURI.replace("8080", String.valueOf(port));
    Map<String, Integer> uriParams = new HashMap<>();
    uriParams.put("id", 1);
    Metric metric = new Metric(1, "", "", 1, 2);
    this.restTemplate.put(newURL, metric, uriParams);
  }

  /**
   * This is to test GET message for Metricsummary - following message to be sent
   * GET /metricsummary?system=1010&amp;name=MARS&amp;from=10122018&amp;to=10122019 HTTP/1.1
   * Host: localhost:8083
   * Cache-Control: no-cache
   * Postman-Token: eb536d2c-3881-06d9-2f85-645b49f93e79
   */
  @Test
  public void whenGETRequestToMetricsummaryIsValidThenReturnSuccess()
  {
    String newURL = url.replace("8080", String.valueOf(port)).replace("metrics", "metricsummary");

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(newURL)
            .queryParam("system", "1010")
            .queryParam("name", "MARS")
            .queryParam("from", "10122018")
            .queryParam("to", 1);

    ResponseEntity<List<MetricSummary>> response = this.restTemplate.exchange(
            builder.toUriString(),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<MetricSummary>>()
            {

            }
    );
    List<MetricSummary> metricSummary = response.getBody();
    assertThat(metricSummary.size() > 0);
  }

}