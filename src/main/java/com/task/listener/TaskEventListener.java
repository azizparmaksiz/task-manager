package com.task.listener;

import com.google.gson.Gson;
import com.task.config.TaskUtil;
import com.task.dto.CustomTaskEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class TaskEventListener implements ApplicationListener<CustomTaskEvent> {
    private static Logger logger = LoggerFactory.getLogger(TaskEventListener.class);
    private Gson gs=new Gson();
    @Override
    public void onApplicationEvent(CustomTaskEvent event) {
        logger.info("Received task id={} event",event.getTaskDto().getId());

        if (!TaskUtil.sessionMap.values().isEmpty()) {
            for (WebSocketSession s : TaskUtil.sessionMap.values()) {
                try {
                    s.sendMessage(new TextMessage(gs.toJson(event.getTaskDto())));
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
