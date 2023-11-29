package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.AccessType;

import java.util.Objects;

@Entity

public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
