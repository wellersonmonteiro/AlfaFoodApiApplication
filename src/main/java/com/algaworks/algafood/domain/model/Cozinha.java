package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;

import java.util.Objects;

@JsonRootName("cozinha")

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)


public class Cozinha {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
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
