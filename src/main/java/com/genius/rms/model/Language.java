package com.genius.rms.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String locale;

    @Column
    private String langKey;

    @Column
    private String langValue;
}
