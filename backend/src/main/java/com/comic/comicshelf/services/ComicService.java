package com.comic.comicshelf.services;

import com.comic.comicshelf.dto.CategoryDTO;
import com.comic.comicshelf.dto.ComicDTO;
import com.comic.comicshelf.dto.ComicExhibitionCardDTO;
import com.comic.comicshelf.entities.Category;
import com.comic.comicshelf.entities.Comic;
import com.comic.comicshelf.repositories.ComicRepository;
import com.comic.comicshelf.services.exceptions.DatabaseException;
import com.comic.comicshelf.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class ComicService {

    @Autowired
    private ComicRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional(readOnly = true)
    public Page<ComicExhibitionCardDTO> findAll(Pageable pageable){
        Page<Comic> comicsPage = repository.findAll(pageable);
        return comicsPage.map(c -> new ComicExhibitionCardDTO(c));
    }

    @Transactional(readOnly = true)
    public Page<ComicExhibitionCardDTO> findByName(Pageable pageable, String comicName){
        Page<Comic> comics = repository.findByTitleContainingIgnoreCase(pageable, comicName);
        return comics.map(c -> new ComicExhibitionCardDTO(c));
    }

    @Transactional
    public ComicDTO insert(ComicDTO dto){
        Comic comic = modelMapper.map(dto, Comic.class);
        for (CategoryDTO cat: dto.getCategories()){
            comic.getCategories().add(modelMapper.map(cat, Category.class));
        }
        comic.setId(null);
        comic.setUpdatedAt(Instant.now());
        comic = repository.save(comic);
        return new ComicDTO(comic, comic.getCategories());
    }

    @Transactional
    public ComicDTO update(Long id, ComicDTO dto){
        try {
            Comic comic = repository.getReferenceById(id);
            comic.setTitle(dto.getTitle());
            comic.setSizeInBytes(dto.getSizeInBytes());
            comic.setPublishedAt(dto.getPublishedAt());
            comic.setLanguage(dto.getLanguage());
            comic.setImageFormat(dto.getImageFormat());
            comic.setCreatedAt(dto.getCreatedAt());
            comic.setSinopse(dto.getSinopse());
            comic.setImgUrl(dto.getImgUrl());
            comic = repository.save(comic);
            return new ComicDTO(comic, comic.getCategories());
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found");
        }
        try{
            repository.deleteById(id);
            repository.flush();
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation error");
        }
    }
}
