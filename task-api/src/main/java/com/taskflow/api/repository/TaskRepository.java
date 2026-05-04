package com.taskflow.api.repository;

import com.taskflow.api.entity.Task;
import com.taskflow.api.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatus(TaskStatus taskStatus, Pageable pageable);
}
