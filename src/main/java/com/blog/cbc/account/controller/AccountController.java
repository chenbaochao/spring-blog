package com.blog.cbc.account.controller;

import com.blog.cbc.auth.domain.User;
import com.blog.cbc.auth.service.IUserService;

import com.blog.cbc.auth.util.ResponseBean;
import com.blog.cbc.auth.util.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/account/api/user")
public class AccountController {


    private final IUserService userServiceImpl;


    @PostMapping
    public ResponseBean createUser(@Valid User user){
        userServiceImpl.create(user);
        return ResultUtil.success();
    }
}
