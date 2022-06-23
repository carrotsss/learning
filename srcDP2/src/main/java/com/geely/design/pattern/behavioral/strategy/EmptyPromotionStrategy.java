package com.geely.design.pattern.behavioral.strategy;

/**
 * Created by carrots on 2019/1/26.
 */
public class EmptyPromotionStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("无促销");
    }
}
