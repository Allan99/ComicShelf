package com.comic.comicshelf.dto;

import com.comic.comicshelf.entities.Comic;
import com.comic.comicshelf.entities.enums.ImageFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComicExhibitionCardDTO {

    private Long id;
    private String title;
    private String imgUrl;
    private String sinopse;
    private Instant createdAt;
    private Integer year;
    private Long sizeInBytes;

    private String category;

    public ComicExhibitionCardDTO(Comic entity) {
        id = entity.getId();
        title = entity.getTitle();
        imgUrl = entity.getImgUrl();
        sinopse = entity.getSinopse();
        createdAt = entity.getCreatedAt();
        year = entity.getPublishedAt().atZone(ZoneId.systemDefault()).getYear();
        sizeInBytes = entity.getSizeInBytes();
        category = entity.getCategories().iterator().next().getName();
    }

}
