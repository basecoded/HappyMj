package com.s4game.core.data.accessor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author zeusgooogle
 * @date 2014-10-2 下午08:04:02
 */
public class AccessorManager implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private String defaultAccessType;

    public Map<String, String> accessors = new HashMap<String, String>();

    public AccessorManager() {
    }

    public void setAccessors(Map<String, String> accessors) {
        this.accessors = accessors;
    }

    public void setDefaultDbType(String defaultAccessType) {
        this.defaultAccessType = defaultAccessType;
    }

    public IDbAccessor getAccessor(String accessType) {
        IDbAccessor localIDbAccessor = (IDbAccessor) this.applicationContext.getBean(this.accessors.get(accessType), IDbAccessor.class);
        if (null == localIDbAccessor) {
            throw new NullPointerException("no accessor type:" + accessType);
        }
        return localIDbAccessor;
    }

    public String getDefaultAccessType() {
        return this.defaultAccessType;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
