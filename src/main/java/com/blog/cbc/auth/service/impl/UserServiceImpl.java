package com.blog.cbc.auth.service.impl;


import com.blog.cbc.auth.domain.User;
import com.blog.cbc.auth.repository.RoleRepository;
import com.blog.cbc.auth.repository.UserRepository;
import com.blog.cbc.auth.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {


  //  private static final String EMAIL_RULE = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

  //  private static final String PHONE_RULE = "^[1][3,4,5,7,8][0-9]{9}$";

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void create(User user) {
        paramsCheck(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName("用户"))));
        userRepository.save(user);

        log.info("创建新用户：{}", user.getUsername());

    }


    /**
     *  校验注册参数
     * @param user
     */
    private void paramsCheck(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("用户名已存在！");
        }
/*        if(userRepository.findByPhone(user.getPhone()).isPresent()){
            throw new RuntimeException("手机号已存在！");
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("邮箱已存在！");
        }
        if(!Pattern.compile(PHONE_RULE).matcher(user.getPhone()).matches()){
            throw new RuntimeException("手机号格式错误！");
        }
        if(!Pattern.compile(EMAIL_RULE).matcher(user.getEmail()).matches()){
            throw new RuntimeException("邮箱格式错误! ");
        }*/
    }
}
