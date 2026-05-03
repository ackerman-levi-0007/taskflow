package com.taskflow.api.dto;

import com.taskflow.api.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateTaskRequest {
    @NotBlank
    @Size(max = 200)
    private String title;
    @NotBlank
    @Size(max = 2000)
    private String description;
    private TaskPriority priority;
}
