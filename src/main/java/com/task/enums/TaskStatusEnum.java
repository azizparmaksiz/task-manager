package com.task.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatusEnum {

    COMPLETED("COMPLETED"),PENDING("PENDING"),WORKING("WORKING");


    private String status;

     TaskStatusEnum(String status){
        this.status=status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
