package com.comic.comicshelf.entities;

import com.comic.comicshelf.entities.enums.ImageFormat;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_comic")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String sinopse;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
    @Column(name = "published_at", nullable = false)
    private Instant publishedAt;
    @Column(nullable = false)
    private String language;
    @Column(name = "size_in_bytes")
    private Long sizeInBytes;
    @Enumerated(EnumType.STRING)
    private ImageFormat imageFormat;

    @ManyToMany
    @JoinTable(
            name = "tb_comic_category",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @PrePersist
    protected void onCreate(){
        createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = Instant.now();
    }
}
