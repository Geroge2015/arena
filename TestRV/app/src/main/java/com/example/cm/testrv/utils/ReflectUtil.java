package com.example.cm.testrv.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by George on 2017/12/7.
 */

public class ReflectUtil {
    private static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> T invoke(String method, Object receiver, Class<T> returnClass,
                               Class[] paramsClass, Object[] params) {
        if (isEmpty(method) || receiver == null) {
            throw new NullPointerException("reflect method or receiver is null");
        }

        if (paramsClass == null && params != null)
            throw new IllegalArgumentException("illegal agument");

        if (paramsClass != null && params != null
                && params.length != paramsClass.length)
            throw new IllegalArgumentException("illegal aguments count");
        try {
            Method methodObj = receiver.getClass().getDeclaredMethod(method, paramsClass);
            methodObj.setAccessible(true);
            return (T)methodObj.invoke(receiver, params);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    public static Object invokeMethod(Object object, String methodName,
                                      Class[] type, Object[] value) {
        Object obj = null;
        try {
            Method method = getDeclaredMethod(object, methodName, type);
            method.setAccessible(true);
            obj = method.invoke(object, value);
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    public static Method getDeclaredMethod(Object target, String name, Class[] type) throws NoSuchMethodException {
        Class<?> clazz = target.getClass();
        Method method = null;
        while (clazz != null) {
            try {
                method = clazz.getDeclaredMethod(name, type);
            } catch (Exception ex) {

            }
            if (method != null) {
                return method;
            }
            clazz = clazz.getSuperclass();
        }
        throw new NoSuchMethodException();
    }

    public static Object getFieldValue(Object target, String member) {
        Field field;
        Class classType = target.getClass();
        while (classType != Object.class) {
            try {
                field = classType.getDeclaredField(member);
                field.setAccessible(true);
                return field.get(target);
            } catch (Exception e) {
                classType = classType.getSuperclass();
            }
        }
        return null;
    }

}
