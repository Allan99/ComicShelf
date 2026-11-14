package com.comic.comicshelf.services;

import com.comic.comicshelf.dto.CategoryDTO;
import com.comic.comicshelf.entities.Category;
import com.comic.comicshelf.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> categoryPage = repository.findAll();
        return categoryPage.stream().map(CategoryDTO::new).toList();
    }
}
