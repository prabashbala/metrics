package com.clairty.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories("com.clairty.api")
public class Application
{

  public static void main(String[] args)
  {
    SpringApplication app = new SpringApplication(Application.class);
    app.setDefaultProperties(Collections.<String, Object>singletonMap("server.port", "8083"));
    app.run(args);
  }

}
