package com.blog.cbc.admin.controller;

import com.blog.cbc.admin.domain.Blog;
import com.blog.cbc.admin.service.BlogService;
import com.blog.cbc.auth.domain.User;

import com.blog.cbc.auth.util.ResponseBean;
import com.blog.cbc.auth.util.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/blog")
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseBean addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog){
        blog.setUserId(user.getId());
        return ResultUtil.success(blogService.addBlog(blog));
    }

    @GetMapping("/{id}")
    public ResponseBean getBlogById(@PathVariable Long id){
        Blog blog = blogService.getBlogById(id);
        return ResultUtil.success(blog);
    }

    @PutMapping
    public ResponseBean updateBlog(@RequestBody @Valid Blog blog){
        blogService.updateBlog(blog);
        return ResultUtil.success();
    }

    @DeleteMapping("/{id}")
    public ResponseBean deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return ResultUtil.success();
    }

    @GetMapping
    public ResponseBean getBlog(@RequestParam(required = false) Long catalogId,
                                @RequestParam(required = false) String tags,
                                Pageable pageable){
        Page<Blog> blogs = blogService.getBlog(catalogId,tags,pageable);
        HashMap<Object, Object> data = new HashMap<>();
        data.put("count",blogs.getTotalElements());
        data.put("rows",blogs.getContent());
        return ResultUtil.success(data);
    }

    @GetMapping("/tags")
    public ResponseBean getTags(){
        List<String> tags = blogService.getTags();
        return ResultUtil.success(tags);
    }

    @GetMapping("/by/year")
    public ResponseBean getBlogsByYear(@RequestParam(required = false) String year){
        List<Blog> blogs = blogService.getBlogsByYear(year);
        HashMap<String, Object> map = new HashMap<>();
        map.put("count",blogs.size());
        map.put("rows",blogs);
        map.put("year",year);
        return ResultUtil.success(map);
    }
}

