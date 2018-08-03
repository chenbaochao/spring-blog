package com.blog.cbc.auth.controller;


import com.blog.cbc.auth.domain.User;
import com.blog.cbc.auth.service.IUserService;
import com.blog.cbc.auth.util.ResponseBean;
import com.blog.cbc.auth.util.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/uaa/user")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/current")
    public Principal getUser(Principal principal){
        return principal;
    }


   // @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping
    public ResponseBean createUser(@RequestBody User user) {
        userService.create(user);
        return ResultUtil.success();
    }

}
