package org.example.domain.dao;

public interface AbstractEntity<I> {
    I getId();

    void setId(I id);
}

