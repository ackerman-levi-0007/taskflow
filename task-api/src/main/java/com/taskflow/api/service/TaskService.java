package com.taskflow.api.service;

import com.taskflow.api.dto.CreateTaskRequest;
import com.taskflow.api.dto.TaskResponse;
import com.taskflow.api.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskResponse create(CreateTaskRequest request);
    TaskResponse getByID(Long id);
    Page<TaskResponse> list(TaskStatus status, Pageable pageable);
    TaskResponse updateStatus(Long id, TaskStatus status);
    void delete(Long id);
}
