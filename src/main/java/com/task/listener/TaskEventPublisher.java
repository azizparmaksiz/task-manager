package com.task.listener;

import com.task.dto.CustomTaskEvent;
import com.task.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TaskEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishTask(final TaskDto taskDto) {
        System.out.println("Publishing task event. ");
        CustomTaskEvent customTaskEvent = new CustomTaskEvent(this, taskDto);
        applicationEventPublisher.publishEvent(customTaskEvent);
    }
}
