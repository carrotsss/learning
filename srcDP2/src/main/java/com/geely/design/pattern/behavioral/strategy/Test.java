//package com.geely.design.pattern.behavioral.strategy;
//
//import org.apache.commons.lang3.StringUtils;
//
///**
// * Created by geely
// */
//public class Test {
//    public static void main(String[] args) {
//        PromotionActivity promotionActivity618 = new PromotionActivity(new LiJianPromotionStrategy());
//        PromotionActivity promotionActivity1111 = new PromotionActivity(new FanXianPromotionStrategy());
//
//        promotionActivity618.executePromotionStrategy();
//        promotionActivity1111.executePromotionStrategy();
//    }
//
//    public static void main(String[]args){
//        PromotionActivity promotionActivity = null;
//
//        /** 我们来创建一个promotionKey */
//        String promotionKey = "LIJIAN";
//
//        if (StringUtils.equals(promotionKey, "LIJIAN")) {
//            promotionActivity = new PromotionActivity(new LiJianPromotionStrategy());
//        } else if (StringUtils.equals(promotionKey, "FANXIAN")) {
//            promotionActivity = new PromotionActivity(new FanXianPromotionStrategy());
//        }//......
//        promotionActivity.executePromotionStrategy();
//    }
//
//
//    public static void main(String[]args){
//
//        /** 我们来创建一个promotionKey */
//        String promotionKey = "LIJIAN";
//
//        PromotionActivity promotionActivity = new PromotionActivity(PromotionStrategyFactory.getPromotionStrategy(promotionKey));
//        promotionActivity.executePromotionStrategy();
//    }
//}
