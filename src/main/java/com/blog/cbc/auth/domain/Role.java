package com.blog.cbc.auth.domain;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *   角色
 * Created by cbc on 2017/12/27.
 */
@Data
@Accessors(chain=true)
@NoArgsConstructor
@Builder
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    private String type;

    @Override
    public String getAuthority() {
        return this.type;
    }

    public Role(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }


}
