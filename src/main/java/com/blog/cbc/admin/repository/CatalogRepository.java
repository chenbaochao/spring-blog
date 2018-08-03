package com.blog.cbc.admin.repository;


import com.blog.cbc.admin.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {


}
