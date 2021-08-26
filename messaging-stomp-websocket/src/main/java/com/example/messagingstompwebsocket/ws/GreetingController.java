package com.example.messagingstompwebsocket.ws;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

  private SimpMessagingTemplate template;

  public GreetingController( SimpMessagingTemplate aTemplate ) {
    template = aTemplate;
  }

  @Scheduled(fixedRate=1000)
  public void repeatingGreeting() throws Exception {
    Greeting greeting = new Greeting( "Hello, everybody in the backend!" );
    template.convertAndSend( "/topic/greetings", greeting);
  }

}