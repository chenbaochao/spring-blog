package com.blog.cbc.admin.domain;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    @Length(min = 2,max = 50)
    private String name;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
