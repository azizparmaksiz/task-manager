package com.task.service;

import com.task.dto.TaskDto;

import java.util.Date;
import java.util.List;

public interface TaskService {


    public List<TaskDto>getAllTasks();

    public void deleteTask(int taskId);

    public void updateTask(TaskDto taskDto);

    public void postponeTask(int taskId, Date toDate);

    public int createTask(TaskDto taskDto);
}
