package com.comic.comicshelf.services;

import com.comic.comicshelf.dto.CategoryDTO;
import com.comic.comicshelf.dto.ComicExhibitionCardDTO;
import com.comic.comicshelf.entities.Category;
import com.comic.comicshelf.entities.Comic;
import com.comic.comicshelf.repositories.CategoryRepository;
import com.comic.comicshelf.services.exceptions.ResourceNotFoundException;
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

    @Transactional(readOnly = true)
    public CategoryDTO findByName(String catName){
        Category cat = repository.searchByName(catName)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new CategoryDTO(cat, cat.getComics());
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto){
        Category category = new Category();
        category.setName(dto.getName());
        category = repository.save(category);
        return new CategoryDTO(category);
    }
}
