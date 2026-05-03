package com.taskflow.api.mapper;

import com.taskflow.api.dto.CreateTaskRequest;
import com.taskflow.api.dto.TaskResponse;
import com.taskflow.api.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(CreateTaskRequest req) {
        return Task.builder()
                .title(req.title())
                .description(req.description())
                .priority(req.priority())
                .build();
    }
    public TaskResponse toResponse(Task t) {
        return TaskResponse.builder()
                .id(t.getId()).title(t.getTitle()).description(t.getDescription())
                .status(t.getStatus()).priority(t.getPriority())
                .createdAt(t.getCreatedAt()).updatedAt(t.getUpdatedAt())
                .build();
    }
}
