package com.task.websocket;

import com.task.config.TaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskWebSocketTransactionsHandler extends TextWebSocketHandler {
    private static Logger logger = LoggerFactory.getLogger(TaskWebSocketTransactionsHandler.class);
    private Executor executor;


    public TaskWebSocketTransactionsHandler(int maxfixedThreadPool) {
        this.executor = Executors.newFixedThreadPool(maxfixedThreadPool);

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connection established session" + session.getId());
        TaskUtil.sessionMap.put(session.getId(),session);
    }



    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException, InterruptedException {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        try {
            removeSessionFromMemory(session);

        } catch (Exception e) {
            logger.error("afterConnectionClosed event {}", e);
        }

    }

    private void removeSessionFromMemory(WebSocketSession session) {
     TaskUtil.sessionMap.remove(session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        try {
            removeSessionFromMemory(session);

        } catch (Exception e) {
            logger.error("afterConnectionClosed event {}", e);
        }

        super.handleTransportError(session, exception);
    }

}
