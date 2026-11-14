package com.comic.comicshelf.dto;

import com.comic.comicshelf.entities.Category;
import com.comic.comicshelf.entities.Comic;
import com.comic.comicshelf.entities.enums.ImageFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class ComicDTO {

    private Long id;
    private String title;
    private String imgUrl;
    private String sinopse;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant publishedAt;
    private String language;
    private Long sizeInBytes;
    private ImageFormat imageFormat;

    private Set<CategoryDTO> categories = new HashSet<>();

    public ComicDTO(Comic entity){
        id = entity.getId();
        title = entity.getTitle();
        imgUrl = entity.getImgUrl();
        sinopse = entity.getSinopse();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();
        publishedAt = entity.getPublishedAt();
        language = entity.getLanguage();
        sizeInBytes = entity.getSizeInBytes();
        imageFormat = entity.getImageFormat();
    }

    public ComicDTO(Comic entity, Set<Category> categories){
        id = entity.getId();
        title = entity.getTitle();
        imgUrl = entity.getImgUrl();
        sinopse = entity.getSinopse();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();
        publishedAt = entity.getPublishedAt();
        language = entity.getLanguage();
        sizeInBytes = entity.getSizeInBytes();
        imageFormat = entity.getImageFormat();
        this.categories = categories
                .stream()
                .map(cat -> new CategoryDTO(cat))
                .collect(Collectors.toSet());
    }
}
