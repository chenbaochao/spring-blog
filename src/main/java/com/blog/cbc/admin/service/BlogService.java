package com.blog.cbc.admin.service;



import com.blog.cbc.admin.domain.Blog;
import com.blog.cbc.admin.domain.Catalog;
import com.blog.cbc.admin.repository.BlogRepository;
import com.blog.cbc.admin.repository.CatalogRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    private final CatalogRepository catalogRepository;

    public Blog addBlog( Blog blog) {
        blog.setCreateDate(LocalDateTime.now());
        blog.setUpdateDate(LocalDateTime.now());
        blog.setCommentSize(0L);
        blog.setReadSize(0L);
        blogRepository.save(blog);
        return blog;
    }

    public Blog getBlogById(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        blog.ifPresent(u -> {
            Optional<Catalog> catalog = catalogRepository.findById(u.getCatalogId());
            u.setCatalog(catalog.orElseGet(Catalog::new));
        });
        return blog.orElseGet(Blog::new);
    }

    public void updateBlog(Blog blog) {
        blog.setUpdateDate(LocalDateTime.now());
        blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public Page<Blog> getBlog(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findAll(pageable);
        blogs.getContent().forEach(u -> {
            Optional<Catalog> catalog = catalogRepository.findById(u.getCatalogId());
            u.setCatalog(catalog.orElseGet(Catalog::new));
        });
        return blogs;
    }

    public List<String> getTags() {
        return blogRepository.findTags();
    }

    public List<Blog> getBlogsByYear(String year) {
        List<Blog> blogs = blogRepository.getBlogsByYear(year);

        return blogs;
    }
}
