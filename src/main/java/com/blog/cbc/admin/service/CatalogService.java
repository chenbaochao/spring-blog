package com.blog.cbc.admin.service;


import com.blog.cbc.admin.domain.Catalog;
import com.blog.cbc.admin.repository.CatalogRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;


    public Catalog addCatalog(Catalog catalog){
        Optional<Catalog> exist = catalogRepository.findOne(Example.of(catalog));
        if(exist.isPresent()){
            throw new RuntimeException("当前分类已经存在!");
        }
        catalog.setCreateDate(LocalDateTime.now());
        catalog.setUpdateDate(LocalDateTime.now());
        catalogRepository.save(catalog);
        return catalog;
    }

    public List<Catalog> getCatalog() {
        List<Catalog> catalogs = catalogRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
        return catalogs;
    }

    public Catalog deleteCatalog(Long id) {
        Optional<Catalog> catalog = catalogRepository.findById(id);
        catalogRepository.deleteById(id);
        return catalog.get();
    }

    public Optional<Catalog> getCatalogById(Long id) {
        Optional<Catalog> catalog = catalogRepository.findById(id);
        if(catalog.isPresent()){
            return catalog;
        }
        return Optional.empty();
    }

    public  void  updateCatalog(Long id,Catalog catalog) {
        catalog.setUpdateDate(LocalDateTime.now());
        catalogRepository.save(catalog);
    }
}
