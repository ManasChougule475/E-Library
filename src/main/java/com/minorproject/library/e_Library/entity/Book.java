package com.minorproject.library.e_Library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minorproject.library.e_Library.enums.Category;
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
    private Category category = Category.NON_FICTION;

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

    // code added cause lombok not working :-
    public Double getPrice(){
        return price;
    }
}
