package ezenweb.controller;

import jakarta.websocket.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
public class ChatSocket extends TextWebSocketHandler {

    //0.접속 성공한 session들을 모아두기(접속 명단)
    private List<WebSocketSession> 접속명단 = new Vector<>(); // vs new vector

    //1.클라이언트 소켓의 접속이 성공일때 (session : 클라이언트의 소켓 정보)
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("session = " + session);
        //1.접속한 세션정보를 리스트에 담기
        접속명단.add(session);
        System.out.println("접속명단 = " + 접속명단);

    }

    //2.클라이언트 메세지를 받았을때

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("message = " + message);
        System.out.println("message.getPayload() = " + message.getPayload());
        //1. 접속명단의 클라이언트 소켓들에게 메세지 보내기
        for (WebSocketSession webSocketSession : 접속명단) {
            WebSocketMessage<String> sendmessage = new TextMessage("서버가 보낸 메세지");
            webSocketSession.sendMessage(message);
        }

    }
    //3.클라이언트 소켓과 접속이 종료 되었을때(session : 접속이 종료된 클라이언트 소켓)
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        접속명단.remove(session);
    }
}
