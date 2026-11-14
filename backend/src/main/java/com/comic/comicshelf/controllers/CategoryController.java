package com.comic.comicshelf.controllers;

import com.comic.comicshelf.dto.CategoryDTO;
import com.comic.comicshelf.services.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categoryDTOList = service.findAll();
        return ResponseEntity.ok().body(categoryDTOList);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<CategoryDTO> findByName(@PathVariable String name){
        CategoryDTO categoryDTO = service.findByName(name);
        return ResponseEntity.ok().body(categoryDTO);
    }
}
