package anno.preCheck.util;

import org.hibernate.validator.HibernateValidator;

import javax.validation.*;
import java.util.Set;

//使用Hibernate validator定义一个工具类，用于做参数校验
public class BeanValidateor {
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
            .buildValidatorFactory().getValidator();

    public static void validateObject(Object object, Class<?>... groups) throws ValidationException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (constraintViolations.stream().findFirst().isPresent()) {
            throw new ValidationException(constraintViolations.stream().findFirst().get().getMessage());
        }
    }
}
