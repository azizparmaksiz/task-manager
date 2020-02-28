package com.task.websocket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
@EnableScheduling
public class WebsocketConfig implements WebSocketConfigurer {

    @Value("${websocket.maxTextMessageBufferSize}")
    private int maxTextMessageBufferSize;

    @Value("${websocket.maxBinaryMessageBufferSize}")
    private int maxBinaryMessageBufferSize;

    @Value("${websocket.maxSessionIdleTimeout}")
    private long maxSessionIdleTimeout;


    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(maxTextMessageBufferSize);
        container.setMaxBinaryMessageBufferSize(maxBinaryMessageBufferSize);
        container.setMaxSessionIdleTimeout(maxSessionIdleTimeout);
        return container;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(taskWebSocketTransactionsHandler(), "/websocket/new-task").setAllowedOrigins("*")
               .withSockJS().setStreamBytesLimit(maxTextMessageBufferSize)
                .setHttpMessageCacheSize(100000).setDisconnectDelay(30 * 1000);


    }


    @Bean
    public TaskWebSocketTransactionsHandler taskWebSocketTransactionsHandler() {
        return new TaskWebSocketTransactionsHandler();
    }






}
