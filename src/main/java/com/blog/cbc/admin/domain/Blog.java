package com.blog.cbc.admin.domain;


import com.blog.cbc.auth.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Accessors()
public class Blog{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Long catalogId;

    @Length(min = 2,max = 50,message = "标题的长度必须在2-50个字符之间！")
    private String title;

    @Length(min = 2,max = 255,message = "摘要的长度必须在2-255个字符之间！")
    private String summary;

    @Lob
    private String content;

    private Long readSize;

    private Long commentSize;

    private String tags;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Transient
    private Catalog catalog;
    @Transient
    private User user;
}
