package com.example.assignment_2_springweb.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity  // Turn class into entity. This will require a primary key.
public class Movie {
    @Id     // primary key annotation.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autogenerate id
    @Column(name = "movie_id")
    private int id;             // int can't be NULL
    @Column(name = "movie_title", length = 50, nullable = false)
    private String title;
    // Genre (just a simple string of comma separated genres)
    @Column(name = "movie_genre", length = 100)
    private String genre;
    @Column(name = "release_year", length = 4)
    private Integer release;    // Integer can be NULL
    @Column(name = "director", length = 50, nullable = false)
    private String director;
    @Column(name = "poster_URL", length = 100)
    private String poster;
    @Column(name = "trailer_link", length = 100)
    private String trailer;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "movie_characters", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<Characters> characters;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @JsonGetter("franchise")
    public Integer jsonGetProfessor() {
        if(franchise != null)
            return franchise.getId();
        return null;
    }

    @JsonGetter("characters")
    public List<Integer> jsonGetSubjects() {
        if(characters != null)
            return characters.stream().map(Characters::getId)
                    .collect(Collectors.toList());
        return null;
    }

}
