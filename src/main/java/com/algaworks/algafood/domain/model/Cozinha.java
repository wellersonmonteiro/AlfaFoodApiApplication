package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;

import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode


public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cozinha cozinha = (Cozinha) o;
        return id == cozinha.id && name.equals(cozinha.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
