package org.example.domain;

import org.example.domain.dao.AbstractEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Winkelmand implements AbstractEntity<Long> {

    @Id
    @GeneratedValue
    private long id;

    public Winkelmand() {
    }

    @OneToMany(fetch = FetchType.EAGER)
    public Set<Artikel> artikelen = new HashSet<Artikel>();

    @OneToOne
    private Gebruiker eigenaar;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void addArtikel(Artikel a) {
        this.artikelen.add(a);
    }

    public void removeArtikel(Artikel a) {
        System.out.println(a);
        System.out.println(artikelen.size());
        artikelen.remove(a);
        System.out.println(artikelen.size());
    }

    public void setEigenaar(Gebruiker eigenaar) {
        this.eigenaar = eigenaar;
    }

    public void removeArtikelen() {
        this.artikelen.clear();
    }

    @Override
    public String toString() {
        return "Winkelmand{" +
                "id=" + id +
                ", artikelen=" + artikelen +
                '}';
    }
}
