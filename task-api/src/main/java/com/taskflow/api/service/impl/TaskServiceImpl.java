package com.taskflow.api.service.impl;

import com.taskflow.api.dto.CreateTaskRequest;
import com.taskflow.api.dto.TaskResponse;
import com.taskflow.api.entity.Task;
import com.taskflow.api.enums.TaskStatus;
import com.taskflow.api.exception.TaskNotFoundException;
import com.taskflow.api.repository.TaskRepository;
import com.taskflow.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public TaskResponse create(
            CreateTaskRequest request
    ) {
        Task task = Task.from(request);
        task.setStatus(TaskStatus.PENDING);
        Task createdTask = taskRepository.save(task);
        return TaskResponse.from(createdTask);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getByID(
            Long id
    ) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return TaskResponse.from(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskResponse> list(
            TaskStatus status,
            Pageable pageable
    ) {
        Page<TaskResponse> taskResponses = (status != null)
                ? taskRepository.findByStatus(status, pageable).map(TaskResponse::from)
                : taskRepository.findAll(pageable).map(TaskResponse::from);
        return taskResponses;
    }

    @Override
    @Transactional
    public TaskResponse updateStatus(
            Long id,
            TaskStatus status
    ) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(status);
        Task updatedTask = taskRepository.save(task);
        return TaskResponse.from(updatedTask);
    }

    @Override
    @Transactional
    public void delete(
            Long id
    ) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(task);
    }
}
