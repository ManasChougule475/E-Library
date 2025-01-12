package com.minorproject.library.e_Library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("author")
    private String author;

    @Column(unique=true)
    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private Category category;

    public enum Category{
        FICTION,
        NON_FICTION
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }

}
