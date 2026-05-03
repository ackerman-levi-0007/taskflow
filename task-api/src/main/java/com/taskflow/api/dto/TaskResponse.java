package com.taskflow.api.dto;

import com.taskflow.api.entity.Task;
import com.taskflow.api.enums.TaskPriority;
import com.taskflow.api.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Instant createdAt;
    private Instant updatedAt;
}

