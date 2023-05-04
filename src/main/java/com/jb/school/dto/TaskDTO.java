package com.jb.school.dto;

import javax.persistence.*;


public class TaskDTO {
    private String description ;
    private Long priority  ;
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getPriority() {
        return priority;
    }
    
    public void setPriority(Long priority) {
        this.priority = priority;
    }
}
