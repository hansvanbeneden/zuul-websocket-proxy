package com.example.zuul;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyRestController {
  @GetMapping("/hello")
  public String hello() {
    return "Hello proxy";
  }
}

