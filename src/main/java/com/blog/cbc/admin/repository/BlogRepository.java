package com.blog.cbc.admin.repository;

import com.blog.cbc.admin.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query(nativeQuery = true,value = "select a.tags from blog a")
    List<String> findTags();

    @Query(nativeQuery = true,value = "select * from blog where create_date >= ?1")
    List<Blog> getBlogsByYear(String year);
}
