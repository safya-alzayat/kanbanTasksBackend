package com.kanbanTasks.kanbanTasksBackend.kafka;

import com.kanbanTasks.kanbanTasksBackend.model.Task;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kanbanTasks.events.TaskEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskProducer {

    private final KafkaTemplate<String, TaskEvent> kafkaTemplate;
    private static final String TOPIC = "task-events";


    public void sendTaskEvent(Task task) {
        TaskEvent event = TaskEvent.newBuilder()
                .setId(task.getId())
                .setTitle(task.getTitle())
                .setTag(task.getTag())
                .setStatus(task.getStatus())
                .setPriority(task.getPriority().name())
                .setAssignedTo(task.getAssignedTo() != null ? task.getAssignedTo() : "")
                .build();
        kafkaTemplate.send(TOPIC, event);
        log.info("[KAFKA] Sent task event for ID: {}", task.getId());
    }
}
