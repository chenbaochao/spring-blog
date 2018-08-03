package com.blog.cbc.auth.config;

import com.blog.cbc.auth.util.ResponseBean;
import com.blog.cbc.auth.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cbc on 2018/3/30.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseBean handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("异常信息为：{}",e);
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        String message = errors.stream().map(u -> u.getField() + ": " + u.getDefaultMessage()).collect(Collectors.joining(","));
        return ResultUtil.error(HttpStatus.BAD_REQUEST.value(),message);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseBean handUsernameNotFoundException(UsernameNotFoundException e){
        log.error("异常信息为：{}",e);
        return ResultUtil.error(401,e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseBean handAuthenticationException(AuthenticationException e){
        log.error("异常信息为：{}",e);
        return ResultUtil.error(401,e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseBean handException(IllegalArgumentException e){
        log.error("异常信息为：{}",e);
        return ResultUtil.error(500,e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseBean handException(Exception e){
        log.error("异常信息为：{}",e);
        return ResultUtil.error(500,e.getMessage());
    }
}