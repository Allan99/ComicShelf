package com.comic.comicshelf.repositories;

import com.comic.comicshelf.entities.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComicRepository extends JpaRepository<Comic, Long> {

    Page<Comic> findByTitleContainingIgnoreCase(Pageable pageable, String comicName);
}
