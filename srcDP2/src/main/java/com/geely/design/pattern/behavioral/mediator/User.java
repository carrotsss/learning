package com.geely.design.pattern.behavioral.mediator;

/**
 * Created by carrots on 2019/1/26.
 */
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        StudyGroup.showMessage(this,message);
    }
}