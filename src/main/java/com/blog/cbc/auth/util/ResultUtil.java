package com.blog.cbc.auth.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by cbc on 2018/3/30.
 */
public class ResultUtil {

    public static ResponseBean success(Object o){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(200);
        responseBean.setMsg("成功！");
        responseBean.setData(o);
        return responseBean;
    }

    public static ResponseBean success(){
        return success(null);
    };

    public static ResponseBean error(Integer code,String msg){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(code);
        responseBean.setMsg(msg);
        return responseBean;
    }

    public static void main(String[] args) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = encoder.encode("blog-account");
        System.out.println(encode);



    }


}
