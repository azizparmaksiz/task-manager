package com.task.controller;

import com.task.dto.TaskDto;
import com.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {
    Logger logger= LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/all")
    @ResponseBody
    public ResponseEntity<List<TaskDto>> getAllActType() {
        return new ResponseEntity<List<TaskDto>>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<Void> updateTask(@RequestBody  TaskDto taskDto) {
        taskService.updateTask(taskDto);
       return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteTask(@PathVariable("id") int id){
        taskService.deleteTask(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/postPone")
    @ResponseBody
    public ResponseEntity<Void> postPoneTask(@RequestBody  TaskDto taskDto) {

        taskService.postponeTask(taskDto.getId(),taskDto.getDueDate());
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }




}