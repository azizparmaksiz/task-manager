package com.task.config;


import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;


public class TaskUtil {

    public static final Map<String, WebSocketSession> sessionMap=new HashMap<String, WebSocketSession>();
}
