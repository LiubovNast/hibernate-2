package com.movie.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(schema = "movie", name = "language")
public class Language {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(columnDefinition = "char")
    private String name;
    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "film_id")
//    private Set<Film> films = new HashSet<Film>();
}
