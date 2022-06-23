package anno.basic;

import java.lang.annotation.*;

/**
 * 元注解：
 *
 * @Target
 * @Retention
 * @Documented
 * @Inherited
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface GoodsType {
    public enum Type {LIFE, STUDY, WORK}

    Type goodsType() default Type.LIFE;
}
