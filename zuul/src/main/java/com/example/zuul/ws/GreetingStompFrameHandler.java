package com.example.zuul.ws;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

public class GreetingStompFrameHandler implements StompFrameHandler {

  private final SimpMessagingTemplate messagingTemplate;

  public GreetingStompFrameHandler( SimpMessagingTemplate aTemplate ) {
    messagingTemplate = aTemplate;
  }

  @Override
  public Type getPayloadType( final StompHeaders stompHeaders ) {
    return Greeting.class;
  }

  @Override
  public void handleFrame( final StompHeaders stompHeaders, final Object o ) {
    Greeting greeting = ( Greeting ) o;
    System.out.println( "frame received: " + greeting.getContent() );

    messagingTemplate.convertAndSend( "/topic/greetings", greeting );
  }
}