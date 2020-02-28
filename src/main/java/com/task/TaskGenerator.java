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


/**
 * This class generate Task in randomly initialized scheduler time.
 * Each time system start up,  scheduler is scheduled randomly in 5sec 15sec range.
 * Scheduler generate task, save task to db and publish newly generated task to clients
 * */


@Component
public final class TaskGenerator {
    private Logger logger = LoggerFactory.getLogger(TaskGenerator.class);

    @Autowired
    TaskService taskService;
    @Autowired
    TaskEventPublisher taskEventPublisher;
    private Random random = new Random();

    @Scheduled(fixedDelayString = "#{new Double((T(java.lang.Math).random() * 10000)+5000).intValue()}")
    private void generateScheduledTask() {
        TaskDto taskDto = generateTask();
        taskDto.setStatus(TaskStatusEnum.PENDING);
        taskDto.setId(taskService.createTask(taskDto));
        logger.info("Task Created {}", taskDto);
        taskEventPublisher.publishTask(taskDto);
    }

    // Generate Task Object without id parameter set.
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

    // Get Random int in min max range. Min and  Max exclusive
    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return  random.nextInt(range) + min;
    }

    // Generate random next date after now.
    private Date generateDueDate() {
        LocalDateTime time = LocalDateTime.of(LocalDate.now(),
                LocalTime.of(random.nextInt(24), random.nextInt(60),
                        random.nextInt(60), random.nextInt(999999999 + 1)));

        // Generate random number to add time.
        int plusRandomNextDay=randomWithRange(1,7);

        time= time.plusDays(plusRandomNextDay);

        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }
}
