package com.comic.comicshelf.repositories;

import com.comic.comicshelf.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT obj FROM Category obj " +
            "JOIN FETCH obj.comics " +
            "WHERE UPPER(obj.name) = UPPER(:name)")
    Optional<Category> searchByName(String name);
}
