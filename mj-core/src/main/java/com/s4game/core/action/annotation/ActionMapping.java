package com.s4game.core.action.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 协议号与方法的映射
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年4月26日 下午6:04:58
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionMapping {

	String mapping() default "";
	
}
