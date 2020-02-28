package com.task.service;

import com.task.dto.TaskDto;
import com.task.exception.CommonExceptionMessage;
import com.task.exception.ResourceNotFoundException;
import com.task.model.Task;
import com.task.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public List<TaskDto> getAllTasks() {

        //Sort sort = Sort.by(
        //      Sort.Order.asc("priority"),
        //    Sort.Order.desc("dueDate"));
        //List<Task> taskList=taskRepository.findAll(sort);

        List<Task> taskList = taskRepository.getCustomShortedTask();

        Type listType = new TypeToken<List<TaskDto>>() {
        }.getType();

        return modelMapper.map(taskList, listType);
    }

    @Override
    public void deleteTask(int taskId) {

        taskRepository.deleteById(taskId);

    }

    @Override
    public void updateTask(TaskDto taskDto) {
        Optional task = taskRepository.findById(taskDto.getId());
        if (task.isPresent()) {
            Task t = modelMapper.map(taskDto, Task.class);
            t.setUpdatedAt(new Date());
            taskRepository.save(t);
        } else {
            throw new ResourceNotFoundException(new CommonExceptionMessage("TASK_NOT_FOUND"));
        }
    }

    @Override
    public int createTask(TaskDto taskDto) {

        Task t = modelMapper.map(taskDto, Task.class);
        t.setUpdatedAt(new Date());
        return taskRepository.save(t).getId();

    }

    @Override
    public void postponeTask(int taskId, Date toDate) {

        Optional task = taskRepository.findById(taskId);

        if (task.isPresent()) {
            Task t = (Task) task.get();
            t.setDueDate(toDate);
            t.setUpdatedAt(new Date());
            taskRepository.save(t);
        } else {
            throw new ResourceNotFoundException(new CommonExceptionMessage("TASK_NOT_FOUND"));
        }


    }
}
