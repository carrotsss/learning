package com.geely.design.pattern.behavioral.chainofresponsibility;

/**
 * Created by carrots on 2019/1/26.
 */
/**
 * 批准者
 */
public abstract class Approver {
    /** 责任链的核心就是在类里面包含了一个自己同样类型的一个对象 */
    protected Approver approver;

    public void setNextApprover(Approver approver) {
        this.approver = approver;
    }

    public abstract void deploy(Course course) ;
}
