package anno.preCheck.user;

import javax.validation.constraints.NotNull;

/**
 * 当我们对外部提供接口的时候，会对其中部分参数有一定的要求，比如某些参数值不能为空等。
 * 大多数情况下我们都需要自己主动进行校验，判断对方传入的值是否合理。
 * 这里推荐一个使用hibernateValudator + 自定义注解 + AOP实现参数校验的方式
 */
public class User {
    private String idempotentNo;

    @NotNull(message = "userName can't be null")
    private String userName;
}
