package com.blog.cbc.admin.controller;

import com.blog.cbc.admin.domain.Catalog;
import com.blog.cbc.admin.service.CatalogService;
import com.blog.cbc.auth.domain.User;
import com.blog.cbc.auth.util.ResponseBean;
import com.blog.cbc.auth.util.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping
    public ResponseBean addCatalog(@AuthenticationPrincipal User user,@RequestBody @Valid Catalog catalog){
        catalog.setUserId(user.getId());
        return ResultUtil.success(catalogService.addCatalog(catalog));
    }

    @GetMapping
    public ResponseBean getCatalog(){
        List<Catalog> catalogs =  catalogService.getCatalog();
        HashMap<Object, Object> data = new HashMap<>();
        data.put("count",catalogs.size());
        data.put("rows",catalogs);
        return ResultUtil.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseBean deleteCatalog(@PathVariable Long id){
        Catalog catalog = catalogService.deleteCatalog(id);
        return ResultUtil.success(catalog);
    }

    @PutMapping("/{id}")
    public ResponseBean updateCatalog(@PathVariable Long id, @RequestBody Catalog catalog){
       catalogService.updateCatalog(id,catalog);
       return ResultUtil.success();
    }

    @GetMapping("/{id}")
    public ResponseBean getCatalogById(@PathVariable Long id){
        return ResultUtil.success(catalogService.getCatalogById(id));
    }


}
