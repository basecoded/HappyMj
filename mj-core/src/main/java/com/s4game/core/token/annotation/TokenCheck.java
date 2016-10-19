package com.s4game.core.token.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zeusgooogle
 * @date 2014-9-30 下午04:51:06
 */
@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenCheck {
    String component();
}
