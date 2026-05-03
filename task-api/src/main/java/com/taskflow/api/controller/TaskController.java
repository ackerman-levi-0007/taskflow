package com.taskflow.api.controller;

import com.taskflow.api.dto.CreateTaskRequest;
import com.taskflow.api.dto.TaskResponse;
import com.taskflow.api.dto.UpdateStatusRequest;
import com.taskflow.api.enums.TaskStatus;
import com.taskflow.api.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(
            @Valid @RequestBody CreateTaskRequest taskRequest
    ){
        TaskResponse createdTask = taskService.create(taskRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> list(
            @RequestParam(required = false) TaskStatus status,
            @PageableDefault(size = 20) Pageable pageable
    ){
        Page<TaskResponse> tasks = taskService.list(status, pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(
            @PathVariable Long id
    ){
        TaskResponse task = taskService.getByID(id);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequest updateStatusRequest
    ){
        TaskResponse updatedTask = taskService.updateStatus(id, updateStatusRequest.status());
        return ResponseEntity.ok(updatedTask);
    }
}
