package com.example.utils;

import net.sf.cglib.beans.BeanCopier;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将beancopier做成静态类，方便拷贝
 */
public class BeanUtils {

    /**
     * copier cache
     */
    private static ConcurrentHashMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<String, BeanCopier>();

    /**
     * 将source的同名属性复制到target.适用于两个不同类型的对象
     *
     * @param a 资源类
     * @param b 目标类
     */
    public static void copyA2B(Object a, Object b) {
        BeanCopier copier = getBeanCopier(a, b);
        copier.copy(a, b, null);
    }

    /**
     * get beanCopies from source and target
     *
     * @param source source
     * @param target target
     * @return BeanCopies
     */
    private static BeanCopier getBeanCopier(Object source, Object target) {
        String key = source.getClass().toString() + target.getClass().toString();
        BeanCopier beanCopier = beanCopierMap.get(key);
        if (beanCopier == null) {
            beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(key, beanCopier);
        }
        return beanCopier;
    }




}
