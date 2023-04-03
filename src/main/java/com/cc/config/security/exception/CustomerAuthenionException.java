package com.cc.config.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义异常
 */
public class CustomerAuthenionException extends AuthenticationException {
    public CustomerAuthenionException(String msg) {
        super(msg);
    }
}