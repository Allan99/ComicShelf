package com.comic.comicshelf.dto;

import com.comic.comicshelf.entities.Category;
import com.comic.comicshelf.entities.Comic;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
public class CategoryDTO {

    private Long id;
    private String name;

    @Setter(AccessLevel.NONE)
    private Set<ComicDTO> comics = new HashSet<>();

    public CategoryDTO(Category entity){
        id = entity.getId();
        name = entity.getName();
    }

    public CategoryDTO(Category entity, Set<Comic> comics){
        id = entity.getId();
        name = entity.getName();
        this.comics = comics
                .stream()
                .map(c -> new ComicDTO(c))
                .collect(Collectors.toSet());
    }
}
