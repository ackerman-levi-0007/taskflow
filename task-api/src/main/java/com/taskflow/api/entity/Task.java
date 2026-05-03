package com.taskflow.api.entity;

import com.taskflow.api.dto.CreateTaskRequest;
import com.taskflow.api.enums.TaskPriority;
import com.taskflow.api.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, length = 2000)
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = Instant.now();
    }

    public static Task from(CreateTaskRequest createTaskRequest){
        return Task.builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .priority(createTaskRequest.getPriority())
                .build();
    }
}
