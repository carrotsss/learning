package com.geely.design.pattern.behavioral.mediator;

import java.util.Date;

/**
 * Created by carrots on 2019/1/26.
 */
public class StudyGroup {
    public static void showMessage(User user, String message) {
        System.out.println(new Date().toString()+"["+user.getName()+"]:"+message);
    }
}
