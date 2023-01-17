package com.anakin.example.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @description: cache key 生成器
 * @author: anakin
 * @date 2019/8/22 15:07
 */
@Component("keyGenerator")
public class SpringCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return target.getClass().getSimpleName() + "_"
                + method.getName() + "_"
                + StringUtils.arrayToDelimitedString(params, "_");
    }
}
