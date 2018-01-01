package com.example;

import com.example.reflect.name;
import com.example.reflect.persion;
import com.example.reflect.sbPersion;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyClass {

    public static void main(String atgs[]){
        sbPersion sbPersion = new sbPersion();

        persion persion = (com.example.reflect.persion) Proxy.newProxyInstance(sbPersion.getClass().getClassLoader(),new Class[]{persion.class
            },new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("1111");
                for (Annotation annotation : method.getAnnotations()) {
                    if(annotation instanceof name){
                        System.out.println(((name)annotation).value());

                    }
                }

                return null;
            }
        });
        persion.buyHouse();
    }
}
