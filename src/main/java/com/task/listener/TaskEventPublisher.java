package com.task.listener;

import com.task.dto.CustomTaskEvent;
import com.task.dto.TaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TaskEventPublisher {
    private static Logger logger = LoggerFactory.getLogger(TaskEventListener.class);
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishTask(final TaskDto taskDto) {
        logger.info("Publishing task id={} event",taskDto.getId());
        CustomTaskEvent customTaskEvent = new CustomTaskEvent(this, taskDto);
        applicationEventPublisher.publishEvent(customTaskEvent);
    }
}
