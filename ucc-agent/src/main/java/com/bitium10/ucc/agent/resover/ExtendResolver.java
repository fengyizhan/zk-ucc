/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.agent.resover;

import com.bitium10.ucc.agent.ExtendDataStore;
import com.bitium10.ucc.agent.Resolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.agent <br>
 * <b>类名称</b>： Notify <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/4 2:14<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ExtendResolver<T> extends Resolver<T> {
    protected final static Logger _LOG = LoggerFactory.getLogger(ExtendResolver.class);

    private String tempKey;
    private ExtendDataStore<T> store;

    public ExtendResolver(Class clazz, Field field) {
        super(clazz, field);
    }

    public ExtendResolver(String tempKey, ExtendDataStore<T> store, Class clazz, Field field) {
        super(clazz,field);
        this.tempKey = tempKey;
        this.store = store;
    }

    @Override
    public T get() {
        return (T) tempKey;
    }

    @Override
    public void set(String src) {
        if(src == null) {
            _LOG.info("temp key null null");
            return;
        }
        T t = store.getValue(src);
        try {
            field.setAccessible(true);
            field.set(clazz,t);
        } catch (IllegalAccessException e) {
            _LOG.debug("illegal access exception..", e);
        }
    }

    public String getTempKey() {
        return tempKey;
    }

    public void setTempKey(String tempKey) {
        this.tempKey = tempKey;
    }
}
