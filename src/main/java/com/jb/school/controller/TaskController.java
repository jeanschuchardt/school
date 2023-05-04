package com.jb.school.controller;

import com.jb.school.dto.TaskDTO;
import com.jb.school.entity.Task;
import com.jb.school.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskRepository taskRepository;
    
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity addTeacherToCourse(@PathVariable("id") int id, @RequestBody TaskDTO taskDTO) {
        Optional<Task> optionalTask = taskRepository.findById((long) id);
        
        if (!optionalTask.isPresent()) {
            ResponseException responseException = new ResponseException("Cannot find task with given id", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseException);
        }
        
        if (Objects.isNull(taskDTO.getDescription())) {
            
            ResponseException responseException = new ResponseException("Task description is required", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
            
            
        }
        
        Task task = optionalTask.get();
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        
        try {
            Task save = taskRepository.save(task);
            TaskDTO response = new TaskDTO();
            response.setDescription(save.getDescription());
            response.setPriority(save.getPriority());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        }
        
        
    }
    
    
}

class ResponseException {
    private String message;
    private int status;
    
    public ResponseException(String message, int status) {
        this.message = message;
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
}