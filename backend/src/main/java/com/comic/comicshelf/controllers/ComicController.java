package com.comic.comicshelf.controllers;

import com.comic.comicshelf.dto.CategoryDTO;
import com.comic.comicshelf.dto.ComicDTO;
import com.comic.comicshelf.dto.ComicExhibitionCardDTO;
import com.comic.comicshelf.services.CategoryService;
import com.comic.comicshelf.services.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/comics")
public class ComicController {

    @Autowired
    private ComicService service;

    @GetMapping
    public ResponseEntity<Page<ComicExhibitionCardDTO>> findAll(Pageable pageable){
        Page<ComicExhibitionCardDTO> comicDTOList = service.findAll(pageable);
        return ResponseEntity.ok().body(comicDTOList);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Page<ComicExhibitionCardDTO>> findByName(Pageable pageable,
                                                             @PathVariable String name){
        Page<ComicExhibitionCardDTO> comicExhibitionCardDTO = service.findByName(pageable, name);
        return ResponseEntity.ok().body(comicExhibitionCardDTO);
    }

    @PostMapping
    public ResponseEntity<ComicDTO> insert(@RequestBody ComicDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ComicDTO> update(
            @PathVariable Long id,
            @RequestBody ComicDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
