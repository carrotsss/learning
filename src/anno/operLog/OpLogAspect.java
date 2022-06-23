//package anno.operLog;
//
//import com.google.common.base.CaseFormat;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
//import org.springframework.expression.EvaluationContext;
//import org.springframework.expression.Expression;
//import org.springframework.expression.spel.standard.SpelExpressionParser;
//import org.springframework.expression.spel.support.StandardEvaluationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//
///**
// * 在一个方法入口或者出口处做统一的日志处理，比如记录一下入参、出参、记录下方法执行的时间等。
// * 如果在方法中自己写这样的代码的话，一方面会有很多代码重复，另外也容易被遗漏。
// * 这种场景，就可以使用自定义注解+切面实现这个功能
// * 下面我们想要在一些web请求方法上，记录本次操作具体做了什么事情，比如新增了一条记录或者删除了一条记录等。
// */
//@Aspect
//@Component
//public class OpLogAspect {
//    private static final Logger LOGGER = LoggerFactory.getLogger(OpLogAspect.class);
//
//    @Autowired
//    HttpServletRequest request;
//
//    /**
//     * 因为我们不急要在日志中记录本次操作了什么，还需要知道被操作的对象的具体的唯一标识，如订单号信息。
//     * 但是每一个接口方法的参数类型肯定是不一样的，很难有一个统一的标准，那么我们就可以借助spel表达式，记载表达式中知名如何获取对应的对象的唯一性标识。
//     * 注意：
//     * 1、使用@Arround注解来指定对标注了OpLog的方法设置切面
//     * 2、使用Spel的相关方法，通过指定的表示，从对应的参数中获取到目标对象的唯一性标志。
//     * 3、在方法执行成功后，输出日志。
//     * @param proceedingJoinPoint
//     * @return
//     * @throws Exception
//     */
//    @Around("@annotation(com.icbc.demo.anno.operLog.OpLog)")
//    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
//        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
//        OpLog opLog = method.getAnnotation(OpLog.class);
//        Object response = null;
//        try {
//            // 目标方法执行
//            response = proceedingJoinPoint.proceed();
//        } catch (Throwable throwable) {
//            throw new Exception(throwable);
//        }
//        if (!StringUtils.isEmpty(opLog.opItemIdExpression())) {
//            SpelExpressionParser parser = new SpelExpressionParser();
//            Expression expression = parser.parseExpression(opLog.opItemIdExpression());
//            EvaluationContext context = new StandardEvaluationContext();
//            //获取参数值
//            Object[] args = proceedingJoinPoint.getArgs();
//            //获取运行时参数名称
//            LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
//            String[] parameterNames = discoverer.getParameterNames(method);
//            //将参数绑定到context中
//            if (parameterNames != null) {
//                for (int i = 0; i < parameterNames.length; i++) {
//                    context.setVariable(parameterNames[i], args[i]);
//                }
//            }
//            //将方法的resp当做变量放到context中，变量名称为该类名转化为小写字母开头的驼峰形式
//            if (response != null) {
//                context.setVariable(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, response.getClass().getSimpleName()), response);
//            }
//            //解析表达式，获取结果
//            String itemId = String.valueOf(expression.getValue(context));
//            //执行日志记录
//            handle(opLog.opType(), opLog.opItem(), itemId);
//        }
//        return response;
//    }
//
//    public void handle(OpLog.OpType opType, String opItem, String opItemId) {
//        //通过日志打印输出
//        LOGGER.info("opType = " + opType.name() + ",opItem = " + opItem + ",opItemId = " + opItemId);
//    }
//
//}
