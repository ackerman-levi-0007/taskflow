package com.taskflow.api.dto;

import com.taskflow.api.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest (
    @NotBlank @Size(max = 200) String title,
    @NotBlank @Size(max = 2000) String description,
    TaskPriority priority){}
