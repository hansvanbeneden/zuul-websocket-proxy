package com.example.zuul.ws;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class WebsocketBridge {

    public WebsocketBridge( SimpMessagingTemplate aTemplate ) throws ExecutionException, InterruptedException, TimeoutException {
        final String url = "ws://localhost:8090/backend/gs-guide-websocket";
        final WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient( createTransportClient()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        final StompSession stompSession = stompClient.connect(url, new StompSessionHandlerAdapter() {
        }).get( 10, TimeUnit.SECONDS);
        stompSession.subscribe("/topic/greetings", new GreetingStompFrameHandler(aTemplate));
    }

    private static List<Transport> createTransportClient() {
        final List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport( new StandardWebSocketClient()));
        return transports;
    }


}