package com.task.repository;

import com.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public  interface TaskRepository extends JpaRepository<Task, Integer> {


    @Query(value = "select t from Task t" +
            " order by  case t.status" +
            " when 'PENDING' then 1" +
            " when 'WORKING' then 1" +
            " when 'COMPLETED' then 3" +
            " else t.status end asc," +
            " t.priority asc , t.dueDate desc")
  List<Task> getCustomShortedTask();

}
