package com.comic.comicshelf.repositories;

import com.comic.comicshelf.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
