package com.zhixin.interceptor;


public @interface Token {

    boolean save() default false;

    boolean remove() default false;
}