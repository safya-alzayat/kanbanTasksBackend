package com.kanbanTasks.kanbanTasksBackend.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String tag;
    
    @NotBlank(message = "Status must be provided")
    private String status;
    
    @NotNull(message = "Priority must be provided")
    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    @NotNull(message = "CreatedAt timestamp is required")
    private Long createdAt;
}

