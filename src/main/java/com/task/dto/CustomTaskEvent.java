package com.task.dto;

import org.springframework.context.ApplicationEvent;


public class CustomTaskEvent extends ApplicationEvent {
    private TaskDto taskDto;

    public CustomTaskEvent(Object source, TaskDto taskDto) {
        super(source);
        this.taskDto = taskDto;
    }

    public TaskDto getTaskDto() {
        return taskDto;
    }
}
