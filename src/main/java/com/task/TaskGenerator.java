package com.task;

import com.task.dto.TaskDto;
import com.task.enums.TaskStatusEnum;
import com.task.listener.TaskEventPublisher;
import com.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Component
public final class TaskGenerator {
    private Logger logger = LoggerFactory.getLogger(TaskGenerator.class);

    @Autowired
    TaskService taskService;
    @Autowired
    TaskEventPublisher taskEventPublisher;

    @Scheduled(fixedDelayString = "#{new Double((T(java.lang.Math).random() + 1) * 10000).intValue()}")
    private void generateScheduledTask() {

        TaskStatusEnum[] taskStatusEnums = TaskStatusEnum.values();
        TaskStatusEnum taskStatus = taskStatusEnums[randomWithRange(0, taskStatusEnums.length - 1)];

        TaskDto taskDto = generateTask();
        taskDto.setStatus(TaskStatusEnum.PENDING);
        taskDto.setId(taskService.createTask(taskDto));

        taskEventPublisher.publishTask(taskDto);

        logger.info("Task Created {}", taskDto);

    }


    private Random random = new Random();

    private TaskDto generateTask() {
        int taskPriority = randomWithRange(1, 10);
        TaskDto taskDto = new TaskDto();
        Date now = new Date();
        taskDto.setTitle("Task " + now.getTime());
        taskDto.setDescription("Description " + now.getTime());
        taskDto.setCreatedAt(now);
        taskDto.setUpdatedAt(now);
        taskDto.setDueDate(generateDueDate());
        taskDto.setPriority(taskPriority);
        return taskDto;
    }

    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    private Date generateDueDate() {
        LocalDateTime time = LocalDateTime.of(LocalDate.now(),
                LocalTime.of(random.nextInt(24), random.nextInt(60),
                        random.nextInt(60), random.nextInt(999999999 + 1)));

        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }
}
