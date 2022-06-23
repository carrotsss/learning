package anno.preCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作用：facade接口注解，用于统一对facade进行参数校验及异常捕获
 * 注意：使用该注解要注意，该方法的返回值必须是BaseResponse的子类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Facade {

}
