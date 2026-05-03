package com.taskflow.api.dto;

import com.taskflow.api.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequest(@NotNull TaskStatus status) {
}
