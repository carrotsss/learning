//package anno.preCheck;
//
//import anno.preCheck.util.BeanValidateor;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.ValidationException;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
///**
// * facade的切面处理类，统一统计进行参数校验及异常捕获
// * 定义了一个切面，会对所有标注@Facade的方法进行统一处理，记载开始方法调用前进行参数校验，一旦校验失败，侧返回一个固定的失败的response
// */
//@Aspect
//@Component
//public class FacadeAspect {
//    private static final Logger LOGGER = LoggerFactory.getLogger(FacadeAspect.class);
//
//    @Autowired
//    HttpServletRequest request;
//
//    @Around("@annotation(anno.preCheck.Facade)")
//    public Object facade(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
//        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
//        Object[] args = proceedingJoinPoint.getArgs();
//        Class returnType = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getReturnType();
//        //循环遍历所有参数，进行参数校验
//        for (Object parameter : args) {
//            try {
//                BeanValidateor.validateObject(parameter);
//            } catch (ValidationException e) {
//                return getFailedResponse(returnType, e);
//            }
//        }
//        try {
//            Object response = proceedingJoinPoint.proceed();
//            return response;
//        } catch (Throwable throwable) {
//            return getFailedResponse(returnType, throwable);
//        }
//    }
//
//    private Object getFailedResponse(Class returnType, Throwable throwable) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
//        //如果返回的类型为BaseResponse的子类，则创建一个通用的失败响应
//        if (returnType.getDeclaredConstructor().newInstance() instanceof HttpServletResponse) {
//            HttpServletResponse response = (HttpServletResponse) returnType.getDeclaredConstructor().newInstance();
//            response.setStatus(1);
//            return response;
//        }
//        LOGGER.error("failed to getFailResponse, returnType(" + returnType + ") is not instanceof httpServletResonse");
//        return null;
//    }
//}
